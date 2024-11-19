package com.hoaiphong.carrental.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hoaiphong.carrental.dtos.user.RoleDTO;

public interface RoleService {
    List<RoleDTO> findAll();

    Page<RoleDTO> findAll(String keyword, Pageable pageable);

    RoleDTO findById(UUID id);

    RoleDTO create(RoleDTO roleDTO);

    RoleDTO update(UUID id, RoleDTO roleDTO);

    boolean deleteById(UUID id);

    RoleDTO findByName(String name);
}