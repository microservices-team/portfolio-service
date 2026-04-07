package com.diegoanyosa.portfolioservice.service;

import com.diegoanyosa.portfolioservice.model.*;
import com.diegoanyosa.portfolioservice.model.dto.*;
import com.diegoanyosa.portfolioservice.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceTest {

    @Mock private ExperienceRepository    experienceRepository;
    @Mock private SkillRepository         skillRepository;
    @Mock private ProjectRepository       projectRepository;
    @Mock private CertificationRepository certificationRepository;
    @Mock private EducationRepository     educationRepository;

    @InjectMocks
    private PortfolioService portfolioService;

    @Test
    @DisplayName("getAllExperiences returns only active experiences ordered by sortOrder")
    void getAllExperiences_ReturnsActiveOnly() {
        Experience exp1 = Experience.builder()
                .id(UUID.randomUUID()).company("NTTData").role("Senior Engineer")
                .descriptionEs("Desc ES").descriptionEn("Desc EN")
                .stack(List.of("Java", "Spring")).highlightsEs(List.of("h1"))
                .highlightsEn(List.of("h1 en")).sortOrder(1).active(true).build();

        Experience exp2 = Experience.builder()
                .id(UUID.randomUUID()).company("Globant").role("Developer")
                .descriptionEs("Desc ES 2").descriptionEn("Desc EN 2")
                .stack(List.of("React")).highlightsEs(List.of("h2"))
                .highlightsEn(List.of("h2 en")).sortOrder(2).active(true).build();

        when(experienceRepository.findByActiveTrueOrderBySortOrderAsc())
                .thenReturn(List.of(exp1, exp2));

        List<ExperienceDto> result = portfolioService.getAllExperiences();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getCompany()).isEqualTo("NTTData");
        assertThat(result.get(1).getCompany()).isEqualTo("Globant");
    }

    @Test
    @DisplayName("getAllSkills returns all skills ordered by sortOrder")
    void getAllSkills_ReturnsAllSkills() {
        Skill skill = Skill.builder()
                .id(UUID.randomUUID()).categoryEs("Lenguajes").categoryEn("Languages")
                .icon("layers").items(List.of("Java", "TypeScript")).sortOrder(1).build();

        when(skillRepository.findAllByOrderBySortOrderAsc()).thenReturn(List.of(skill));

        List<SkillDto> result = portfolioService.getAllSkills();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCategoryEs()).isEqualTo("Lenguajes");
        assertThat(result.get(0).getItems()).contains("Java");
    }

    @Test
    @DisplayName("getAllProjects returns only active projects")
    void getAllProjects_ReturnsActiveOnly() {
        Project project = Project.builder()
                .id(UUID.randomUUID()).name("Kafka DLQ")
                .descriptionEs("Desc ES").descriptionEn("Desc EN")
                .stack(List.of("Java", "Kafka")).featured(true)
                .githubUrl("https://github.com/DxrosNgu/kafka-using-spring-boot")
                .sortOrder(1).active(true).build();

        when(projectRepository.findByActiveTrueOrderBySortOrderAsc())
                .thenReturn(List.of(project));

        List<ProjectDto> result = portfolioService.getAllProjects();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Kafka DLQ");
        assertThat(result.get(0).getFeatured()).isTrue();
    }

    @Test
    @DisplayName("getAllCertifications returns all certifications")
    void getAllCertifications_ReturnsAll() {
        Certification cert = Certification.builder()
                .id(UUID.randomUUID()).name("AZ-900 Azure Fundamentals")
                .issuer("Microsoft Azure").year("2021").badge("Azure").sortOrder(1).build();

        when(certificationRepository.findAllByOrderBySortOrderAsc())
                .thenReturn(List.of(cert));

        List<CertificationDto> result = portfolioService.getAllCertifications();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("AZ-900 Azure Fundamentals");
    }
}
