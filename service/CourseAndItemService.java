package ajaxtest.demo.service;

import ajaxtest.demo.bean.Course;
import ajaxtest.demo.bean.Trainitem;
import ajaxtest.demo.mapper.CourseAndItemMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseAndItemService {
    @Resource
    private CourseAndItemMapper courseAndItemMapper;

    public List <Trainitem> getAllItem(){

        return courseAndItemMapper.getAllItem();
    }

    public List <Course> getCourseByItmeId(int id){
        return courseAndItemMapper.getCourseByItmeId(id);
    }
}
