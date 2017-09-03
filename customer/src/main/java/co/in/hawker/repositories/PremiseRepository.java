package co.in.hawker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.in.hawker.entities.Premise;

/**
 * @author Siva
 * 
 */
public interface PremiseRepository extends JpaRepository<Premise, Integer> {

	Premise findByPremiseId(Long premiseId);

	List<Premise> findByArea(String area);

	List<Premise> findByBuilder(String builder);

	List<Premise> findByCountry(String country);

	List<Premise> findByName(String name);

	List<Premise> findByPincode(int pincode);

	List<Premise> findByState(String state);

	List<Premise> findByType(String type);

	Premise findByNameAndBuilder(String name, String builder);
}