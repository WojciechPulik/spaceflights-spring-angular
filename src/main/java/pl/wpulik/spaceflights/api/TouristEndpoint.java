package pl.wpulik.spaceflights.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pl.wpulik.spaceflights.service.TouristService;
import pl.wpulik.spaceflights.model.Tourist;

@RestController
public class TouristEndpoint {
	
	
	private TouristService touristService;
	
	@Autowired
	public TouristEndpoint(TouristService touristService) {
		this.touristService = touristService;
	}
	
	@GetMapping("/api/tourists")
	public List<Tourist> getAll(){
		List<Tourist> resultTourist = touristService.getAllTourists();
		return resultTourist;
	}
	
	@GetMapping("/api/tourists/{id}")
	public ResponseEntity<Tourist> getById(@PathVariable Long id){
		Tourist tourist = touristService.getTourist(id);
		if(tourist!=null) {
			return ResponseEntity.ok(tourist);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/api/tourists")
	public ResponseEntity<?>save(@RequestBody Tourist tourist){
		if(tourist.getId()==null) {
			Tourist saved = touristService.addTourist(tourist);
			  URI location = ServletUriComponentsBuilder
	                    .fromCurrentRequest()
	                    .path("/{id}")
	                    .buildAndExpand(saved.getId())
	                    .toUri();
	            return ResponseEntity.created(location).body(tourist);
	    } else {
	            return ResponseEntity.status(HttpStatus.CONFLICT).build();
	        } 
	    }
		
	
	
	
	
	
	

}














