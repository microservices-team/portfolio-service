package com.diegoanyosa.portfolioservice.controller;

import com.diegoanyosa.portfolioservice.dto.request.*;
import com.diegoanyosa.portfolioservice.dto.response.*;
import com.diegoanyosa.portfolioservice.service.PortfolioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    // ── Experience ─────────────────────────── PUBLIC (GET) / ADMIN (write)

    @GetMapping("/experience")
    public ResponseEntity<ApiResponse<List<ExperienceResponse>>> getAllExperiences() {
        return ResponseEntity.ok(ApiResponse.ok("OK", portfolioService.getAllExperiences()));
    }

    @GetMapping("/experience/{id}")
    public ResponseEntity<ApiResponse<ExperienceResponse>> getExperience(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.ok("OK", portfolioService.getExperienceById(id)));
    }

    @PostMapping("/experience")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ExperienceResponse>> createExperience(
            @Valid @RequestBody ExperienceRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.ok("Created", portfolioService.createExperience(req)));
    }

    @PutMapping("/experience/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ExperienceResponse>> updateExperience(
            @PathVariable UUID id, @Valid @RequestBody ExperienceRequest req) {
        return ResponseEntity.ok(ApiResponse.ok("Updated", portfolioService.updateExperience(id, req)));
    }

    @DeleteMapping("/experience/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteExperience(@PathVariable UUID id) {
        portfolioService.deleteExperience(id);
        return ResponseEntity.ok(ApiResponse.ok("Deleted", null));
    }

    // ── Skills ──────────────────────────────────────── PUBLIC

    @GetMapping("/skills")
    public ResponseEntity<ApiResponse<List<SkillResponse>>> getAllSkills() {
        return ResponseEntity.ok(ApiResponse.ok("OK", portfolioService.getAllSkills()));
    }

    // ── Projects ────────────────────────────── PUBLIC (GET) / ADMIN (write)

    @GetMapping("/projects")
    public ResponseEntity<ApiResponse<List<ProjectResponse>>> getAllProjects() {
        return ResponseEntity.ok(ApiResponse.ok("OK", portfolioService.getAllProjects()));
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ApiResponse<ProjectResponse>> getProject(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.ok("OK", portfolioService.getProjectById(id)));
    }

    @PostMapping("/projects")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ProjectResponse>> createProject(
            @Valid @RequestBody ProjectRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.ok("Created", portfolioService.createProject(req)));
    }

    @PutMapping("/projects/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ProjectResponse>> updateProject(
            @PathVariable UUID id, @Valid @RequestBody ProjectRequest req) {
        return ResponseEntity.ok(ApiResponse.ok("Updated", portfolioService.updateProject(id, req)));
    }

    @DeleteMapping("/projects/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteProject(@PathVariable UUID id) {
        portfolioService.deleteProject(id);
        return ResponseEntity.ok(ApiResponse.ok("Deleted", null));
    }

    // ── Certifications ──────────────────────────────── PUBLIC

    @GetMapping("/certifications")
    public ResponseEntity<ApiResponse<List<CertificationResponse>>> getAllCertifications() {
        return ResponseEntity.ok(ApiResponse.ok("OK", portfolioService.getAllCertifications()));
    }

    // ── Education ───────────────────────────────────── PUBLIC

    @GetMapping("/education")
    public ResponseEntity<ApiResponse<List<EducationResponse>>> getAllEducation() {
        return ResponseEntity.ok(ApiResponse.ok("OK", portfolioService.getAllEducation()));
    }
}
