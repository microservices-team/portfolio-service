package com.diegoanyosa.portfolioservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.List;
import java.util.UUID;

@Entity @Table(name = "projects", schema = "portfolio")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "description_es", columnDefinition = "TEXT")
    private String descriptionEs;

    @Column(name = "description_en", columnDefinition = "TEXT")
    private String descriptionEn;

    private String metrics;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> stack;

    @Builder.Default
    private boolean featured = false;

    @Column(name = "github_url")
    private String githubUrl;

    @Column(name = "live_url")
    private String liveUrl;

    @Column(name = "sort_order") @Builder.Default
    private int sortOrder = 0;

    @Builder.Default
    private boolean active = true;
}
