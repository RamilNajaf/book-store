package com.ingressaca.bookstoretask.service;

import com.ingressaca.bookstoretask.dto.AppUserDTO;
import com.ingressaca.bookstoretask.entity.AppUser;
import com.ingressaca.bookstoretask.mapper.AppUserMapper;
import com.ingressaca.bookstoretask.repository.AppUserRepository;
import com.ingressaca.bookstoretask.security.model.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AdminServiceTest {

    private AppUserRepository appUserRepository;
    private AppUserMapper appUserMapper;
    private AdminService adminService;

    @BeforeEach
    public void setUp() {
        appUserRepository = mock(AppUserRepository.class);
        appUserMapper = mock(AppUserMapper.class);
        adminService = new AdminService(appUserRepository, appUserMapper);
    }


    @Test
    public void testAddPublisherRoleToUser() {
        AppUser appUser = new AppUser();
        appUser.setRoles("");

        when(appUserRepository.findById(any())).thenReturn(Optional.of(appUser));
        adminService.addPublisherRoleToUser(any());

        assertEquals(appUser.getRoleList(), List.of(Roles.PUBLISHER));
    }

    @Test
    public void testAddPublisherRoleToUserWhenAlreadyPublisher() {
        AppUser appUser = new AppUser();
        appUser.setRoles(Roles.PUBLISHER);

        when(appUserRepository.findById(any())).thenReturn(Optional.of(appUser));
        adminService.addPublisherRoleToUser(any());

        verify(appUserRepository, times(0)).save(appUser);
    }


    @Test
    public void testFindAllUsers() {
        List<AppUser> appUsers = new ArrayList<>();

        IntStream stream = IntStream.range(1, 3);
        stream.forEach(range -> {
            appUsers.add(new AppUser());
        });

        List<AppUserDTO> expected = appUsers.stream().map(appUserMapper::toDto).collect(Collectors.toList());
        when(appUserRepository.findAll()).thenReturn(appUsers);

        List<AppUserDTO> result = adminService.findAllUsers();
        assertEquals(result, expected);
        verify(appUserRepository,  times(1)).findAll();
        verifyNoMoreInteractions(appUserRepository);
    }
}
