package com.myproject.BackendApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.BackendApp.service.PollService;

@RestController
@RequestMapping("/poll")
public class PollController {
    
    @Autowired
    private PollService pollService;

    @PostMapping("/{profileId}/{postId}")
    public ResponseEntity<String> addPoll(@PathVariable String profileId, @PathVariable String postId,  @RequestBody String pollValue) {
        return new ResponseEntity<>(pollService.addPoll(profileId, postId, pollValue), HttpStatus.OK);
    }
}
