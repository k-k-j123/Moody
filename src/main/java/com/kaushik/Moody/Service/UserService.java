package com.kaushik.Moody.Service;

import com.kaushik.Moody.Model.Users;
import com.kaushik.Moody.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(Users user){
        userRepository.save(user);
    }

    public Users getUser(int userId){
        return userRepository.findById(userId);
    }

    public void deleteUser(Users user){
        userRepository.delete(user);
    }

    public void updateUser(Users user){
        Users tempUser = userRepository.findById(user.getId());
        if(tempUser!=null){
            tempUser.setEmail(user.getEmail());
            tempUser.setName(user.getName());
            userRepository.save(tempUser);
        }
    }

    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
