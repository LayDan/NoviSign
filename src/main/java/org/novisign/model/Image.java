package org.novisign.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "image")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //In this case, we could use url like an id, because url is unique and not changeable, but okay
    @Column(name = "url", nullable = false, unique = true)
    private String url;

    @Column(name = "duration", nullable = false)
    private Double duration;
    @Column(name = "addition_date", nullable = false)
    private LocalDateTime additionDate;
}
