package es.gestor_bancos.gestorbancos.modelo.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "dinero",nullable = true)
    private double dinero;
    
    @ManyToOne
    @JoinColumn(name = "usuario", nullable = true)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "banco", nullable = true)
    private Banco banco;

    public Cuenta(){

    }

    public Cuenta(int id, double dinero, Usuario usuario, Banco banco) {

        this.id = id;
        this.dinero = dinero;
        this.usuario = usuario;
        this.banco = banco;

    }

    public Cuenta(double dinero, Usuario usuario, Banco banco) {

        this.dinero = dinero;
        this.usuario = usuario;
        this.banco = banco;

    }

    @Override
	public String toString() {

		return "Cuenta [id=" + id + ", dinero=" + dinero + ", usuario=" + usuario.getId() + ", banco=" + banco.getId() + "]";

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

    public double getDinero() {

        return dinero;

    }

    public void setDinero(double dinero) {

        this.dinero = dinero;

    }

    public Usuario getUsuario() {

        return usuario;

    }

    public void setUsuario(Usuario usuario) {

        this.usuario = usuario;

    }

    public Banco getBanco() {

        return banco;

    }

    public void setBanco(Banco banco) {

        this.banco = banco;
        
    }

}