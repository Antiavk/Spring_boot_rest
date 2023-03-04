package com.netology.authorization.service;


import com.netology.authorization.entity.Authorities;
import com.netology.authorization.entity.User;

import java.util.List;


public interface UserService {

    List<Authorities> getAuthorities(User user);


}
