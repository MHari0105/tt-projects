package com.myproject.BackendApp.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.myproject.BackendApp.security.user.Role;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_profile")
public class UserProfile {
    
    @Id
    private String id;
    private String profilePic;
    private String username;
    private Role role;
    private Date dob;
    private Integer totalPosts = 0;
    private List<String> myPosts;

}
