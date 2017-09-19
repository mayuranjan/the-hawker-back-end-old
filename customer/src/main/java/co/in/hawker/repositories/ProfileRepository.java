package co.in.hawker.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.in.hawker.entities.Credential;
import co.in.hawker.entities.Profile;

/**
 * @author Siva
 * 
 */
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	
	Profile findByEmailId(String emailId);

	Profile findByMobileNumber(int mobileNumber);

	List<Profile> findByDateOfBirth(Date dateOfBirth);

	Profile findByCredential(Credential credential);

	List<Profile> findByFirstName(String firstName);

	List<Profile> findByLastName(String lastName);

	List<Profile> findByFirstNameAndLastName(String firstName, String lastName);

	Profile findByProfileId(Long profileId);

	List<Profile> findBySex(String sex);

	List<Profile> findByStatus(String status);
	
}