package com.diegoanyosa.portfolioservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity @Table(name = "education", schema = "portfolio")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Education {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String institution;

    @Column(name = "degree_es")
    private String degreeEs;

    @Column(name = "degree_en")
    private String degreeEn;

    private String period;
    private String location;
}
