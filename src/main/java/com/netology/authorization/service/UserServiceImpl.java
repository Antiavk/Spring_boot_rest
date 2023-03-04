package com.netology.authorization.service;


import com.netology.authorization.entity.Authorities;
import com.netology.authorization.entity.User;
import com.netology.authorization.exceptions.InvalidCredentials;
import com.netology.authorization.exceptions.UnauthorizedUser;
import com.netology.authorization.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Authorities> getAuthorities(User user) {
        if (isEmpty(user.getName()) || isEmpty(user.getPassword())) {
            throw new InvalidCredentials("user or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user.getName(),
                user.getPassword());
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("unknown user " + user.getName());
        }
        return userAuthorities;
    }


    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }


    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }


}
