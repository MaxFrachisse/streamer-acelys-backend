package fr.aelion.streamer.services;

import fr.aelion.streamer.entities.Media;
import fr.aelion.streamer.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {
    @Autowired
    private MediaRepository repository;

    public List<Media> findAll() {
        List<Media> medias = repository.findAll();
        return medias;
    }
}
