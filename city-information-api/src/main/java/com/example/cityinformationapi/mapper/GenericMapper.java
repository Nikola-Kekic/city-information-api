package com.example.cityinformationapi.mapper;

import com.example.cityinformationapi.dto.Dto;
import com.example.cityinformationapi.model.Entity;

public interface GenericMapper <E extends Entity, D extends Dto > {
    E toEntity(D dto);
    D toDto(E entity);
}
