package com.example.volleyapplication.student;

import java.util.List;

public class StudentService implements IDao<Student>{

    private List<Student> students;

    public StudentService(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean create(Student o) {
        return students.add(o);
    }

    @Override
    public boolean update(Student o) {
        return false;
    }

    @Override
    public boolean delete(Student o) {
        return students.remove(o);
    }

    @Override
    public List<Student> findAll() {
        return this.students;
    }

    @Override
    public Student findByid(int id) {
        for(Student student:students){
            if (student.getId()==id){
                return student;
            }
        }
        return null;
    }

    @Override
    public boolean addAll(List<Student> students) {
        return this.students.addAll(students);
    }
}
