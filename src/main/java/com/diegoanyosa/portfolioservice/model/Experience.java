package com.diegoanyosa.portfolioservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.List;
import java.util.UUID;

@Entity @Table(name = "experience", schema = "portfolio")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Experience {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String role;

    private String period;
    private String location;

    @Column(name = "description_es", columnDefinition = "TEXT")
    private String descriptionEs;

    @Column(name = "description_en", columnDefinition = "TEXT")
    private String descriptionEn;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "highlights_es", columnDefinition = "jsonb")
    private List<String> highlightsEs;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "highlights_en", columnDefinition = "jsonb")
    private List<String> highlightsEn;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> stack;

    @Column(name = "sort_order")
    @Builder.Default
    private int sortOrder = 0;

    @Builder.Default
    private boolean active = true;
}
