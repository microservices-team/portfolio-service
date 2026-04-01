package com.diegoanyosa.portfolioservice.dto.response;

import lombok.*;
import java.util.List;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ExperienceResponse {
    private UUID   id;
    private String company;
    private String role;
    private String period;
    private String location;
    private String descriptionEs;
    private String descriptionEn;
    private List<String> highlightsEs;
    private List<String> highlightsEn;
    private List<String> stack;
    private int    sortOrder;
}
