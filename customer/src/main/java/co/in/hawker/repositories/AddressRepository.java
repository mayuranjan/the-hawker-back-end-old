package co.in.hawker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.in.hawker.entities.Address;
import co.in.hawker.entities.Credential;
import co.in.hawker.entities.Premise;

/**
 * @author Siva
 * 
 */
public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	Address findByAddressId(Long addressId);

	Address findByPremise(Premise premise);

	Address findByPremiseAndFlatNumber(Premise premise, String flatNumber);

	Address findByPremiseAndBlockNumber(Premise premise, String blockNumber);

	Address findByPremiseAndTowerNumber(Premise premise, String towerNumber);

	Address findByPremiseAndFloorNumber(Premise premise, String floorNumber);

	List<Address> findByCredential(Credential credential);
	
}