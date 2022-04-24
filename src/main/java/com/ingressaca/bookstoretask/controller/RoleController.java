package com.ingressaca.bookstoretask.controller;

import com.ingressaca.bookstoretask.dto.RoleDTO;
import com.ingressaca.bookstoretask.entity.Role;
import com.ingressaca.bookstoretask.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends GenericController<RoleDTO, Role> {
    public RoleController(RoleService roleService) {
        super(roleService);
    }
}