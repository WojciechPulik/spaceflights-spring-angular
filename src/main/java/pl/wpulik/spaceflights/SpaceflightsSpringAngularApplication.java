package pl.wpulik.spaceflights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pl.wpulik.spaceflights.model.Flight;
import pl.wpulik.spaceflights.model.Gender;
import pl.wpulik.spaceflights.model.Tourist;
import pl.wpulik.spaceflights.service.FlightService;
import pl.wpulik.spaceflights.service.TouristService;

@SpringBootApplication
public class SpaceflightsSpringAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceflightsSpringAngularApplication.class, args);
		
	}

}
