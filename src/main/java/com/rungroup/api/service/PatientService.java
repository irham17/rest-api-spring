package com.rungroup.api.service;

import com.rungroup.api.entity.Patient;
import com.rungroup.api.entity.User;
import com.rungroup.api.model.CreatePatientRequest;
import com.rungroup.api.model.PatientResponse;
import com.rungroup.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public PatientResponse create(User user, CreatePatientRequest request) {
        validationService.validate(request);

        Patient patient = new Patient();
        patient.setId(UUID.randomUUID().toString());
        patient.setNama(request.getNama());
        patient.setTanggalLahir(request.getTanggalLahir());
        patient.setEmail(request.getEmail());
        patient.setNomorTelp(request.getNomorTelp());
        patient.setDiagnosa(request.getDiagnosa());
        patient.setResepObat(request.getResepObat());
        patient.setSaranPerawatan(request.getSaranPerawatan());
        patient.setUser(user);


        patientRepository.save(patient);

        return toPatientResponse(patient);
    }

    private PatientResponse toPatientResponse(Patient patient) {
        return PatientResponse.builder()
                .id(patient.getId())
                .nama(patient.getNama())
                .tanggalLahir(patient.getTanggalLahir())
                .email(patient.getEmail())
                .nomorTelp(patient.getNomorTelp())
                .diagnosa(patient.getDiagnosa())
                .resepObat(patient.getResepObat())
                .saranPerawatan(patient.getSaranPerawatan())
                .build();
    }

    @Transactional(readOnly = true)
    public PatientResponse get(User user, String id) {
        Patient patient = patientRepository.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pasien tidak ditemukan"));

        return toPatientResponse(patient);
    }

}
