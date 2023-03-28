package fr.aelion.streamer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @JoinColumn(name = "module_id", referencedColumnName = "id")
    private Module module;
    @ManyToOne(targetEntity = TypeMedia.class)
    @JoinColumn(name = "typemedia_id", referencedColumnName = "id")
    private TypeMedia mediaType;
}
