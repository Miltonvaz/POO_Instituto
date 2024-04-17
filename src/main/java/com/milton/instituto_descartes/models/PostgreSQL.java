package com.milton.instituto_descartes.models;

import java.util.ArrayList;

public class PostgreSQL implements IBase_Datos {
    private ArrayList<Estudiante> listStudents3;

    public PostgreSQL(){
        listStudents3 = new ArrayList<>();
    }

    @Override
    public boolean save(Estudiante estudiante) {
        return listStudents3.add(estudiante);
    }

    @Override
    public boolean update(Estudiante estudiante) {
        for (int i = 0; i < listStudents3.size(); i++) {
            Estudiante estudiante1 = listStudents3.get(i);
            if (estudiante1.getMatricula() == estudiante1.getMatricula()){
                listStudents3.set(i, estudiante);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String matricula) {
        for (Estudiante estudiante : listStudents3) {
            if (estudiante.getMatricula().equals(matricula)) {
                listStudents3.remove(estudiante);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Estudiante> getListStudents3() {
        return listStudents3;
    }
}
