package com.myproject.BackendApp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.myproject.BackendApp.dto.Poll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "issued_posts")
public class AllPosts {
    
    @Id
    private String id;
    private String postId;
    private String profileId;
    private Integer dangerPercentage;
    private Integer moderatePercentage;
    private Integer lowPercentage;
    private Poll totalPollCounts;
    private String postStatus;
    
}
