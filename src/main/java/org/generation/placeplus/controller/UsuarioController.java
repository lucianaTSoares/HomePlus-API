package org.generation.placeplus.controller;

import java.util.List;
import java.util.Optional;

import org.generation.placeplus.model.Usuario;
import org.generation.placeplus.model.UsuarioLogin;
import org.generation.placeplus.repository.UsuarioRepository;
import org.generation.placeplus.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository repository;
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> post(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarUsuario(usuario));
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> Authentication(@RequestBody Optional<UsuarioLogin> user){
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
}