package com.kaushik.Moody.Repository;

import com.kaushik.Moody.Model.Entry;
import com.kaushik.Moody.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry,Integer> {

    Entry findById(int id);
    List<Entry> findByUser(Users user);
}
