package com.arqui.vetstore.dao;

import com.arqui.vetstore.dto.RoleName;
import com.arqui.vetstore.dto.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByRole(RoleName role);

}
