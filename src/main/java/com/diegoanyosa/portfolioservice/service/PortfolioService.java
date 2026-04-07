package com.diegoanyosa.portfolioservice.service;

import com.diegoanyosa.portfolioservice.exception.ResourceNotFoundException;
import com.diegoanyosa.portfolioservice.model.*;
import com.diegoanyosa.portfolioservice.api.model.*;
import com.diegoanyosa.portfolioservice.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 * Business logic layer.
 *
 * All method signatures use DTOs from the generated package
 * (com.diegoanyosa.portfolioservice.model.dto.*) which are produced
 * from portfolio-api.yaml by the openapi-generator-maven-plugin.
 */
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

    // ── Experience ────────────────────────────────────────────────────────

    public List<ExperienceDto> getAllExperiences() {
        return experienceRepository.findByActiveTrueOrderBySortOrderAsc()
                .stream().map(this::toExperienceDto).toList();
    }

    public ExperienceDto getExperienceById(UUID id) {
        return experienceRepository.findById(id)
                .map(this::toExperienceDto)
                .orElseThrow(() -> new ResourceNotFoundException("getExperienceById", id));
    }

    @Transactional
    public ExperienceDto createExperience(ExperienceRequest req) {
        Experience exp = Experience.builder()
                .company(req.getCompany())
                .role(req.getRole())
                .period(req.getPeriod())
                .location(req.getLocation())
                .descriptionEs(req.getDescriptionEs())
                .descriptionEn(req.getDescriptionEn())
                .highlightsEs(req.getHighlightsEs())
                .highlightsEn(req.getHighlightsEn())
                .stack(req.getStack())
                .sortOrder(req.getSortOrder() != null ? req.getSortOrder() : 0)
                .active(req.getActive() != null ? req.getActive() : true)
                .build();
        return toExperienceDto(experienceRepository.save(exp));
    }

    @Transactional
    public ExperienceDto updateExperience(UUID id, ExperienceRequest req) {
        Experience exp = experienceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("updateExperience", id));
        exp.setCompany(req.getCompany());
        exp.setRole(req.getRole());
        exp.setPeriod(req.getPeriod());
        exp.setLocation(req.getLocation());
        exp.setDescriptionEs(req.getDescriptionEs());
        exp.setDescriptionEn(req.getDescriptionEn());
        exp.setHighlightsEs(req.getHighlightsEs());
        exp.setHighlightsEn(req.getHighlightsEn());
        exp.setStack(req.getStack());
        if (req.getSortOrder() != null) exp.setSortOrder(req.getSortOrder());
        if (req.getActive()    != null) exp.setActive(req.getActive());
        return toExperienceDto(experienceRepository.save(exp));
    }

    @Transactional
    public void deleteExperience(UUID id) {
        Experience exp = experienceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("deleteExperience", id));
        exp.setActive(false);
        experienceRepository.save(exp);
    }

    // ── Skills ────────────────────────────────────────────────────────────

    public List<SkillDto> getAllSkills() {
        return skillRepository.findAllByOrderBySortOrderAsc()
                .stream().map(this::toSkillDto).toList();
    }

    // ── Projects ──────────────────────────────────────────────────────────

    public List<ProjectDto> getAllProjects() {
        return projectRepository.findByActiveTrueOrderBySortOrderAsc()
                .stream().map(this::toProjectDto).toList();
    }

    public ProjectDto getProjectById(UUID id) {
        return projectRepository.findById(id)
                .map(this::toProjectDto)
                .orElseThrow(() -> new ResourceNotFoundException("getProjectById", id));
    }

    @Transactional
    public ProjectDto createProject(ProjectRequest req) {
        Project p = Project.builder()
                .name(req.getName())
                .descriptionEs(req.getDescriptionEs())
                .descriptionEn(req.getDescriptionEn())
                .metrics(req.getMetrics())
                .stack(req.getStack())
                .featured(req.getFeatured() != null ? req.getFeatured() : false)
                .githubUrl(req.getGithubUrl().toString())
                .liveUrl(req.getLiveUrl().toString())
                .sortOrder(req.getSortOrder() != null ? req.getSortOrder() : 0)
                .active(req.getActive() != null ? req.getActive() : true)
                .build();
        return toProjectDto(projectRepository.save(p));
    }

    @Transactional
    public ProjectDto updateProject(UUID id, ProjectRequest req) {
        Project p = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("updateProject", id));
        p.setName(req.getName());
        p.setDescriptionEs(req.getDescriptionEs());
        p.setDescriptionEn(req.getDescriptionEn());
        p.setMetrics(req.getMetrics());
        p.setStack(req.getStack());
        if (req.getFeatured()   != null) p.setFeatured(req.getFeatured());
        p.setGithubUrl(req.getGithubUrl().toString());
        p.setLiveUrl(req.getLiveUrl().toString());
        if (req.getSortOrder()  != null) p.setSortOrder(req.getSortOrder());
        if (req.getActive()     != null) p.setActive(req.getActive());
        return toProjectDto(projectRepository.save(p));
    }

    @Transactional
    public void deleteProject(UUID id) {
        Project p = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("deleteProject", id));
        p.setActive(false);
        projectRepository.save(p);
    }

    // ── Certifications ────────────────────────────────────────────────────

    public List<CertificationDto> getAllCertifications() {
        return certificationRepository.findAllByOrderBySortOrderAsc()
                .stream().map(this::toCertificationDto).toList();
    }

    // ── Education ─────────────────────────────────────────────────────────

    public List<EducationDto> getAllEducation() {
        return educationRepository.findAll()
                .stream().map(this::toEducationDto).toList();
    }

    // ── Mappers ───────────────────────────────────────────────────────────

    private ExperienceDto toExperienceDto(Experience e) {
        ExperienceDto dto = new ExperienceDto();
        dto.setId(e.getId());
        dto.setCompany(e.getCompany());
        dto.setRole(e.getRole());
        dto.setPeriod(e.getPeriod());
        dto.setLocation(e.getLocation());
        dto.setDescriptionEs(e.getDescriptionEs());
        dto.setDescriptionEn(e.getDescriptionEn());
        dto.setHighlightsEs(e.getHighlightsEs());
        dto.setHighlightsEn(e.getHighlightsEn());
        dto.setStack(e.getStack());
        dto.setSortOrder(e.getSortOrder());
        return dto;
    }

    private SkillDto toSkillDto(Skill s) {
        SkillDto dto = new SkillDto();
        dto.setId(s.getId());
        dto.setCategoryEs(s.getCategoryEs());
        dto.setCategoryEn(s.getCategoryEn());
        dto.setIcon(s.getIcon());
        dto.setItems(s.getItems());
        dto.setSortOrder(s.getSortOrder());
        return dto;
    }

    private ProjectDto toProjectDto(Project p) {
        ProjectDto dto = new ProjectDto();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescriptionEs(p.getDescriptionEs());
        dto.setDescriptionEn(p.getDescriptionEn());
        dto.setMetrics(p.getMetrics());
        dto.setStack(p.getStack());
        dto.setFeatured(p.isFeatured());
        dto.setGithubUrl(URI.create(p.getGithubUrl()));
        dto.setLiveUrl(URI.create(p.getGithubUrl()));
        dto.setSortOrder(p.getSortOrder());
        return dto;
    }

    private CertificationDto toCertificationDto(Certification c) {
        CertificationDto dto = new CertificationDto();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setIssuer(c.getIssuer());
        dto.setYear(c.getYear());
        dto.setBadge(c.getBadge());
        dto.setSortOrder(c.getSortOrder());
        return dto;
    }

    private EducationDto toEducationDto(Education e) {
        EducationDto dto = new EducationDto();
        dto.setId(e.getId());
        dto.setInstitution(e.getInstitution());
        dto.setDegreeEs(e.getDegreeEs());
        dto.setDegreeEn(e.getDegreeEn());
        dto.setPeriod(e.getPeriod());
        dto.setLocation(e.getLocation());
        return dto;
    }
}
