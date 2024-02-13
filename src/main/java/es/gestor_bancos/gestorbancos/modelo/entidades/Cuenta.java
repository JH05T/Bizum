package es.gestor_bancos.gestorbancos.modelo.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUENTA")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "DINERO",nullable = true, precision = 10, scale = 2 )
    private double dinero;
    
    @Column(name = "USUARIO", nullable = true)
    private int usuario;
    
    @Column(name = "BANCO", nullable = true)
    private int banco;

    public Cuenta(){

    }

    public Cuenta(int id, double dinero, int usuario, int banco) {

        this.id = id;
        this.dinero = dinero;
        this.usuario = usuario;
        this.banco = banco;

    }

    public Cuenta(double dinero, int usuario, int banco) {

        this.dinero = dinero;
        this.usuario = usuario;
        this.banco = banco;

    }

    @Override
	public String toString() {

		return "Cuenta [id=" + id + ", dinero=" + dinero + ", usuario=" + usuario + ", banco=" + banco + "]";

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
    public double getDinero() {
        return dinero;
    }
    public void setDinero(double dinero) {
        this.dinero = dinero;
    }
    public int getUsuario() {
        return usuario;
    }
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
    public int getBanco() {
        return banco;
    }
    public void setBanco(int banco) {
        this.banco = banco;
    }

}