package com.myproject.BackendApp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pincode_map")
public class PincodeMapping {
    
    @Id
    private String id;
    private Integer pincode;
    private List<UserProfile> higherOfficialId;
    private List<UserProfile> supervisorId;
    private List<UserProfile> workerId;
    private List<UserProfile> profileId;
}
