package es.gestor_bancos.gestorbancos.modelo.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bancos")
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = true,length = 255)
    private String nombre;

    public Banco(){

    }

    public Banco(int id, String nombre) {

        this.id = id;
        this.nombre = nombre;

    }

    public Banco(String nombre) {

        this.nombre = nombre;

    }

    @Override
	public String toString() {

		return "Banco [id=" + id + ", nombre=" + nombre + "]";
        
	}



/* * * * * * * * * * * * *
 *                       *
 *   Getters & Setters   *
 *                       *
 * * * * * * * * * * * * */

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