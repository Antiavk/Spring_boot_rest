package com.netology.authorization.repository;


import com.netology.authorization.entity.Authorities;
import com.netology.authorization.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    List<User> users = new ArrayList<>();
    {users.add(new User("admin", "123"));
    users.add(new User("Ivan", "1234"));
    }


    public List<Authorities> getUserAuthorities(String name, String password) {
        List<Authorities> authorities = new ArrayList<>();
        for (User user : users) {
           if (user.getName().equals(name) && user.getPassword().equals(password)){
               if (user.getName().equals("admin") && user.getPassword().equals("123")){
                   Collections.addAll(authorities, Authorities.READ, Authorities.WRITE,
                           Authorities.DELETE);
               } else {
                   Collections.addAll(authorities, Authorities.READ, Authorities.WRITE);
               }
           }
        } return authorities;

    }

}
