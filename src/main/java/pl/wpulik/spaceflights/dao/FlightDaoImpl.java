package pl.wpulik.spaceflights.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.wpulik.spaceflights.model.Flight;
import pl.wpulik.spaceflights.model.Tourist;

@Repository
public class FlightDaoImpl implements FlightDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	
	@Override
	@Transactional
	public void addTouristToFlight(Long flightId, Tourist tourist) {
		Flight flight = get(flightId);
		if(flight != null) {
				flight.getTourists().add(tourist);
			}
	}
	
	@Override
	@Transactional
	public void save(Flight flight) {
		entityManager.persist(flight);
		
	}

	@Override
	@Transactional
	public Flight get(Long flightId) {
		Flight resultFlight = entityManager.find(Flight.class, flightId);
		return resultFlight;
	}

	@Override
	@Transactional
	public void update(Flight updateFlight) {
		entityManager.merge(updateFlight);
		
	}

	@Override
	@Transactional
	public void remove(Long flightId) {
		Flight flightToRemove = entityManager.find(Flight.class, flightId);
		flightToRemove.getTourists().clear();
		entityManager.remove(flightToRemove);
	}

	@Override
	@Transactional
	public List<Flight> getAll() {
		final String getAll = "Select f From Flight f";
		TypedQuery<Flight> getAllQuery = entityManager.createQuery(getAll, Flight.class);
		List<Flight> resultList = getAllQuery.getResultList();
		return resultList;
	}

	
	@Override
	@Transactional
	public void removeTouristFromFlight(Long flightId, Tourist tourist) {
		Flight flight = entityManager.find(Flight.class, flightId);
		Long id = tourist.getId();
		Set<Tourist> tourists = flight.getTourists();
		for(Iterator<Tourist> t = tourists.iterator(); t.hasNext();) {
			Long next = t.next().getId();
			if(next.equals(id))
					t.remove();
		}
		
	}
	
	
	
	 

}
