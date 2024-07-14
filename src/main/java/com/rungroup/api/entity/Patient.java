package com.rungroup.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    private String id;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "tanggal_lahir", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate tanggalLahir;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nomor_telp", nullable = false)
    private String nomorTelp;

    @Column(name = "diagnosa", columnDefinition = "TEXT")
    private String diagnosa;

    @Column(name = "resep_obat", columnDefinition = "TEXT")
    private String resepObat;

    @Column(name = "saran_perawatan", columnDefinition = "TEXT")
    private String saranPerawatan;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
}
