package com.nexflix.clone.com.nexflix.clone.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private String email, password, fullName, role;
    private Boolean active;
}
