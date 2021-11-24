package com.izzat.demo_web.resource;

import com.izzat.demo_web.enumeration.Status;
import com.izzat.demo_web.model.Course;
import com.izzat.demo_web.model.Response;
import com.izzat.demo_web.service.implementation.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.izzat.demo_web.enumeration.Status.COURSE_UP;
import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseResource {
    private final CourseServiceImpl courseService;

    @GetMapping("/list")
    public ResponseEntity<Response> getCourses() throws InterruptedException {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("courses", courseService.list(30)))
                        .message("Server retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/list/{ipAddress}")
    public ResponseEntity<Response> pingCourse(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Course course = courseService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("courses", course))
                        .message(course.getStatus() == COURSE_UP ? "Ping success" : "Ping failed")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveCourse(@RequestBody @Valid Course course) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("course", courseService.create(course)))
                        .message("Course created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getCourse(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("courses", courseService.get(id)))
                        .message("Course retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Response> deleteCourse(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("courses", courseService.delete(id)))
                        .message("Course deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getCourseImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home" + "/Изображения/forDemoWebpng/" + fileName)));
    }
}
