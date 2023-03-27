package fr.aelion.streamer.services;

import fr.aelion.streamer.dto.FullCourseDto;
import fr.aelion.streamer.dto.ModuleDto;
import fr.aelion.streamer.entities.Course;
import fr.aelion.streamer.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository repository;
    public List<FullCourseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(c -> {
                    FullCourseDto fullCourseDto = new FullCourseDto();

                    fullCourseDto.setId(c.getId());
                    fullCourseDto.setTitle(c.getTitle());
                    fullCourseDto.setCreatedAt(c.getCreatedAt());
                    fullCourseDto.setUpdatedAt(c.getUpdatedAt());
                    // Make as many ModuleDto as needed
                    var modules = c.getModules();
                    for (var module : modules) {
                        var moduleDto = fullCourseDto.addModule(module);
                        for(var media : module.getMedias()){
                            moduleDto.addMedias(media);
                        }
                        fullCourseDto.getModules().add(moduleDto);

                    }
                    return fullCourseDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public FullCourseDto findOne(int id) {
       return repository.findById(id)
               .map((c) -> {
                   var fullCourseDto = new FullCourseDto();
                   fullCourseDto.setId(c.getId());
                   fullCourseDto.setTitle(c.getTitle());
                   fullCourseDto.setCreatedAt(c.getCreatedAt());
                   fullCourseDto.setUpdatedAt(c.getUpdatedAt());
                   // Make as many ModuleDto as needed
                   var modules = c.getModules();
                   for (var module : modules) {
                       fullCourseDto.addModule(module);
                   }
                   return fullCourseDto;
               })
               .orElseThrow();

    }
}
