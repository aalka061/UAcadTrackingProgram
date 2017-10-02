package com.carleton.mainclasses;

import java.util.ArrayList;
import java.util.List;

public interface UniversityMediator {

    public static final int MaxCoursesForFTStudents=4;
    public static final int MaxCoursesForPTStudents=2;
    public static final int PassRate=70;
    
    public List<Course> coursesOfUniversity = new ArrayList<Course>();
    public List<Course> courses();



}
