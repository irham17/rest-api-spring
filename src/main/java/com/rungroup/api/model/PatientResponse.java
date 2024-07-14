package com.rungroup.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {

    private String id;

    private String nama;

    private LocalDate tanggalLahir;

    private String email;

    private String nomorTelp;

    private String diagnosa;

    private String resepObat;

    private String saranPerawatan;
}
