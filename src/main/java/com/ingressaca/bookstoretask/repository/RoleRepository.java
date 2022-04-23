package com.ingressaca.bookstoretask.repository;


import com.ingressaca.bookstoretask.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
