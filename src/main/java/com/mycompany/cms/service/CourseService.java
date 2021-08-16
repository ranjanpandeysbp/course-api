package com.mycompany.cms.service;

import com.mycompany.cms.model.CourseModel;

import java.util.List;

public interface CourseService {

    public List<CourseModel> getAllCourses();
    public CourseModel getCourseDetail(Long courseId);
    public CourseModel addNewCourse(CourseModel courseModel);
    public CourseModel updateFullCourse(Long courseId, CourseModel courseModel);
    public CourseModel updateCourseTitle(Long courseId, String title);
    public Long deleteCourse(Long courseId);
}
