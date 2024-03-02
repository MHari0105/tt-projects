package com.myproject.BackendApp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection ="polls")
public class Poll {
    
    @Id
    private String id;
    private String profileId;
    private String postId;
    private String pollvalue;

}
