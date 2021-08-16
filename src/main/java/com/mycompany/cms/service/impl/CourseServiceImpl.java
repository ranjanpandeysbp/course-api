package com.mycompany.cms.service.impl;

import com.mycompany.cms.entity.CourseEntity;
import com.mycompany.cms.model.CourseModel;
import com.mycompany.cms.repository.CourseRepository;
import com.mycompany.cms.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseModel> getAllCourses() {
        List<CourseEntity> listOfEntities = courseRepository.findAll();
        List<CourseModel> modelList = null;
        if(!listOfEntities.isEmpty()){
            modelList = new ArrayList<>();
            CourseModel courseModel = new CourseModel();
            for(CourseEntity ce :listOfEntities){
                BeanUtils.copyProperties(ce, courseModel);
                modelList.add(courseModel);
            }
        }
        return modelList;
    }

    @Override
    public CourseModel getCourseDetail(Long courseId) {
        Optional<CourseEntity> optCE = courseRepository.findById(courseId);
        CourseModel courseModel = null;
        if(optCE.isPresent()){
            courseModel = new CourseModel();
            BeanUtils.copyProperties(optCE.get(), courseModel);
        }
        return courseModel;
    }

    @Override
    public CourseModel addNewCourse(CourseModel courseModel) {

        CourseEntity courseEntity = new CourseEntity();
        BeanUtils.copyProperties(courseModel, courseEntity);
        courseEntity = courseRepository.save(courseEntity);

        CourseModel cm = new CourseModel();
        BeanUtils.copyProperties(courseEntity, cm);

        return cm;
    }

    @Override
    public CourseModel updateFullCourse(Long courseId, CourseModel courseModel) {
        CourseEntity courseEntity = courseRepository.findById(courseId).get();
        BeanUtils.copyProperties(courseModel, courseEntity);
        courseEntity = courseRepository.save(courseEntity);
        BeanUtils.copyProperties(courseEntity, courseModel);
        return courseModel;
    }

    @Override
    public CourseModel updateCourseTitle(Long courseId, String title) {
        CourseEntity courseEntity = courseRepository.findById(courseId).get();
        courseEntity.setTitle(title);
        courseEntity = courseRepository.save(courseEntity);
        CourseModel cm = new CourseModel();
        BeanUtils.copyProperties(courseEntity, cm);
        return cm;
    }

    @Override
    public Long deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
        return courseId;
    }
}
