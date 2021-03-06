package com.sombre.shop.models.pojo.dto.adminDto.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Created by inna on 15.02.17.
 */
@Data
@AllArgsConstructor
public class UpdateAdminDto {
    private UUID uniqueid;
    private int degree;
    private String roledescription;
}
