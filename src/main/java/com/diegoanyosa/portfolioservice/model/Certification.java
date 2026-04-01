package com.diegoanyosa.portfolioservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity @Table(name = "certifications", schema = "portfolio")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Certification {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String issuer;
    private String year;
    private String badge;

    @Column(name = "sort_order") @Builder.Default
    private int sortOrder = 0;
}
