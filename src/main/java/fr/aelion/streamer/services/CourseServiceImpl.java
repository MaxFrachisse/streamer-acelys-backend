package fr.aelion.streamer.services;

import fr.aelion.streamer.dto.AddCourseDto;
import fr.aelion.streamer.dto.AddStudentDto;
import fr.aelion.streamer.dto.FullCourseDto;
import fr.aelion.streamer.entities.Course;
import fr.aelion.streamer.entities.Module;
import fr.aelion.streamer.entities.Student;
import fr.aelion.streamer.repositories.CourseRepository;
import fr.aelion.streamer.repositories.MediaRepository;
import fr.aelion.streamer.repositories.ModuleRepository;
import fr.aelion.streamer.services.exceptions.EmailAlreadyExistsException;
import fr.aelion.streamer.services.exceptions.LoginAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<FullCourseDto> findAll() {
        return courseRepository.findAll()
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
                        for (var media : module.getMedias()) {
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
        return courseRepository.findById(id)
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

    @Override
    public void delete(int id) {
        try {
            var course = this.findById(id);
            var modules = course.getModules();
            for (var module : modules
            ) {
                var medias = module.getMedias();
                for (var media : medias
                ) {
                    media.setModule(null);
                    mediaRepository.save(media);
                }
            }
            courseRepository.delete(course);
        } catch (NoSuchElementException e) {
            throw e;
        }
    }

    @Override
    public Course findById(int id) {
        return courseRepository.findById(id).orElseThrow();
    }

    public Course add(AddCourseDto course){
        Course newCourse = modelMapper.map(course, Course.class);
        newCourse.setCreatedAt(LocalDate.now());
        newCourse = (Course) courseRepository.save(newCourse);

        return newCourse;
    }

    public Course addCourseAndModule(Course course) {
        AddCourseDto newCourseDto = modelMapper.map(course, AddCourseDto.class);
        newCourseDto.setCreatedAt(LocalDate.now());
        Course myNewCourse = add(newCourseDto);

        Module[] modules = course.getModules().toArray(new Module[0]);

        for (var module: modules
             ) {
            module.setCourse(myNewCourse);
            moduleRepository.save(module);
        }
        return myNewCourse;
    }
}
