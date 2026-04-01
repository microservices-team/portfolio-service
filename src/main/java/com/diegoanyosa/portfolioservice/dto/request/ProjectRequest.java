package com.diegoanyosa.portfolioservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProjectRequest {
    @NotBlank private String name;
    private String descriptionEs;
    private String descriptionEn;
    private String metrics;
    private List<String> stack;
    private boolean featured;
    private String githubUrl;
    private String liveUrl;
    private int    sortOrder;
    private boolean active = true;
}
