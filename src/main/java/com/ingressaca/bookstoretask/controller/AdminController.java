package com.ingressaca.bookstoretask.controller;

import com.ingressaca.bookstoretask.dto.AppUserDTO;
import com.ingressaca.bookstoretask.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PutMapping("/{id}/add_publisher_role")
    public ResponseEntity<AppUserDTO> addPublisherRoleToUser(@PathVariable Long id) {
        adminService.addPublisherRoleToUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUserDTO>> findAllUsers() {
        return ResponseEntity.ok(adminService.findAllUsers());
    }


}
