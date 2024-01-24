package com.revature.RevSpeed.services;

import com.revature.RevSpeed.models.UserServiceLink;
import com.revature.RevSpeed.repositorys.UserServiceLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceLinkService {
    private final UserServiceLinkRepository userServiceLinkRepository;

    @Autowired
    public UserServiceLinkService(UserServiceLinkRepository userServiceLinkRepository) {
        this.userServiceLinkRepository = userServiceLinkRepository;
    }

    public UserServiceLink saveUserServiceLink(UserServiceLink userServiceLink) {
        // You can add any business logic or validation before saving
        return userServiceLinkRepository.save(userServiceLink);
    }
}
