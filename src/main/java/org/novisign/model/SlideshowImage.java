package org.novisign.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "slideshow_image")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlideshowImage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "slideshow_id", nullable = false)
    private Slideshow slideshow;

    @ManyToOne
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;

    @Column(nullable = false)
    private Double duration;

    @Column(nullable = false)
    private Integer orderIndex;

}
