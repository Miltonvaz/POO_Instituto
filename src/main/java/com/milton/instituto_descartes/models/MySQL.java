package com.milton.instituto_descartes.models;

import java.util.ArrayList;

public class MySQL implements IBase_Datos {
    private static ArrayList<Student> listStudents = new ArrayList<>();

    @Override
    public ArrayList<Student> getStudents() {
        return listStudents;
    }

    @Override
    public boolean save(Student student) {
        return listStudents.add(student);
    }

    @Override
    public boolean update(Student student) {
        return true;
    }

    @Override
    public boolean delete(Student student) {
        return listStudents.remove(student);
    }
}
