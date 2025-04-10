package com.nhnacademy.nhnmartcustomerservice.service.impl;

import com.nhnacademy.nhnmartcustomerservice.domain.User;
import com.nhnacademy.nhnmartcustomerservice.repository.UserRepository;
import com.nhnacademy.nhnmartcustomerservice.repository.impl.UserRepositoryImpl;
import com.nhnacademy.nhnmartcustomerservice.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserServiceImpl implements UserService {
    private final static UserRepository repository = new UserRepositoryImpl();

    @Override
    public boolean isExists(String id) {
        if(Objects.isNull(id)) {
            throw new IllegalArgumentException();
        }

        return repository.isExists(id);
    }

    @Override
    public boolean isMatches(String id, String password) {
        if(!isExists(id) || Objects.isNull(password)) return false;

        return repository.isMatches(id, password);
    }

    @Override
    public void registerUser(User user) {
        if(isExists(user.getId())) {
            return;
        }

        repository.registerUser(user);
    }

    @Override
    public void updateUser(User user) {
        if(!isExists(user.getId())) {
            return;
        }

        repository.updateUser(user);
    }

    @Override
    public void deleteUser(String id) {
        if(!isExists(id)) {
            return;
        }

        repository.deleteUser(id);
    }

    @Override
    public User getUser(String id) {
        if(!isExists(id)) {
            return null;
        }

        return repository.getUser(id);
    }
}
