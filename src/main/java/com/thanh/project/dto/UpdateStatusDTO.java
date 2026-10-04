package com.thanh.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class UpdateStatusDTO {

    @NotBlank(message = "status is required")
    private String status;
}
