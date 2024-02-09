package es.gestor_bancos.gestorbancos.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BANCO")
public class Banco {

    @Id
    private int id;

    @Column
    private String nombre;

    public Banco(int id, String nombre) {

        this.id = id;
        this.nombre = nombre;

    }

    public Banco(String nombre) {

        this.nombre = nombre;

    }

    /*
     * Getters & Setters
     */

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}