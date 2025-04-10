package com.nhnacademy.nhnmartcustomerservice.domain;

import com.nhnacademy.nhnmartcustomerservice.domain.auth.Auth;
import lombok.NonNull;
import lombok.Value;

@Value
public class User {

    String id;
    String password;
    String name;

    Auth auth;

}
