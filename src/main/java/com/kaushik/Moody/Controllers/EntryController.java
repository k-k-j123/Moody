package com.kaushik.Moody.Controllers;

import com.kaushik.Moody.Model.Entry;
import com.kaushik.Moody.Model.Users;
import com.kaushik.Moody.Service.EntryService;
import com.kaushik.Moody.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/entries")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<List<Entry>> getEntriesForCurrentUser(OAuth2AuthenticationToken token) {
        String email = (String) token.getPrincipal().getAttributes().get("email");
        Users user = userService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.status(403).build();
        }
        List<Entry> entries = entryService.getEntryByUser(user);
        return ResponseEntity.ok(entries);
    }

    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody Entry entry, OAuth2AuthenticationToken token) throws IOException {
        String email = (String) token.getPrincipal().getAttributes().get("email");
        Users user = userService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.status(403).body("Invalid user");
        }
        entry.setUser(user);
        entryService.createEntry(entry);
        return ResponseEntity.ok("Entry created successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable int id, OAuth2AuthenticationToken token) {
        Entry entry = entryService.findById(id); // Make sure this method exists in service/repo
        if (entry == null) {
            return ResponseEntity.notFound().build();
        }

        String email = (String) token.getPrincipal().getAttributes().get("email");
        Users user = userService.getUserByEmail(email);
        if (entry.getUser().getId()!=(user.getId())) {
            return ResponseEntity.status(403).body("Not your entry!");
        }

        entryService.deleteEntry(entry);
        return ResponseEntity.ok("Entry deleted successfully");
    }
}
