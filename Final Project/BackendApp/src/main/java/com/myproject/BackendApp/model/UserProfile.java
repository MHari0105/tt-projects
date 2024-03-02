package com.myproject.BackendApp.model;

import java.util.Date;
import java.util.List;

import java.text.SimpleDateFormat;  

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
    private String userId;
    private String profilePic;
    private String username;
    private Role role;
    private Date dob;
    private Integer totalPosts;
    private List<Post> allPosts;

    public String getDobString() {
        if (dob == null)
            return null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(dob);
    }

}

    