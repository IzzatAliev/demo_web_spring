package com.izzat.demo_web.repository;

import com.izzat.demo_web.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByIpAddress(String ipAddress);
}
