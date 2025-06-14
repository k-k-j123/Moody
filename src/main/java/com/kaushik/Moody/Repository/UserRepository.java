package com.kaushik.Moody.Repository;

import com.kaushik.Moody.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

    Users findById(int id);
    Users findByEmail(String email);
    List<Users> findAll();
}
