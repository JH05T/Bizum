package es.gestor_bancos.gestorbancos.Modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("BANCOS")
public class Banco {

    @Id
    private int id;
    private String nombre;

    public Banco(int id, String nombre) {

        this.id = id;
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