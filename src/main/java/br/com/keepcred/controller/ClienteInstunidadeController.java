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

import br.com.keepcred.entities.ClienteInstunidade;
import br.com.keepcred.services.ClienteInstunidadeService;

@Controller
@RequestMapping("/clienteinstunidade")
public class ClienteInstunidadeController {

	@Autowired
	private ClienteInstunidadeService clienteInstunidadeService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClienteInstunidade> findAllCliInst() {
		return clienteInstunidadeService.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClienteInstunidade findCliInstById(@PathVariable("id") Long id) {
		return clienteInstunidadeService.findById(id);
	}
}
