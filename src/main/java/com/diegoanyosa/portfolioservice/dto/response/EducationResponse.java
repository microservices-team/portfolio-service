package com.diegoanyosa.portfolioservice.dto.response;

import lombok.*;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class EducationResponse {
    private UUID   id;
    private String institution;
    private String degreeEs;
    private String degreeEn;
    private String period;
    private String location;
}
