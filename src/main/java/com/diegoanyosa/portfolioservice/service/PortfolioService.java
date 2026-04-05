package com.diegoanyosa.portfolioservice.service;

import com.diegoanyosa.portfolioservice.dto.request.*;
import com.diegoanyosa.portfolioservice.dto.response.*;
import com.diegoanyosa.portfolioservice.exception.ResourceNotFoundException;
import com.diegoanyosa.portfolioservice.model.*;
import com.diegoanyosa.portfolioservice.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PortfolioService {

    private final ExperienceRepository    experienceRepository;
    private final SkillRepository         skillRepository;
    private final ProjectRepository       projectRepository;
    private final CertificationRepository certificationRepository;
    private final EducationRepository     educationRepository;

    // ── Experience ───────────────────────────────────────────

    public List<ExperienceResponse> getAllExperiences() {
        return experienceRepository.findByActiveTrueOrderBySortOrderAsc()
            .stream().map(this::toExperienceResponse).toList();
    }

    public ExperienceResponse getExperienceById(UUID id) {
        return experienceRepository.findById(id)
            .map(this::toExperienceResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Experience", id));
    }

    @Transactional
    public ExperienceResponse createExperience(ExperienceRequest req) {
        Experience exp = Experience.builder()
            .company(req.getCompany()).role(req.getRole())
            .period(req.getPeriod()).location(req.getLocation())
            .descriptionEs(req.getDescriptionEs()).descriptionEn(req.getDescriptionEn())
            .highlightsEs(req.getHighlightsEs()).highlightsEn(req.getHighlightsEn())
            .stack(req.getStack()).sortOrder(req.getSortOrder()).active(req.isActive())
            .build();
        return toExperienceResponse(experienceRepository.save(exp));
    }

    @Transactional
    public ExperienceResponse updateExperience(UUID id, ExperienceRequest req) {
        Experience exp = experienceRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Experience", id));
        exp.setCompany(req.getCompany()); exp.setRole(req.getRole());
        exp.setPeriod(req.getPeriod());   exp.setLocation(req.getLocation());
        exp.setDescriptionEs(req.getDescriptionEs()); exp.setDescriptionEn(req.getDescriptionEn());
        exp.setHighlightsEs(req.getHighlightsEs());   exp.setHighlightsEn(req.getHighlightsEn());
        exp.setStack(req.getStack());     exp.setSortOrder(req.getSortOrder());
        exp.setActive(req.isActive());
        return toExperienceResponse(experienceRepository.save(exp));
    }

    @Transactional
    public void deleteExperience(UUID id) {
        Experience exp = experienceRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Experience", id));
        exp.setActive(false); // soft delete
        experienceRepository.save(exp);
    }

    // ── Skills ───────────────────────────────────────────────

    public List<SkillResponse> getAllSkills() {
        return skillRepository.findAllByOrderBySortOrderAsc()
            .stream().map(this::toSkillResponse).toList();
    }

    // ── Projects ─────────────────────────────────────────────

    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findByActiveTrueOrderBySortOrderAsc()
            .stream().map(this::toProjectResponse).toList();
    }

    public ProjectResponse getProjectById(UUID id) {
        return projectRepository.findById(id)
            .map(this::toProjectResponse)
            .orElseThrow(() -> new ResourceNotFoundException("Project", id));
    }

    @Transactional
    public ProjectResponse createProject(ProjectRequest req) {
        Project p = Project.builder()
            .name(req.getName())
            .descriptionEs(req.getDescriptionEs()).descriptionEn(req.getDescriptionEn())
            .metrics(req.getMetrics()).stack(req.getStack()).featured(req.isFeatured())
            .githubUrl(req.getGithubUrl()).liveUrl(req.getLiveUrl())
            .sortOrder(req.getSortOrder()).active(req.isActive())
            .build();
        return toProjectResponse(projectRepository.save(p));
    }

    @Transactional
    public ProjectResponse updateProject(UUID id, ProjectRequest req) {
        Project p = projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project", id));
        p.setName(req.getName());
        p.setDescriptionEs(req.getDescriptionEs()); p.setDescriptionEn(req.getDescriptionEn());
        p.setMetrics(req.getMetrics()); p.setStack(req.getStack()); p.setFeatured(req.isFeatured());
        p.setGithubUrl(req.getGithubUrl()); p.setLiveUrl(req.getLiveUrl());
        p.setSortOrder(req.getSortOrder()); p.setActive(req.isActive());
        return toProjectResponse(projectRepository.save(p));
    }

    @Transactional
    public void deleteProject(UUID id) {
        Project p = projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project", id));
        p.setActive(false);
        projectRepository.save(p);
    }

    // ── Certifications ───────────────────────────────────────

    public List<CertificationResponse> getAllCertifications() {
        return certificationRepository.findAllByOrderBySortOrderAsc()
            .stream().map(this::toCertificationResponse).toList();
    }

    // ── Education ────────────────────────────────────────────

    public List<EducationResponse> getAllEducation() {
        return educationRepository.findAll()
            .stream().map(this::toEducationResponse).toList();
    }

    // ── Mappers ──────────────────────────────────────────────

    private ExperienceResponse toExperienceResponse(Experience e) {
        return ExperienceResponse.builder()
            .id(e.getId()).company(e.getCompany()).role(e.getRole())
            .period(e.getPeriod()).location(e.getLocation())
            .descriptionEs(e.getDescriptionEs()).descriptionEn(e.getDescriptionEn())
            .highlightsEs(e.getHighlightsEs()).highlightsEn(e.getHighlightsEn())
            .stack(e.getStack()).sortOrder(e.getSortOrder()).build();
    }

    private SkillResponse toSkillResponse(Skill s) {
        return SkillResponse.builder()
            .id(s.getId()).categoryEs(s.getCategoryEs()).categoryEn(s.getCategoryEn())
            .icon(s.getIcon()).items(s.getItems()).sortOrder(s.getSortOrder()).build();
    }

    private ProjectResponse toProjectResponse(Project p) {
        return ProjectResponse.builder()
            .id(p.getId()).name(p.getName())
            .descriptionEs(p.getDescriptionEs()).descriptionEn(p.getDescriptionEn())
            .metrics(p.getMetrics()).stack(p.getStack()).featured(p.isFeatured())
            .githubUrl(p.getGithubUrl()).liveUrl(p.getLiveUrl()).sortOrder(p.getSortOrder()).build();
    }

    private CertificationResponse toCertificationResponse(Certification c) {
        return CertificationResponse.builder()
            .id(c.getId()).name(c.getName()).issuer(c.getIssuer())
            .year(c.getYear()).badge(c.getBadge()).sortOrder(c.getSortOrder()).build();
    }

    private EducationResponse toEducationResponse(Education e) {
        return EducationResponse.builder()
            .id(e.getId()).institution(e.getInstitution())
            .degreeEs(e.getDegreeEs()).degreeEn(e.getDegreeEn())
            .period(e.getPeriod()).location(e.getLocation()).build();
    }
}
