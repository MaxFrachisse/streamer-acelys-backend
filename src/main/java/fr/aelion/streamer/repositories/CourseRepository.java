package fr.aelion.streamer.repositories;

import fr.aelion.streamer.entities.Course;
import fr.aelion.streamer.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
