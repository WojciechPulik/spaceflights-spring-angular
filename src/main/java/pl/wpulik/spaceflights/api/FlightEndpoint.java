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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pl.wpulik.spaceflights.model.Flight;
import pl.wpulik.spaceflights.service.FlightService;

@RestController
public class FlightEndpoint {
	
	private FlightService flightService;
	
	@Autowired
	public FlightEndpoint(FlightService flightService) {
		this.flightService = flightService;
	}
	
	@GetMapping("/api/flights")
	public List<Flight> getAll(){
		List<Flight> resultFlight = flightService.getAllFlights();
		return resultFlight;
	}
	
	@GetMapping("/api/flights/{id}")
	public ResponseEntity<Flight> getById(@PathVariable Long id){
		Flight flight = flightService.getFlight(id);
		if(flight!=null) {
			return ResponseEntity.ok(flight);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/api/flights")
	public ResponseEntity<?>save(@RequestBody Flight flight){
		if(flight.getId()==null) {
			Flight saved = flightService.addFlight(flight);
			  URI location = ServletUriComponentsBuilder
	                    .fromCurrentRequest()
	                    .path("/{id}")
	                    .buildAndExpand(saved.getId())
	                    .toUri();
	            return ResponseEntity.created(location).body(flight);
	    } else {
	            return ResponseEntity.status(HttpStatus.CONFLICT).build();
	        } 
	    }
	
}
