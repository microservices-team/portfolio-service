package com.diegoanyosa.portfolioservice.dto.response;

import lombok.*;
import java.util.List;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ProjectResponse {
    private UUID   id;
    private String name;
    private String descriptionEs;
    private String descriptionEn;
    private String metrics;
    private List<String> stack;
    private boolean featured;
    private String githubUrl;
    private String liveUrl;
    private int    sortOrder;
}
