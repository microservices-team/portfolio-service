package com.diegoanyosa.portfolioservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class ExperienceRequest {
    @NotBlank private String company;
    @NotBlank private String role;
    private String period;
    private String location;
    private String descriptionEs;
    private String descriptionEn;
    private List<String> highlightsEs;
    private List<String> highlightsEn;
    private List<String> stack;
    private int    sortOrder;
    private boolean active = true;
}
