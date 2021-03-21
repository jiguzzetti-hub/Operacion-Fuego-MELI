package fireOperation.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fireOperation.entities.Satellite;

public class SatellitesRepositoryImpl {
	
	@PersistenceContext
    private EntityManager entityManager;

    public List<Satellite> findOrderedByInsertionDateLimitedTo(int limit) {
        return entityManager.createQuery("SELECT s FROM Satellite s ORDER BY s.insertionDate",
        		Satellite.class).setMaxResults(limit).getResultList();
    }

}
