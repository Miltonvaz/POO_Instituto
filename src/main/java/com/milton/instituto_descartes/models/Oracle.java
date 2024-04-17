package com.milton.instituto_descartes.models;

import java.util.ArrayList;

public class Oracle implements IBase_Datos {
    private ArrayList<Estudiante> listStudents2;

    public  Oracle  (){
        listStudents2 = new ArrayList<>();
    }

    @Override
    public boolean save(Estudiante estudiante) {
        return listStudents2.add(estudiante);
    }

    @Override
    public boolean update(Estudiante estudiante) {
        for (int i = 0; i < listStudents2.size(); i++) {
            Estudiante estudiante1 = listStudents2.get(i);
            if (estudiante1.getMatricula() == estudiante1.getMatricula()){
                listStudents2.set(i, estudiante);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Estudiante estudiante) {
        return listStudents2.remove(estudiante);
    }

    public ArrayList<Estudiante> getListStudents2() {
        return listStudents2;
    }
}
