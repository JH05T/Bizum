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
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre",nullable = true,length = 255)
    private String nombre;

    @Column(name = "apellidos",nullable = true,length = 255)
    private String apellidos;

    @Column(name = "telefono",nullable = true,length = 255)
    private String telefono;

    @Column(name = "email",nullable = true,length = 15)
    private String email;

    @Column(name = "password",nullable = true,length = 255)
    private String password;

    @ManyToOne
    @JoinColumn(name = "cuentaBizum", referencedColumnName = "id")
    private Cuenta cuentaBizum;


    public Usuario() {

    }

    public Usuario(int id, String nombre, String apellidos, String telefono, String email, String password, Cuenta cuentaBizum) {

        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.cuentaBizum = cuentaBizum;

    }

    public Usuario(String nombre, String apellidos, String telefono, String email, String password, Cuenta cuentaBizum) {

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.password = password;
        this.cuentaBizum = cuentaBizum;

    }

    @Override
	public String toString() {

		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", email=" + email + ", password=" + password + ", cuentaBizum=" + cuentaBizum + "]";

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cuenta getCuentaBizum() {
        return cuentaBizum;
    }

    public void setCuentaBizum(Cuenta cuentaBizum) {
        this.cuentaBizum = cuentaBizum;
    }
    
}
