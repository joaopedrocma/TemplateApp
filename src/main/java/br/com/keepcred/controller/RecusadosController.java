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

import br.com.keepcred.entities.Recusados;
import br.com.keepcred.services.RecusadosService;

@Controller
@RequestMapping("/recusados")
public class RecusadosController {

	@Autowired
	private RecusadosService recusadoService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Recusados> findAllRecusados() {
		return recusadoService.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Recusados findRecusado(@PathVariable("id") Long id) {
		return recusadoService.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<Recusados> createRecusado(@RequestBody Recusados recusado) {
		Recusados savedRecusado = recusadoService.create(recusado);
		return new ResponseEntity<Recusados>(savedRecusado, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<Recusados> updateRecusado(@RequestBody Recusados recusado) {
		Recusados savedRecusado = recusadoService.update(recusado);
		return new ResponseEntity<Recusados>(savedRecusado, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Void> deleteRecusado(@PathVariable("id") Long id) {
		recusadoService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
