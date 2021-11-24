package com.izzat.demo_web;

import com.izzat.demo_web.enumeration.Status;
import com.izzat.demo_web.model.Course;
import com.izzat.demo_web.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoWebApplication.class, args);
    }

    @Bean
    CommandLineRunner run(CourseRepository courseRepository) {
        return args -> {
            courseRepository.save(new Course(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "Personal PC",
                    "http://localhost:8080/course/image/course1.png", Status.COURSE_UP));
            courseRepository.save(new Course(null, "192.168.1.16", "Izzat Linux", "32 GB", "Dell PC",
                    "http://localhost:8080/course/image/course2.png", Status.COURSE_UP));
            courseRepository.save(new Course(null, "192.168.1.134", "Mc", "64 GB", "Web PC",
                    "http://localhost:8080/course/image/course3.png", Status.COURSE_UP));
            courseRepository.save(new Course(null, "192.168.1.145", "APPLE", "16 GB", "Mail PC",
                    "http://localhost:8080/course/image/course4.png", Status.COURSE_UP));
        };
    }

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Requested-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
