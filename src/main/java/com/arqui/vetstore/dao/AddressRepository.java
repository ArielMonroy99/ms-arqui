package com.arqui.vetstore.dao;

import com.arqui.vetstore.dto.AddressEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

    Page<AddressEntity> findAllByUserIdAndStatus(Pageable pageable, Integer userId, Integer status);

}
