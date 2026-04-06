package com.diegoanyosa.portfolioservice.dto.response;

import lombok.*;
import java.util.UUID;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CertificationResponse {
    private UUID   id;
    private String name;
    private String issuer;
    private String year;
    private String badge;
    private int    sortOrder;
}
