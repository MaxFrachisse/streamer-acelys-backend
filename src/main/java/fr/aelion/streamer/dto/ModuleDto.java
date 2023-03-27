package fr.aelion.streamer.dto;

import fr.aelion.streamer.entities.Media;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ModuleDto {
    private int id;
    private String name;
    private String objective;
    private Set<Media> medias = new HashSet<>();
}
