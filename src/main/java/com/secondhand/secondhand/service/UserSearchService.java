package com.secondhand.secondhand.service;

import com.secondhand.secondhand.exception.UserNotExistException;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSearchService {
    private UserRepository userRepository;

    @Autowired
    public UserSearchService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public User search(String username) throws UserNotExistException {
        if (!userRepository.existsById(username)) {
            throw new UserNotExistException("User Doesn't Exist");
        }

        User user = userRepository.findByUsername(username);
        if (!user.isEnabled()) {
            throw new UserNotExistException("User Is Disabled");
        }
        return user;
    }
}
