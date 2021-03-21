package fireOperation.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import fireOperation.entities.Satellite;

public interface SatellitesRepository extends JpaRepository<Satellite, Long> {
	
		
		
		 	
		
		
		public void save(fireOperation.dtos.Satellite satellite);

	

		
			
	}


