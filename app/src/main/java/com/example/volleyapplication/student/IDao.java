package com.example.volleyapplication.student;

import java.util.List;

public interface IDao<T>{
    boolean create(T o);
    boolean update(T o);
    boolean delete(T o);
    List<T> findAll();
    T findByid(int id);

    boolean addAll(List<Student> students);
}
