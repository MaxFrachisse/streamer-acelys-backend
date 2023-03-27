package fr.aelion.streamer.dto;

import fr.aelion.streamer.entities.Media;
import fr.aelion.streamer.entities.Module;
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
    private Set<MediaDto> medias = new HashSet<>();

    public void addMedias(Media media) {
        var mediaDto = new MediaDto();
        mediaDto.setId(media.getId());
        mediaDto.setTitle(media.getTitle());
        mediaDto.setSummary(media.getSummary());
        mediaDto.setDuration(media.getDuration());
        mediaDto.setCreatedAt(media.getCreatedAt());
        mediaDto.setUrl(media.getUrl());
        mediaDto.setSummary(media.getSummary());

        this.medias.add(mediaDto);
    }
}
