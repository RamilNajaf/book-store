package com.ingressaca.rolestoretask.service;


import com.ingressaca.bookstoretask.dto.RoleDTO;
import com.ingressaca.bookstoretask.dto.mapper.RoleMapper;
import com.ingressaca.bookstoretask.entity.Role;
import com.ingressaca.bookstoretask.repository.RoleRepository;
import com.ingressaca.bookstoretask.service.GenericService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleService implements GenericService<RoleDTO,Role> {
    private  final RoleRepository roleRepository;
    private  final RoleMapper roleMapper;

    public RoleDTO findById(Long roleId){
        Role role = roleRepository.findById(roleId).orElseThrow();
        return roleMapper.toDto(role);
    }

    public List<RoleDTO> findAll(){
        return roleRepository.findAll().stream().map(role -> roleMapper.toDto(role)).collect(Collectors.toList());
    }
    public RoleDTO save(RoleDTO roleDTO){
        Role role  = roleMapper.toEntity(roleDTO);
        return roleMapper.toDto(roleRepository.save(role));
    }

    @Override
    public Optional<RoleDTO> update(Long id, RoleDTO dto) {
        return Optional
                .of(roleRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(
                        role->
                        {
                            roleMapper.updateModel(dto,role);
                            return  roleRepository.save(role);
                        }
                )
                .map(role -> roleMapper.toDto(role));
    }


    public void delete(Long roleId){
        roleRepository.deleteById(roleId);
    }
}
