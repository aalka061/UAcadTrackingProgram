package server.logic.model;

import java.util.ArrayList;
import java.util.List;

public interface UniversityMediator {

    public static final int MaxCoursesForFTStudents=4;
    public static final int MaxCoursesForPTStudents=2;
    public static final int PassRate=70;
    
    public List<Course> coursesOfUniversity = new ArrayList<Course>();
    public List<Student> studentsOfUniversity = new ArrayList<Student>();
    public Student creatStudent(String title, int age, boolean isFullTime) ;
    public List<Course> courses();



}
