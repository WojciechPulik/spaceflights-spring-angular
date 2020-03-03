package pl.wpulik.spaceflights.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wpulik.spaceflights.dao.FlightDaoImpl;
import pl.wpulik.spaceflights.dao.TouristDaoImpl;
import pl.wpulik.spaceflights.model.Flight;
import pl.wpulik.spaceflights.model.Tourist;

@Service
public class TouristService {
	
	@Autowired
	private TouristDaoImpl touristDaoImpl;
	@Autowired
	private FlightDaoImpl flightDaoImpl;
	
	
	public Tourist getTourist(Long touristId) {
		Tourist resultFlight = touristDaoImpl.get(touristId);
		return resultFlight;
	}
	
	public List<Tourist> getAllTourists (){
		List<Tourist> results = touristDaoImpl.getAll();
		return results;
	}
	
	public Tourist addTourist(Tourist tourist) {
		touristDaoImpl.save(tourist);
		return tourist;
		
	}
	public void removeTouristFlight(Long touristId, Long flightId) {
		Tourist tourist = touristDaoImpl.get(touristId);
		flightDaoImpl.removeTouristFromFlight(flightId, tourist);
	}
	
	public void removeTourist(Long touristId) {
		Tourist touristToRemove = touristDaoImpl.get(touristId);
		Set<Flight> flights = touristToRemove.getFlights();
		for(Flight f : flights) {
			flightDaoImpl.removeTouristFromFlight(f.getId(), touristToRemove);
		}
		touristDaoImpl.remove(touristId);
	}
	
	

}
