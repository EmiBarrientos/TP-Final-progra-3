package com.example.demo.mapper;

import com.example.demo.dto.CostoServAdicionalDTO;
import com.example.demo.model.CostoServAdicional;


    public interface CostoServAdicionalMapper {
        CostoServAdicional toEntity(CostoServAdicionalDTO dto);
        CostoServAdicionalDTO toDTO(CostoServAdicional entity);
    }

