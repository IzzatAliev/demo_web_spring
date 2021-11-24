package com.izzat.demo_web.service.implementation;

import com.izzat.demo_web.enumeration.Status;
import com.izzat.demo_web.model.Course;
import com.izzat.demo_web.repository.CourseRepository;
import com.izzat.demo_web.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static com.izzat.demo_web.enumeration.Status.COURSE_DOWN;
import static com.izzat.demo_web.enumeration.Status.COURSE_UP;
import static java.lang.Boolean.*;
import static org.springframework.data.domain.PageRequest.*;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Course create(Course course) {
        log.info("Saving new course: {}", course.getName());
        course.setImageUrl(setCourseImageUrl());
        return courseRepository.save(course);
    }

    @Override
    public Course ping(String ipAddress) throws IOException {
        log.info("Pinging course IP: {}", ipAddress);
        Course course = courseRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        course.setStatus(address.isReachable(10000) ? COURSE_UP : COURSE_DOWN);
        courseRepository.save(course);
        return course;
    }

    @Override
    public Collection<Course> list(int limit) {
        log.info("Fetching all courses");
        return courseRepository.findAll(of(0, limit)).toList();
    }

    @Override
    public Course get(Long id) {
        log.info("Fetching course by id: {}", id);
        return courseRepository.findById(id).get();
    }

    @Override
    public Course update(Course course) {
        log.info("Updating course: {}", course.getName());
        return courseRepository.save(course);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting course by id: {}", id);
        courseRepository.deleteById(id);
        return TRUE;
    }

    private String setCourseImageUrl() {
        String[] imageNames = {"course1.png", "course2.png", "course3.png", "course4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/course/image/" + imageNames[new Random().nextInt(4)]).toUriString();
    }
}
