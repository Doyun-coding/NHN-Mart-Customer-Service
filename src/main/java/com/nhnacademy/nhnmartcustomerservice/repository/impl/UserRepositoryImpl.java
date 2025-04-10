package com.nhnacademy.nhnmartcustomerservice.repository.impl;

import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.domain.auth.Auth;
import com.nhnacademy.nhnmartcustomerservice.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepositoryImpl implements UserRepository {
    private static final Map<String, User> repository = new HashMap<>(
            Map.of(
                    "admin", new User("admin", "1234", "admin", Auth.ADMIN),
                    "nhn1", new User("nhn1", "1234", "nhn1", Auth.USER),
                    "nhn2", new User("nhn2", "1234", "nhn2", Auth.USER)
            )
    );

    @Override
    public boolean isExists(String id) {
        return repository.containsKey(id);
    }

    @Override
    public boolean isMatches(String id, String password) {
        User user = repository.get(id);

        return user.getPassword().equals(password);
    }

    @Override
    public void registerUser(User user) {
        repository.put(user.getId(), user);
    }

    @Override
    public void updateUser(User user) {
        repository.replace(user.getId(), user);
    }

    @Override
    public void deleteUser(String id) {
        repository.remove(id);
    }

    @Override
    public User getUser(String id) {
        return repository.get(id);
    }
}
