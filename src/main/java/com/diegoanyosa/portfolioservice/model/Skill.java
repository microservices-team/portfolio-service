package com.diegoanyosa.portfolioservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.List;
import java.util.UUID;

@Entity @Table(name = "skills", schema = "portfolio")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Skill {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "category_es")
    private String categoryEs;

    @Column(name = "category_en")
    private String categoryEn;

    private String icon;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> items;

    @Column(name = "sort_order") @Builder.Default
    private int sortOrder = 0;
}
