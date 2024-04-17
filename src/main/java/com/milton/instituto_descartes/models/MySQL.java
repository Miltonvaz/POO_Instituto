package com.milton.instituto_descartes.models;

import java.util.ArrayList;



public class MySQL implements IBase_Datos {
    private ArrayList<Estudiante> listStudents1;

    public  MySQL (){
        listStudents1 = new ArrayList<>();
    }

    @Override
    public boolean save(Estudiante estudiante) {
        return listStudents1.add(estudiante);
    }

    @Override
    public boolean update(Estudiante estudiante) {
        for (int i = 0; i < listStudents1.size(); i++) {
            Estudiante estudiante1 = listStudents1.get(i);
            if (estudiante1.getMatricula() == estudiante1.getMatricula()){
                listStudents1.set(i, estudiante);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Estudiante estudiante) {
        return listStudents1.remove(estudiante);
    }

    public ArrayList<Estudiante> getListStudents1() {
        return listStudents1;
    }
}
