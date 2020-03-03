package pl.wpulik.spaceflights.dao;


import pl.wpulik.spaceflights.model.Flight;
import pl.wpulik.spaceflights.model.Tourist;

public interface FlightDao extends GenericDao<Flight, Long>{
	
	void addTouristToFlight(Long flightId, Tourist tourist);
	void removeTouristFromFlight(Long flightId, Tourist tourist);

}
