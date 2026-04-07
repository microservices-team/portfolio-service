package com.diegoanyosa.portfolioservice.controller;

import com.diegoanyosa.portfolioservice.api.PortfolioApiDelegate;
import com.diegoanyosa.portfolioservice.api.model.*;
import com.diegoanyosa.portfolioservice.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Implements the generated {@link PortfolioApiDelegate}.
 *
 * The openapi-generator-maven-plugin reads portfolio-api.yaml and produces:
 *   • PortfolioApi          – the @RequestMapping interface (wired by Spring MVC)
 *   • PortfolioApiDelegate  – the delegate interface you implement here
 *   • All DTO classes under model.dto.*
 *
 * Security (@PreAuthorize) is enforced here rather than in the YAML so that
 * Spring Security's method-level security (enabled in SecurityConfig) applies.
 */
@Component
@RequiredArgsConstructor
public class PortfolioController implements PortfolioApiDelegate {

    private final PortfolioService portfolioService;

    // ── Experience ────────────────────────────────────────────────────────

    @Override
    public ResponseEntity<ExperienceListResponse> getAllExperiences() {
        ExperienceListResponse body = new ExperienceListResponse();
        body.setSuccess(true);
        body.setMessage("OK");
        body.setData(portfolioService.getAllExperiences());
        return ResponseEntity.ok(body);
    }

    @Override
    public ResponseEntity<ExperienceSingleResponse> getExperienceById(UUID id) {
        ExperienceSingleResponse body = new ExperienceSingleResponse();
        body.setSuccess(true);
        body.setMessage("OK");
        body.setData(portfolioService.getExperienceById(id));
        return ResponseEntity.ok(body);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExperienceSingleResponse> createExperience(ExperienceRequest req) {
        ExperienceSingleResponse body = new ExperienceSingleResponse();
        body.setSuccess(true);
        body.setMessage("Created");
        body.setData(portfolioService.createExperience(req));
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExperienceSingleResponse> updateExperience(UUID id, ExperienceRequest req) {
        ExperienceSingleResponse body = new ExperienceSingleResponse();
        body.setSuccess(true);
        body.setMessage("Updated");
        body.setData(portfolioService.updateExperience(id, req));
        return ResponseEntity.ok(body);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VoidResponse> deleteExperience(UUID id) {
        portfolioService.deleteExperience(id);
        VoidResponse body = new VoidResponse();
        body.setSuccess(true);
        body.setMessage("Deleted");
        return ResponseEntity.ok(body);
    }

    // ── Skills ────────────────────────────────────────────────────────────

    @Override
    public ResponseEntity<SkillListResponse> getAllSkills() {
        SkillListResponse body = new SkillListResponse();
        body.setSuccess(true);
        body.setMessage("OK");
        body.setData(portfolioService.getAllSkills());
        return ResponseEntity.ok(body);
    }

    // ── Projects ──────────────────────────────────────────────────────────

    @Override
    public ResponseEntity<ProjectListResponse> getAllProjects() {
        ProjectListResponse body = new ProjectListResponse();
        body.setSuccess(true);
        body.setMessage("OK");
        body.setData(portfolioService.getAllProjects());
        return ResponseEntity.ok(body);
    }

    @Override
    public ResponseEntity<ProjectSingleResponse> getProjectById(UUID id) {
        ProjectSingleResponse body = new ProjectSingleResponse();
        body.setSuccess(true);
        body.setMessage("OK");
        body.setData(portfolioService.getProjectById(id));
        return ResponseEntity.ok(body);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectSingleResponse> createProject(ProjectRequest req) {
        ProjectSingleResponse body = new ProjectSingleResponse();
        body.setSuccess(true);
        body.setMessage("Created");
        body.setData(portfolioService.createProject(req));
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectSingleResponse> updateProject(UUID id, ProjectRequest req) {
        ProjectSingleResponse body = new ProjectSingleResponse();
        body.setSuccess(true);
        body.setMessage("Updated");
        body.setData(portfolioService.updateProject(id, req));
        return ResponseEntity.ok(body);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VoidResponse> deleteProject(UUID id) {
        portfolioService.deleteProject(id);
        VoidResponse body = new VoidResponse();
        body.setSuccess(true);
        body.setMessage("Deleted");
        return ResponseEntity.ok(body);
    }

    // ── Certifications ────────────────────────────────────────────────────

    @Override
    public ResponseEntity<CertificationListResponse> getAllCertifications() {
        CertificationListResponse body = new CertificationListResponse();
        body.setSuccess(true);
        body.setMessage("OK");
        body.setData(portfolioService.getAllCertifications());
        return ResponseEntity.ok(body);
    }

    // ── Education ─────────────────────────────────────────────────────────

    @Override
    public ResponseEntity<EducationListResponse> getAllEducation() {
        EducationListResponse body = new EducationListResponse();
        body.setSuccess(true);
        body.setMessage("OK");
        body.setData(portfolioService.getAllEducation());
        return ResponseEntity.ok(body);
    }
}
