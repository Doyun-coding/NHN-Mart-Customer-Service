package com.nhnacademy.nhnmartcustomerservice.domain.request;

import lombok.*;

@Value
@Getter
public class UserRequest {

    @NonNull
    String id;
    @NonNull
    String password;

}
