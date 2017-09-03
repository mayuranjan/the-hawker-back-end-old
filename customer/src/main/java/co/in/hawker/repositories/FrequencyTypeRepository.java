package co.in.hawker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.in.hawker.entities.FrequencyType;

/**
 * 
 * @author RB071
 *
 */
public interface FrequencyTypeRepository extends JpaRepository<FrequencyType, Integer> {

	FrequencyType findByFrequencyTypeId(Long frequencyTypeId);
	
}