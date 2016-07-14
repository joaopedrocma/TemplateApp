package br.com.keepcred;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.keepcred.entities.Prestamistas;
import br.com.keepcred.services.PrestamistasService;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		Application main = new Application();
		main.saveData();
	}

	Boolean addtolist = true;

	@Autowired
	private PrestamistasService prestService;

	List<Prestamistas> prestcomcontrato;
	List<Prestamistas> prestsemcontrato;
	List<Prestamistas> prestfinal;

	public void saveData() {

		// Select Prestamistas Info on Mysql
		prestcomcontrato = prestService.getPrestamistasComContrato();
		prestsemcontrato = prestService.getPrestamistasSemContrato();

		// Merge prestamistascomcontrato with prestamistassemcontrato
		for (Prestamistas psc : prestsemcontrato) {
			addtolist = true;
			for (Prestamistas pcc : prestcomcontrato) {
				if (pcc.getNumcpfcnpj().equals(psc.getNumcpfcnpj())) {
					addtolist = false;
					break;
				}
			}
			if (addtolist == true) {
				prestfinal.add(psc);
			}
		}

		// Save Prestamistas on Mysql
		for (Prestamistas prestfinal : prestfinal) {
			prestService.create(prestfinal);
		}
	}
}
