package pl.wpulik.spaceflights.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wpulik.spaceflights.dao.FlightDaoImpl;
import pl.wpulik.spaceflights.dao.TouristDaoImpl;
import pl.wpulik.spaceflights.model.Flight;
import pl.wpulik.spaceflights.model.Tourist;

@Service
public class FlightService {
	
	@Autowired
	private FlightDaoImpl flightDaoImpl;
	@Autowired
	private TouristDaoImpl touristDaoImpl;
	
	
	public Flight getFlight(Long flightId) {
		Flight resultFlight = flightDaoImpl.get(flightId);
		return resultFlight;
	}
	
	public List<Flight> getAllFlights() {
		List<Flight> resultList = flightDaoImpl.getAll();
		return resultList;
	}
	
	public void addTouristToFlight(Long touristId, Long flightId) {
		int takenSeats;
		int allSeats;
		Flight flightForTourist = flightDaoImpl.get(flightId);
		takenSeats = flightForTourist.getTourists().size();
		allSeats = flightForTourist.getSeatsNumber();
		
		if(takenSeats < allSeats) {
			Tourist touristToAdd = touristDaoImpl.get(touristId);
			flightDaoImpl.addTouristToFlight(flightId, touristToAdd);
		}
		else {
			System.out.println("No seats aviliable");
		}
	}
	
	public void removeTouristFromFlight(Long touristId, Long flightId) {
		Tourist touristToRemove = touristDaoImpl.get(touristId);
		flightDaoImpl.removeTouristFromFlight(flightId, touristToRemove);
		
	}
	
	public void removeFlight(Long flightId) {
		flightDaoImpl.remove(flightId);
	}
	
	public Flight addFlight(Flight flight) {
		
		flightDaoImpl.save(flight);
		return flight;
	}

	
	
	
	

}
