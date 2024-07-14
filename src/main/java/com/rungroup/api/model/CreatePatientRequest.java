package com.rungroup.api.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePatientRequest {

    @NotBlank
    @Size(max = 255)
    private String nama;

    @Past(message = "The birth date must be in the past")
    private LocalDate tanggalLahir;

    @NotBlank
    @Size(max = 255)
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Nomor telepon tidak sesuai")
    private String nomorTelp;

    private String diagnosa;

    private String resepObat;

    private String saranPerawatan;

}
