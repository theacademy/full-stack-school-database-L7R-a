package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDaoImpl;
import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE

    StudentDao dao;
    public StudentServiceImpl(StudentDao studentDao){
        dao = studentDao;
    }
    //YOUR CODE ENDS HERE

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE
        return dao.getAllStudents();
        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE
        Student res = new Student();
        try{
            res = dao.findStudentById(id);
        }
        catch (DataAccessException e){
            res.setStudentFirstName("Student Not Found");
            res.setStudentFirstName("Student Not Found");
        }
        return res;
        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE

        if(student.getStudentFirstName().isBlank()){
            student.setStudentFirstName("First Name blank, student NOT added");
        }
        if(student.getStudentLastName().isBlank()){
            student.setStudentLastName("Last Name blank, student NOT added");
        }
        return dao.createNewStudent(student);
        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE

        if(id != student.getStudentId()){
            student.setStudentFirstName("IDs do not match, student not updated");
            student.setStudentLastName("IDs do not match, student not updated");
        }
        dao.updateStudent(student);
        return student;
        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE

        dao.deleteStudent(id);

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        CourseDaoImpl courseDao = new CourseDaoImpl(jdbcTemplate);
        Course course = courseDao.findCourseById(courseId);

        Student student = getStudentById(studentId);

        if(student.getStudentFirstName().equalsIgnoreCase("Student Not Found")){
            System.out.println("Student not found");
        }
        else if(course.getCourseName().equalsIgnoreCase("Course Not Found")){
            System.out.println("Course not found") ;
        }
        else{
            dao.deleteStudentFromCourse(studentId, courseId);
            System.out.println("Student: " + studentId + " deleted from course:" + courseId);
        }

        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            CourseDaoImpl courseDao = new CourseDaoImpl(jdbcTemplate);
            Course course = courseDao.findCourseById(courseId);

            Student student = getStudentById(studentId);

            if(student.getStudentFirstName().equalsIgnoreCase("Student Not Found")){
                System.out.println("Student not found");
            }
            else if(course.getCourseName().equalsIgnoreCase("Course Not Found")){
                System.out.println("Course not found");
            }
            else{
                dao.addStudentToCourse(studentId, courseId);
                System.out.println("Student: " + studentId + " added to course:" + courseId);
            }
        }
        catch (Exception e){
            System.out.println("Student: " + studentId + " already enrolled in course:" + courseId);
        }
        //YOUR CODE ENDS HERE
    }
}
