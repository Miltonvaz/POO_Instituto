package com.milton.instituto_descartes.models;

import java.util.ArrayList;

public interface IBase_Datos {
    ArrayList<Student> getStudents();
    boolean save(Student student);
    boolean update(Student student);
    boolean delete(Student student);
}
