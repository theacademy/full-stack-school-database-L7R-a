package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.TeacherDao;
import mthree.com.fullstackschool.model.Student;
import mthree.com.fullstackschool.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherServiceInterface {

    //YOUR CODE STARTS HERE
    TeacherDao dao;
    public TeacherServiceImpl(TeacherDao teacherDao){
        dao = teacherDao;
    }

    //YOUR CODE ENDS HERE

    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE
        return dao.getAllTeachers();
        //YOUR CODE ENDS HERE
    }

    public Teacher getTeacherById(int id) {
        //YOUR CODE STARTS HERE
        Teacher res = new Teacher();
        try{
            res = dao.findTeacherById(id);
        }
        catch (DataAccessException e){
            res.setTeacherLName("Teacher Not Found");
            res.setTeacherFName("Teacher Not Found");
        }
        return res;
        //YOUR CODE ENDS HERE
    }

    public Teacher addNewTeacher(Teacher teacher) {
        //YOUR CODE STARTS HERE


        if(teacher.getTeacherFName().isBlank()){
            teacher.setTeacherFName("First Name blank, teacher NOT added");
        }
        if(teacher.getTeacherLName().isBlank()){
            teacher.setTeacherLName("Last Name blank, teacher NOT added");
        }
        return dao.createNewTeacher(teacher);
        //YOUR CODE ENDS HERE
    }

    public Teacher updateTeacherData(int id, Teacher teacher) {
        //YOUR CODE STARTS HERE


        if(id != teacher.getTeacherId()){
            teacher.setTeacherFName("IDs do not match, teacher not updated");
            teacher.setTeacherLName("IDs do not match, teacher not updated");
        }
        dao.updateTeacher(teacher);
        return teacher;

        //YOUR CODE ENDS HERE
    }

    public void deleteTeacherById(int id) {
        //YOUR CODE STARTS HERE

        deleteTeacherById(id);

        //YOUR CODE ENDS HERE
    }
}
