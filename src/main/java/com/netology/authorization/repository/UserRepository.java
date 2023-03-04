package com.netology.authorization.repository;


import com.netology.authorization.entity.Authorities;

import java.util.List;


public interface UserRepository {

    List<Authorities> getUserAuthorities(String user, String password);
}
