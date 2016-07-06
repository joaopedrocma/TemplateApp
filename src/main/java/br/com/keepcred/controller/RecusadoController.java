package br.com.keepcred.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.keepcred.entities.Recusado;
import br.com.keepcred.services.RecusadoService;

@Controller
@RequestMapping("/recusados")
public class RecusadoController {

	@Autowired
	private RecusadoService recusadoService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Recusado> findAll() {
		return recusadoService.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Recusado findRecusado(@PathVariable("id") Long id) {
		return recusadoService.findRecusadoById(id);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<Recusado> createRecusado(@RequestBody Recusado recusado) {
		Recusado savedRecusado = recusadoService.create(recusado);
		return new ResponseEntity<Recusado>(savedRecusado, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<Recusado> update(@RequestBody Recusado recusado) {
		Recusado savedRecusado = recusadoService.update(recusado);
		return new ResponseEntity<Recusado>(savedRecusado, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		recusadoService.deleteRecusado(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
