package com.nhnacademy.nhnmartcustomerservice.repository;

import com.nhnacademy.nhnmartcustomerservice.domain.User;

public interface UserRepository {

    boolean isExists(String id);
    boolean isMatches(String id, String password);

    void registerUser(User user);
    void updateUser(User user);
    void deleteUser(String id);

    User getUser(String id);

}
