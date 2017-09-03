package co.in.hawker.repositories;

import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.in.hawker.entities.Credential;
import co.in.hawker.entities.Preference;
import co.in.hawker.entities.Product;

/**
 * @author Siva
 * 
 */
public interface PreferenceRepository extends JpaRepository<Preference, Integer> {
	
	Preference findByPreferenceId(Long preferenceId);

	Preference findByCredential(Credential credential);

	List<Preference> findByChannelOfCommunication(String channelOfCommunication);

	List<Preference> findByLanguage(String language);

	List<Preference> findByMilk(Product milk);

	List<Preference> findByWater(Product water);

	List<Preference> findByPreferredTimeSlot(Time preferredTimeSlot);
	
}