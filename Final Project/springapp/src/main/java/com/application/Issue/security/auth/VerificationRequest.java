package com.application.Issue.security.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerificationRequest {

    private String userName;
    private String code;
}
