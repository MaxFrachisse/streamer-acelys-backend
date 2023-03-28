package fr.aelion.streamer.services;

import fr.aelion.streamer.dto.FullCourseDto;
import fr.aelion.streamer.entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<FullCourseDto> findAll();

    FullCourseDto findOne(int id);

    void delete(int id);

    Course findById(int id);

}
