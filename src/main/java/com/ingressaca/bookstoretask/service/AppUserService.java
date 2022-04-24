package com.ingressaca.bookstoretask.service;

import com.ingressaca.bookstoretask.dto.AppUserDTO;
import com.ingressaca.bookstoretask.entity.AppUser;
import com.ingressaca.bookstoretask.mapper.AppUserMapper;
import com.ingressaca.bookstoretask.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppUserService implements GenericService<AppUserDTO,AppUser> {
    private  final AppUserRepository appUserRepository;
    private  final AppUserMapper appUserMapper;


    public AppUserDTO findById(Long appUserId){
        AppUser appUser = appUserRepository.findById(appUserId).orElseThrow();
        return appUserMapper.toDto(appUser);
    }

    public List<AppUserDTO> findAll(){
        return appUserRepository.findAll().stream().map(appUser -> appUserMapper.toDto(appUser)).collect(Collectors.toList());
    }
    public AppUserDTO save(AppUserDTO appUserDTO){
        AppUser appUser  = appUserMapper.toEntity(appUserDTO);
        return appUserMapper.toDto(appUserRepository.save(appUser));
    }

    @Override
    public Optional<AppUserDTO> update(Long id, AppUserDTO dto) {
        return Optional
                .of(appUserRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(
                       appUser ->
                        {
                            appUserMapper.updateModel(dto,appUser);
                            return appUserRepository.save(appUser);
                        }
                )
                .map(appUser -> appUserMapper.toDto(appUser));
    }


    public void delete(Long appUserId){
        appUserRepository.deleteById(appUserId);
    }
}
