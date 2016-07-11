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

import br.com.keepcred.entities.ParticipanteContaCor;
import br.com.keepcred.services.ParticipanteContaCorService;;

@Controller
@RequestMapping("/participante")
public class ParticipanteContaCorController {

	@Autowired
	private ParticipanteContaCorService participanteContaCorService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ParticipanteContaCor> findAllPartContaCor() {
		return participanteContaCorService.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ParticipanteContaCor findPartContaCor(@PathVariable("id") Long id) {
		return participanteContaCorService.findById(id);
	}
}
