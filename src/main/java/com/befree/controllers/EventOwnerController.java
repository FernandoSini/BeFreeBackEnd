package com.befree.controllers;

import com.befree.data.model.vo.EventOwnerVO;
import com.befree.services.EventOwnerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventowner")
public class EventOwnerController {

    @Autowired
    private EventOwnerServices services;

    @GetMapping(value = "/all",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<List<EventOwnerVO>> getallOwners() {
        List<EventOwnerVO> ownersList = services.getAllOwners();
        return ResponseEntity.ok(ownersList);
    }

    @PutMapping(value = "/updateOwner/{ownerId}",
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<EventOwnerVO> updateEventOwner(@PathVariable("ownerId") String ownerId, @RequestBody EventOwnerVO ownerVO){
       EventOwnerVO owner = services.updateOwner(ownerVO);

        return ResponseEntity.ok().body(owner);
    }

}
