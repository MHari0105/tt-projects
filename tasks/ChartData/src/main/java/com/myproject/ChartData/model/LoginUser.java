package com.myproject.ChartData.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "login_user")
public class LoginUser {

    @Id
    private String id;
    private String username;
    private String password;

    private String loginTime;
    private String logoutTime;
    private String avgTime;

}
