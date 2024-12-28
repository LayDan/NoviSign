package org.novisign.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "slideshow")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Slideshow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "slideshow", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SlideshowImage> images;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

}
