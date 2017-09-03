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

	List<Preference> findByMilkAndLanguage(Product milk, String language);

	List<Preference> findByMilkAndChannelOfCommunication(Product milk, String channelOfCommunication);

	List<Preference> findByMilkAndLanguageAndChannelOfCommunication(Product milk, String language, String channelOfCommunication);

	List<Preference> findByMilkAndPreferredTimeSlot(Product milk, Time preferredTimeSlot);

	List<Preference> findByMilkAndLanguageAndPreferredTimeSlot(Product milk, String language, Time preferredTimeSlot);

	List<Preference> findByMilkAndChannelOfCommunicationAndPreferredTimeSlot(Product milk, String channelOfCommunication, Time preferredTimeSlot);
	
	List<Preference> findByMilkAndLanguageAndChannelOfCommunicationAndPreferredTimeSlot(Product milk, String language, String channelOfCommunication, Time preferredTimeSlot);

	List<Preference> findByWater(Product water);

	List<Preference> findByWaterAndLanguage(Product water, String language);

	List<Preference> findByWaterAndChannelOfCommunication(Product water, String channelOfCommunication);

	List<Preference> findByWaterAndLanguageAndChannelOfCommunication(Product water, String language, String channelOfCommunication);

	List<Preference> findByWaterAndPreferredTimeSlot(Product water, Time preferredTimeSlot);

	List<Preference> findByWaterAndLanguageAndPreferredTimeSlot(Product water, String language, Time preferredTimeSlot);

	List<Preference> findByWaterAndChannelOfCommunicationAndPreferredTimeSlot(Product water, String channelOfCommunication, Time preferredTimeSlot);
	
	List<Preference> findByWaterAndLanguageAndChannelOfCommunicationAndPreferredTimeSlot(Product water, String language, String channelOfCommunication, Time preferredTimeSlot);

	List<Preference> findByNewspaper(Product newspaper);

	List<Preference> findByNewspaperAndLanguage(Product newspaper, String language);

	List<Preference> findByNewspaperAndChannelOfCommunication(Product newspaper, String channelOfCommunication);

	List<Preference> findByNewspaperAndLanguageAndChannelOfCommunication(Product newspaper, String language, String channelOfCommunication);

	List<Preference> findByNewspaperAndPreferredTimeSlot(Product newspaper, Time preferredTimeSlot);

	List<Preference> findByNewspaperAndLanguageAndPreferredTimeSlot(Product newspaper, String language, Time preferredTimeSlot);

	List<Preference> findByNewspaperAndChannelOfCommunicationAndPreferredTimeSlot(Product newspaper, String channelOfCommunication, Time preferredTimeSlot);
	
	List<Preference> findByNewspaperAndLanguageAndChannelOfCommunicationAndPreferredTimeSlot(Product newspaper, String language, String channelOfCommunication, Time preferredTimeSlot);

	List<Preference> findByMilkAndWater(Product milk, Product water);
	
	List<Preference> findByMilkAndNewspaper(Product milk, Product newspaper);
	
	List<Preference> findByWaterAndNewspaper(Product water, Product newspaper);
	
	List<Preference> findByMilkAndWaterAndNewspaper(Product milk, Product water, Product newspaper);
	
	List<Preference> findByPreferredTimeSlot(Time preferredTimeSlot);
	
}