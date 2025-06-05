package com.example.demo.mapper;


import com.example.demo.dto.ServAdicionalDTO;
import com.example.demo.model.ServAdicional;


public interface ServAdicionalMapper {
        ServAdicional toEntity(ServAdicionalDTO dto);
        ServAdicionalDTO toDTO(ServAdicional entity);
    }

