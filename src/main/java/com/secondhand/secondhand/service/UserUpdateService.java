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

/**
 * Service to update user
 */
@Service
public class UserUpdateService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;



    @Autowired
    public UserUpdateService(UserRepository userRepository,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void update(String username, User user, UserRole role) throws UserNotExistException {
        if (!userRepository.existsById(username)) {
            throw new UserNotExistException("User not exist");
        }
        User userFromDB = userRepository.findByUsername(username);

        if (user.getPassword() != null) {
            userFromDB.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if (user.getFirstName() != null) {
            userFromDB.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null) {
            userFromDB.setLastName(user.getLastName());
        }

        if (user.getEmail() != null) {
            userFromDB.setEmail(user.getEmail());
        }

        if (user.getPhone() != null) {
            userFromDB.setPhone(user.getPhone());
        }

        userRepository.save(userFromDB);

    }
}
