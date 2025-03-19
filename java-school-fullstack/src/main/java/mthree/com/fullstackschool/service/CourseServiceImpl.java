package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInterface {

    //YOUR CODE STARTS HERE
    CourseDao dao;
    public CourseServiceImpl(CourseDao courseDao){
        dao = courseDao;
    }


    //YOUR CODE ENDS HERE

    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE
        return dao.getAllCourses();

        //YOUR CODE ENDS HERE
    }

    public Course getCourseById(int id) {
        //YOUR CODE STARTS HERE
        Course course = new Course();
        try{
            course = dao.findCourseById(id);
        }
        catch (DataAccessException e){
            course.setCourseName("Course Not Found");
            course.setCourseDesc("Course Not Found");
        }
        return course;
        //YOUR CODE ENDS HERE
    }

    public Course addNewCourse(Course course) {
        //YOUR CODE STARTS HERE
        if(course.getCourseDesc().isBlank()){
            course.setCourseDesc("Description blank, course NOT added");
        }
        if(course.getCourseName().isBlank()){
            course.setCourseName("Name blank, course NOT added");
        }
        return dao.createNewCourse(course);

        //YOUR CODE ENDS HERE
    }

    public Course updateCourseData(int id, Course course) {
        //YOUR CODE STARTS HERE
        if(id != course.getCourseId()){
            course.setCourseDesc("IDs do not match, course not updated");
            course.setCourseName("IDs do not match, course not updated");
        }
        dao.updateCourse(course);
        return course;
        //YOUR CODE ENDS HERE
    }

    public void deleteCourseById(int id) {
        //YOUR CODE STARTS HERE
        dao.deleteCourse(id);
        System.out.println("Course ID: " + id + " deleted");
        //YOUR CODE ENDS HERE
    }
}
