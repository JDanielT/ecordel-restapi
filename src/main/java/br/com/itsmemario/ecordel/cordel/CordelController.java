package br.com.itsmemario.ecordel.cordel;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("cordels")
public class CordelController {
	
	private CordelService service;
	
	@Autowired
	CordelController(CordelService service) {
		super();
		this.service = service;
	}

	@GetMapping("{id}")
	public Cordel getCordel(@PathVariable String id) {
		return Cordel.builder()
			.author("Mário S.").title("This is a cordel")
			.xilogravura("https://i.pinimg.com/originals/25/9d/47/259d47304bf26a4678cb039b8d8ce7f9.jpg")
			.build();
	}
	
	@GetMapping
	public Page<Cordel> getCordels(Pageable pageable){
		return service.getCordels(pageable);
	}
	
	@PostMapping
	public ResponseEntity<Cordel> create(@RequestBody @Valid Cordel cordel, UriComponentsBuilder uriBuilder){
		
		URI uri = uriBuilder.path("/cordel/{id}").buildAndExpand(cordel.getId()).toUri();
		
		return ResponseEntity.created(uri).body(cordel); 
	}

}
