package fr.aelion.streamer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    private String summary;

    private float duration;

    private LocalDate createdAt;

    private String url;

    @ManyToOne(targetEntity = Module.class)
    @JoinColumn(name = "module_id")
    private Module module;
    @OneToOne
    @JoinColumn(name = "typemedia_id")
    private MediaType mediaType;
}
