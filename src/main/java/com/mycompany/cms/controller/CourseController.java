package com.mycompany.cms.controller;

import com.mycompany.cms.model.CourseModel;
import com.mycompany.cms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public ResponseEntity<List<CourseModel>> getAllCourses(){

        List<CourseModel> modelList = courseService.getAllCourses();
        ResponseEntity<List<CourseModel>> re = new ResponseEntity<>(modelList, HttpStatus.OK);
        return re;
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<CourseModel> getCourseDetail(@PathVariable Long courseId){
        CourseModel cm  = courseService.getCourseDetail(courseId);
        ResponseEntity<CourseModel> re = new ResponseEntity<>(cm, HttpStatus.OK);
        return re;
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseModel> addNewCourse(@RequestBody CourseModel courseModel){
        CourseModel cm = courseService.addNewCourse(courseModel);
        ResponseEntity<CourseModel> re = new ResponseEntity<>(cm, HttpStatus.OK);
        return re;
    }

    @PutMapping("/courses/{courseId}")
    public ResponseEntity<CourseModel> updateCourse(@PathVariable Long courseId, @RequestBody CourseModel courseModel){
        CourseModel cm = courseService.updateFullCourse(courseId, courseModel);
        ResponseEntity<CourseModel> re = new ResponseEntity<>(cm, HttpStatus.OK);
        return re;
    }

    @PatchMapping("/courses/{courseId}")
    public ResponseEntity<CourseModel> updateCourseTitle(@PathVariable Long courseId, @RequestBody CourseModel courseModel){
        CourseModel cm = courseService.updateCourseTitle(courseId, courseModel.getTitle());
        ResponseEntity<CourseModel> re = new ResponseEntity<>(cm, HttpStatus.OK);
        return re;
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId){
        courseService.deleteCourse(courseId);
        ResponseEntity<String> re = new ResponseEntity<>("Deleted course with Id: "+courseId, HttpStatus.OK);
        return re;
    }
}
