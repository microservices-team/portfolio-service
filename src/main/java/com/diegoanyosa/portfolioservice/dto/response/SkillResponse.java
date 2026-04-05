package com.diegoanyosa.portfolioservice.dto.response;

import lombok.*;
import java.util.List;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SkillResponse {
    private UUID   id;
    private String categoryEs;
    private String categoryEn;
    private String icon;
    private List<String> items;
    private int    sortOrder;
}
