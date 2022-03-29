package ajaxtest.demo.controller;

import ajaxtest.demo.bean.Course;
import ajaxtest.demo.bean.Trainitem;
import ajaxtest.demo.service.CourseAndItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CourseAndItemController {
    @Resource
    private CourseAndItemService courseAndItemService;

    @RequestMapping("/getAllItme")
    public String getAllItme(){
        List <Trainitem> list = courseAndItemService.getAllItem();
        String str = "";
        for (Trainitem item:list) {
            str += item.getItemId() + "," +
                    item.getItemName() + ";";
        }
//        System.out.println(str);
        return str;
    }

    @RequestMapping("/getCourseByItmeId")
    public String getCourseByItmeId(int id){
        List <Course> list = courseAndItemService.getCourseByItmeId(id);
        String str = "";
        for (Course course:list) {
            str += course.getCourseId() + "," +
                    course.getCourseName() + "," +
                    course.getPeriod() + "," +
                    course.getDetails() + ";";
        }
//        System.out.println(str);
        return str;
    }

}
