package es.gestor_bancos.gestorbancos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import es.gestor_bancos.gestorbancos.Modelo.GestorDB;

@SpringBootApplication
public class GestorBancosApplication {

	public static void main(String[] args) {

		//SpringApplication.run(GestorBancosApplication.class, args);
		GestorDB gestorDB = new GestorDB();
		gestorDB.conectar();
		gestorDB.desconectar();

	}

}
