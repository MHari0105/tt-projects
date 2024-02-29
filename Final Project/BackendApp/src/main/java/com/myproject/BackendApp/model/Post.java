package com.myproject.BackendApp.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
// import org.springframework.data.mongodb.core.mapping.DocumentReference;

// import com.myproject.BackendApp.security.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "posts")
public class Post {
    
    @Id
    private String id;
    private List<String> postImage;
    private String creatorId;
    private String description;
    private Date dateTime;
    private String level;                           // LOW, MEDIUM, HIGH
    private String status = "pending";              // PENDING, IN PROGRESS, RESOLVED
    private Integer commentCount = 0;
    private Integer pollCount;

}
