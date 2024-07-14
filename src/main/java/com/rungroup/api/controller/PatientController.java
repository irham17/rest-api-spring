package com.rungroup.api.controller;

import com.rungroup.api.entity.User;
import com.rungroup.api.model.CreatePatientRequest;
import com.rungroup.api.model.PatientResponse;
import com.rungroup.api.model.WebResponse;
import com.rungroup.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping(
            path = "/api/patient",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<PatientResponse> create(User user, @RequestBody CreatePatientRequest request) {
        PatientResponse patientResponse = patientService.create(user, request);
        return WebResponse.<PatientResponse>builder().data(patientResponse).build();
    }

    @GetMapping(
            path = "/api/patient/{patientId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<PatientResponse> get(User user, @PathVariable("patientId") String patientId) {
        PatientResponse patientResponse = patientService.get(user, patientId);
        return WebResponse.<PatientResponse>builder().data(patientResponse).build();
    }
}
