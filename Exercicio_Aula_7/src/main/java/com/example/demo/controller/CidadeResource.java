package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.*;	

@RestController
@RequestMapping ("/cidades")
public class CidadeResource {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@GetMapping ("/cidades")
	public List <Cidade> cidades () {
		return cidadeRepository.findAll();
	}
	
	@PostMapping ("/salvar")
	public ResponseEntity<Cidade> registrarCidades (@RequestBody Cidade paramCidade, HttpServletResponse response) {
		Cidade cidade = cidadeRepository.save(paramCidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}").buildAndExpand(cidade.getId()).toUri();
		return ResponseEntity.created(uri).body(cidade);
	}
	
	@GetMapping ("/primeiraLetra/{nome}")
	public List<Cidade> buscarNome (@PathVariable String nome) {
		return cidadeRepository.listarPorLetraEspecifica(nome);
	}	

	@GetMapping ("/Coord/{latitude}/{longitude}")
	public List<Cidade> buscarCoordenada (@PathVariable String latitude, @PathVariable String longitude) {
		return cidadeRepository.listarPorLatitudeLongitude(latitude, longitude);
	}	
	
}
