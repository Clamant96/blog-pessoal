package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // define uma classe controller
@RequestMapping("/postagens") // define uma URI para ser consumida no front-end
@CrossOrigin("*") // aceita requisicao de qualquer origem, seja ela web, mobile etc
public class PostagemController {
	
	@Autowired // criamos uma ingecao de dependecia porque a classe abaixo, se trata de uma interface, entao nao pode ser instanciada
	private PostagemRepository repository;
	
	@GetMapping // retorna todos os dados contidos dentro da base de dados
	public ResponseEntity<List<Postagem>> GetAll() {
		
		return ResponseEntity.ok(repository.findAll()); // caso a resposta do servidor seja OK, entao e impresso os dados ao usuario
	}
	
	@GetMapping("/{id}") // retorna uma pesquisa feita por um id especifico
	public ResponseEntity<Postagem> GetById(@PathVariable long id) { // recebe como paremetro o id requisitado pelo usuario via URI / URL
		
		return repository.findById(id) // cria uma condicao de retorno
				.map(resp -> ResponseEntity.ok(resp)) // caso haja dados nesse id requisitado, entao e retornado os dados do id
				.orElse(ResponseEntity.notFound().build()); // caso contrario, e retornado uma mensagem de erro 404, informado que os dados solicitados nao foram localizados na base de dados
															
	}
	
	@GetMapping("/titulo/{titulo}") // retorna uma pequisa de um titulo ou por partes das letras desse titulo
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo) { // recebe como parametro letras de um titulo
		
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo)); // por meio da query criada dentro do Repository, podemos utilizalo para 
																						  //realizar nossa pesquisa por meio de letras ou pavras completas de titulos como se fosse o LIKE do SQL
	}

}
