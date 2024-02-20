package es.gestor_bancos.gestorbancos;

import java.awt.Desktop;
import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import es.gestor_bancos.gestorbancos.modelo.servicio.GestorDB;

@SpringBootApplication
@ComponentScan(basePackages = {"es.gestor_bancos.gestorbancos.modelo", "es.gestor_bancos.gestorbancos.controlador"})
@EntityScan(basePackages = "es.gestor_bancos.gestorbancos.modelo")
public class GestorBancosApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext contexto = SpringApplication.run(GestorBancosApplication.class, args);

		GestorDB gestorDB = contexto.getBean(GestorDB.class);

		if (gestorDB.buscarUsuarioPorId(1) == null){

			gestorDB.insertarDatos();
			
		}

		String url = "http://localhost:8090";

		if(Desktop.isDesktopSupported()){

			Desktop desktop = Desktop.getDesktop();

			try {

				desktop.browse(new URI(url));

			} catch (Exception e) {

				e.printStackTrace();

			}

		}else{

			Runtime runtime = Runtime.getRuntime();

			try {

				runtime.exec("xdg-open " + url);

			} catch (Exception e) {

				e.printStackTrace();

			}
			
		}

	}

}