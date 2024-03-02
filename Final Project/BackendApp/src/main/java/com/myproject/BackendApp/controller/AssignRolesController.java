package com.myproject.BackendApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.BackendApp.service.AssignRolesService;

@RestController
@RequestMapping("set-role")
public class AssignRolesController {
    
    @Autowired
    private AssignRolesService assignRoleService;

    @PostMapping("/{profileId}/higher-official")
    public ResponseEntity<String> addHigherOfficial(@PathVariable String profileId) {
        return new ResponseEntity<>(assignRoleService.addRoleHigherOfficial(profileId), HttpStatus.OK);
    }

    @PostMapping("/{profileId}/supervisor")
    public ResponseEntity<String> addSupervisor(@PathVariable String profileId) {
        return new ResponseEntity<>(assignRoleService.addRoleSupervisor(profileId), HttpStatus.OK);
    }

    @PostMapping("/{profileId}/worker")
    public ResponseEntity<String> addWorker(@PathVariable String profileId) {
        return new ResponseEntity<>(assignRoleService.addRoleWorker(profileId), HttpStatus.OK);
    }
}
