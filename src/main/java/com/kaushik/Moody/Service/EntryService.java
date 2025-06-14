package com.kaushik.Moody.Service;

import com.kaushik.Moody.Model.Entry;
import com.kaushik.Moody.Model.Users;
import com.kaushik.Moody.Repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private SentimentService sentimentService;

    public List<Entry> getEntryByUser(Users user) {
        return entryRepository.findByUser(user);
    }

    public void createEntry(Entry entry) throws IOException {
        Map<String, Object> sentimentScores = sentimentService.analyzeSentiment(entry.getContent());
        System.out.println(sentimentScores);

        entry.setCompoundScore(
                sentimentScores.get("compound") != null ? ((Number) sentimentScores.get("compound")).doubleValue() : 0.0
        );
        entry.setCreatedAt(LocalDateTime.now());
        entryRepository.save(entry);
    }

    public void deleteEntry(Entry entry) {
        entryRepository.delete(entry);
    }

    public void updateEntry(Entry entry) throws IOException {
        Optional<Entry> optionalEntry = Optional.ofNullable(entryRepository.findById(entry.getId()));
        if (optionalEntry.isPresent()) {
            Entry tempEntry = optionalEntry.get();

            tempEntry.setContent(entry.getContent());
            tempEntry.setUser(entry.getUser());
            tempEntry.setCreatedAt(entry.getCreatedAt());

            Map<String, Object> sentimentScores = sentimentService.analyzeSentiment(entry.getContent());

            tempEntry.setCompoundScore(
                    sentimentScores.get("compound") != null ? ((Number) sentimentScores.get("compound")).doubleValue() : 0.0
            );

            entryRepository.save(tempEntry);
        }
    }

    public Entry findById(int id) {
        return entryRepository.findById(id);
    }
}
