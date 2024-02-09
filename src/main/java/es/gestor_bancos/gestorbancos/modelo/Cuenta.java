package es.gestor_bancos.gestorbancos.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUENTA")
public class Cuenta {

    @Id
    private int id;
    
    
    @Column
    private double dinero;
    
    @Column
    private int usuario;
    
    @Column
    private int banco;

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