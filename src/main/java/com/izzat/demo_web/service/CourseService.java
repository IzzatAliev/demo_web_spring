package com.izzat.demo_web.service;

import com.izzat.demo_web.model.Course;

import java.io.IOException;
import java.util.Collection;

public interface CourseService {
    Course create(Course course);
    Course ping(String ipAddress) throws IOException;
    Collection<Course> list(int limit);
    Course get(Long id);
    Course update(Course course);
    Boolean delete(Long id);
}
