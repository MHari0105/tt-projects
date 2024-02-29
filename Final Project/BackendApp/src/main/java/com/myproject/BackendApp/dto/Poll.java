package com.myproject.BackendApp.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "poll")
public class Poll {

    @Id
    private String id;
    private String pollType;        // LOW, MODERATE, HIGH

}