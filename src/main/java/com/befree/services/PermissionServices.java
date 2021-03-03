package com.befree.services;

import com.befree.data.model.Permission;
import com.befree.exceptions.ResourceNotFoundException;
import com.befree.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServices {

     @Autowired
    private PermissionRepository permissionRepository;

     public Permission getPermissionById(long id){
         var permissionEntity = permissionRepository.findById(id)
                 .orElseThrow(()->new ResourceNotFoundException("Not found"));
         return permissionEntity ;
     }
}
