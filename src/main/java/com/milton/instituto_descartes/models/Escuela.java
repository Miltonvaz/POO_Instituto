package com.milton.instituto_descartes.models;

import java.util.ArrayList;

public class Escuela {
    private String user = "Upchiapas";
    private String password = "Upchiapas2024";
    private  ArrayList<IBase_Datos> listStudents = new ArrayList<>();
    private MySQL mySQL;
    private Oracle oracle;
    private PostgreSQL postgreSQL;
    private IBase_Datos baseDatos;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Escuela(IBase_Datos baseDatos) {
        this.baseDatos = baseDatos;
        mySQL = new MySQL();
        oracle= new Oracle();
        postgreSQL= new PostgreSQL();

        listStudents.add(mySQL);
        listStudents.add(oracle);
        listStudents.add(postgreSQL);

    }
    public void save(Estudiante estudiante) {
        for (IBase_Datos database : listStudents) {
            database.save(estudiante);
        }
    }

    public boolean delete(String matricula) {
        for (IBase_Datos data : listStudents){
            data.delete(matricula);
        }
        return true;
    }

    public void updateStudent(Estudiante estudiante) {
        for (IBase_Datos data : listStudents){
            data.update(estudiante);
        }
    }

    public Escuela() {
    }

    public MySQL getMySQL() {
        return mySQL;
    }
    public Oracle getOracle() {
        return oracle;
    }

    public PostgreSQL getPostgreSQL() {
        return postgreSQL;
    }


}
