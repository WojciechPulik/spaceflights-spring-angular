package pl.wpulik.spaceflights.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.wpulik.spaceflights.model.Tourist;

@Repository
public class TouristDaoImpl implements TouristDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	@Transactional
	public void save(Tourist tourist) {
		entityManager.persist(tourist);
		
	}

	@Override
	@Transactional
	public Tourist get(Long touristId) {
		Tourist resultTourist = entityManager.find(Tourist.class, touristId);
		return resultTourist;
	}

	@Override
	@Transactional
	public void update(Tourist tourist) {
		entityManager.merge(tourist);
		
	}

	@Override
	@Transactional  
	public void remove(Long touristId) {
		Tourist touristToRemove = entityManager.find(Tourist.class, touristId);
		entityManager.remove(touristToRemove);
	
	}

	@Override
	@Transactional
	public List<Tourist> getAll() {
		final String getAll = "SELECT t FROM Tourist t";
		TypedQuery<Tourist> getAllQuery = entityManager.createQuery(getAll, Tourist.class);
		List<Tourist> resultList = getAllQuery.getResultList();
		return resultList;
		
	}
	
	

}
