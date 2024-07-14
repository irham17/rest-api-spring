package com.rungroup.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rungroup.api.entity.Patient;
import com.rungroup.api.entity.User;
import com.rungroup.api.model.CreatePatientRequest;
import com.rungroup.api.model.PatientResponse;
import com.rungroup.api.model.WebResponse;
import com.rungroup.api.repository.PatientRepository;
import com.rungroup.api.repository.UserRepository;
import com.rungroup.api.security.BCrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        patientRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setUsername("test");
        user.setPassword(BCrypt.hashpw("test", BCrypt.gensalt()));
        user.setName("Test");
        user.setToken("test");
        user.setTokenExpiredAt(System.currentTimeMillis() + 1000000);
        userRepository.save(user);
    }

    @Test
    void createPatientBadRequest() throws Exception {
        CreatePatientRequest request = new CreatePatientRequest();
        request.setNama("");
        request.setEmail("salah");

        mockMvc.perform(
                post("/api/patient")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void createPatientSuccess() throws Exception {
        CreatePatientRequest request = new CreatePatientRequest();
        request.setNama("Irham");
        request.setTanggalLahir(LocalDate.parse("2002-04-17"));
        request.setEmail("irham@example.com");
        request.setNomorTelp("081315244955");

        mockMvc.perform(
                post("/api/patient")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<PatientResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals("Irham", response.getData().getNama());
            assertEquals("irham@example.com", response.getData().getEmail());
            assertEquals(LocalDate.parse("2002-04-17"), response.getData().getTanggalLahir());
            assertEquals("081315244955", response.getData().getNomorTelp());

            assertTrue(patientRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void getPatientNotFound() throws Exception {
        mockMvc.perform(
                get("/api/patient/20202020020202")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void getPatientSuccess() throws Exception {
        User user = userRepository.findById("test").orElseThrow();

        Patient patient = new Patient();
        patient.setId(UUID.randomUUID().toString());
        patient.setUser(user);
        patient.setNama("Irham");
        patient.setTanggalLahir(LocalDate.parse("2002-04-17"));
        patient.setEmail("irham@example.com");
        patient.setNomorTelp("081315244955");
        patientRepository.save(patient);

        mockMvc.perform(
                get("/api/patient/" + patient.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<PatientResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());

            assertEquals(patient.getId(), response.getData().getId());
            assertEquals(patient.getNama(), response.getData().getNama());
            assertEquals(patient.getTanggalLahir(), response.getData().getTanggalLahir());
            assertEquals(patient.getEmail(), response.getData().getEmail());
            assertEquals(patient.getNomorTelp(), response.getData().getNomorTelp());
        });
    }
}
