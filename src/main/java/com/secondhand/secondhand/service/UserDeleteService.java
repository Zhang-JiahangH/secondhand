package com.secondhand.secondhand.service;

import com.secondhand.secondhand.exception.UserNotExistException;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.model.UserRole;
import com.secondhand.secondhand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDeleteService {
    private UserRepository userRepository;

    @Autowired
    public UserDeleteService(UserRepository userRepository,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(String username, UserRole role) throws UserNotExistException {
        if (!userRepository.existsById(username)) {
            throw new UserNotExistException("User not exist");
        }
        User userFromDB = userRepository.findByUsername(username);
        userFromDB.setEnabled(false);
        userRepository.save(userFromDB);
    }
}
