package ajaxtest.demo.mapper;

import ajaxtest.demo.bean.Course;
import ajaxtest.demo.bean.Trainitem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseAndItemMapper {
    @Select("select * from trainitem")
    List <Trainitem> getAllItem();

    @Select("SELECT courseId,courseName,period,details  from course as a,trainitem as b where b.itemId=a.itemId and a.itemId = #{id} ")
    List <Course> getCourseByItmeId(@Param("id") int id);
}
