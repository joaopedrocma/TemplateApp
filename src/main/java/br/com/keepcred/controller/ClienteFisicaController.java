package br.com.keepcred.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.keepcred.entities.ClienteFisica;
import br.com.keepcred.services.ClienteFisicaService;

@Controller
@RequestMapping("/clientefisica")
public class ClienteFisicaController {

	@Autowired
	private ClienteFisicaService clienteFisicaService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClienteFisica> findAll() {
		return clienteFisicaService.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClienteFisica findClienteInstunidade(@PathVariable("id") Long id) {
		
		ClienteFisica ret = clienteFisicaService.findClienteFisicaById(id);

	
		return ret;
	}
}
