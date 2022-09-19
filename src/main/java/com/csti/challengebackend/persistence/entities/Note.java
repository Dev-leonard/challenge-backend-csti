package com.csti.challengebackend.persistence.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(	name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(0)
    @Max(20)
    private Integer note;

    @NotBlank
    @NotNull
    @Size(max = 150)
    private String course;

    @NotBlank
    @NotNull
    @Size(max = 250)
    private String teacher;

    private Date createDate;

    private Date updateDate;

    @PrePersist
    private void onCreated() {
        Instant instant = Instant.now();
        this.createDate = Date.from(instant);
    }

    @PreUpdate
    private void onUpdated() {
        Instant instant = Instant.now();
        this.updateDate = Date.from(instant);
    }
}
