package com.milton.instituto_descartes.models;

import java.util.ArrayList;

public class Instituto {
    private String user = "Upchiapas";
    private String password = "Upchiapas2024";
    private static ArrayList<Student> listStudents = new ArrayList<>();
    private IBase_Datos baseDatos;

    public Instituto(IBase_Datos baseDatos) {
        this.baseDatos = baseDatos;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void saveStudent(Student student) {
        if (baseDatos != null) {
            baseDatos.save(student);
            listStudents.add(student);
        }
    }

    public boolean deleteStudent(Student student) {
        if (baseDatos != null && baseDatos.delete(student)) {
            return listStudents.remove(student);
        }
        return false;
    }

    public void updateStudent(Student student) {
        if (baseDatos != null) {
            baseDatos.update(student);
        }
    }

    public ArrayList<Student> getAllStudents() {
        return listStudents;
    }

    public IBase_Datos getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(IBase_Datos baseDatos) {
        this.baseDatos = baseDatos;
    }
}
