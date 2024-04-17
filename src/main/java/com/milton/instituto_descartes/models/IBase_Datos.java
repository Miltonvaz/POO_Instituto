package com.milton.instituto_descartes.models;

public interface IBase_Datos {
    boolean save(Estudiante estudiante);
    boolean update(Estudiante estudiante);
    boolean delete(String matricula);

}
