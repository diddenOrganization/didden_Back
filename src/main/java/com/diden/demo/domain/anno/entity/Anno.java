package com.diden.demo.domain.anno.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "TB_ANNOUNCEMENT")
public class Anno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long annoId;
    private String annoTitle;
    private String annoContent;
    private LocalDateTime annoCreatedate;

    protected Anno() {
    }

    @Builder
    protected Anno(Long annoId, String annoTitle, String annoContent, LocalDateTime annoCreatedate) {
        this.annoId = annoId;
        this.annoTitle = annoTitle;
        this.annoContent = annoContent;
        this.annoCreatedate = annoCreatedate;
    }
}
