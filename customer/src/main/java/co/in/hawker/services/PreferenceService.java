package co.in.hawker.services;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.in.hawker.entities.Credential;
import co.in.hawker.entities.Preference;
import co.in.hawker.entities.Product;
import co.in.hawker.repositories.PreferenceRepository;
import co.in.hawker.services.validations.PreferenceValidations;

@Service
@Transactional
public class PreferenceService extends PreferenceValidations {
	// public class CredentialService extends CredentialValidations {
	@Autowired
	private PreferenceRepository preferenceRespository;

	public Preference addPreference(Preference preference) {

		// null check

		// duplicate check
		handleException(checkIfPreferenceIdDoesntExists(preference));

		return preferenceRespository.save(preference);
	}

	public List<Preference> getAllPreferences() {
		return preferenceRespository.findAll();
	}

	public Preference getPreferenceByPreferenceId(Long preferenceId) {
		Preference preference = new Preference();
		preference.setPreferenceId(preferenceId);

		handleException(checkIfPreferenceIdExists(preference));
		return preferenceRespository.findByPreferenceId(preferenceId);
	}

	public Preference getPreferenceByCredential(Credential credential) {
		Preference preference = new Preference();
		preference.setCredential(credential);

		handleException(checkIfPreferenceCredentialExists(preference));
		return preferenceRespository.findByCredential(credential);
	}

	public List<Preference> getPreferenceByChannelOfCommunication(String channelOfCommunication) {
		Preference preference = new Preference();
		preference.setChannelOfCommunication(channelOfCommunication);

		handleException(checkIfPreferenceChannelOfCommunicationExists(preference));
		return preferenceRespository.findByChannelOfCommunication(channelOfCommunication);
	}

	public List<Preference> getPreferenceByLanguage(String language) {
		Preference preference = new Preference();
		preference.setLanguage(language);

		handleException(checkIfPreferenceLanguageExists(preference));
		return preferenceRespository.findByLanguage(language);
	}

	public List<Preference> getPreferenceByPaymentMode(String paymentMode) {
		Preference preference = new Preference();
		preference.setPaymentMode(paymentMode);

		handleException(checkIfPreferencePaymentModeExists(preference));
		return preferenceRespository.findByPaymentMode(paymentMode);
	}

	public List<Preference> getPreferenceByMilk(Product milk) {
		Preference preference = new Preference();
		preference.setMilk(milk);

		handleException(checkIfPreferenceProductMilkExists(preference));
		return preferenceRespository.findByMilk(milk);
	}

	public List<Preference> getPreferenceByMilkAndLanguage(Product milk, String language) {
		Preference preference = new Preference();
		preference.setMilk(milk);
		preference.setLanguage(language);

		handleException(checkIfPreferenceProductMilkExists(preference));
		handleException(checkIfPreferenceLanguageExists(preference));
		return preferenceRespository.findByMilkAndLanguage(milk, language);
	}

	public List<Preference> getPreferenceByMilkAndChannelOfCommunication(Product milk, String channelOfCommunication) {
		Preference preference = new Preference();
		preference.setMilk(milk);
		preference.setChannelOfCommunication(channelOfCommunication);

		handleException(checkIfPreferenceProductMilkExists(preference));
		handleException(checkIfPreferenceChannelOfCommunicationExists(preference));
		return preferenceRespository.findByMilkAndChannelOfCommunication(milk, channelOfCommunication);
	}

	public List<Preference> getPreferenceByMilkAndLanguageAndChannelOfCommunication(Product milk, String language,
			String channelOfCommunication) {
		Preference preference = new Preference();
		preference.setMilk(milk);
		preference.setLanguage(language);
		preference.setChannelOfCommunication(channelOfCommunication);

		handleException(checkIfPreferenceProductMilkExists(preference));
		handleException(checkIfPreferenceLanguageExists(preference));
		handleException(checkIfPreferenceChannelOfCommunicationExists(preference));
		return preferenceRespository.findByMilkAndLanguageAndChannelOfCommunication(milk, language,
				channelOfCommunication);
	}

	public List<Preference> getPreferenceByMilkAndPreferredTimeSlot(Product milk, Time preferredTimeSlot) {
		Preference preference = new Preference();
		preference.setMilk(milk);
		preference.setPreferredTimeSlot(preferredTimeSlot);

		handleException(checkIfPreferenceProductMilkExists(preference));
		handleException(checkIfPreferencePreferredTimeSlotExists(preference));
		return preferenceRespository.findByMilkAndPreferredTimeSlot(milk, preferredTimeSlot);
	}

	public List<Preference> getPreferenceByMilkAndLanguageAndPreferredTimeSlot(Product milk, String language,
			Time preferredTimeSlot) {
		Preference preference = new Preference();
		preference.setMilk(milk);
		preference.setLanguage(language);
		preference.setPreferredTimeSlot(preferredTimeSlot);

		handleException(checkIfPreferenceProductMilkExists(preference));
		handleException(checkIfPreferenceLanguageExists(preference));
		handleException(checkIfPreferencePreferredTimeSlotExists(preference));
		return preferenceRespository.findByMilkAndLanguageAndPreferredTimeSlot(milk, language, preferredTimeSlot);
	}

	public List<Preference> getPreferenceByMilkAndChannelOfCommunicationAndPreferredTimeSlot(Product milk,
			String channelOfCommunication, Time preferredTimeSlot) {
		Preference preference = new Preference();
		preference.setMilk(milk);
		preference.setChannelOfCommunication(channelOfCommunication);
		preference.setPreferredTimeSlot(preferredTimeSlot);

		handleException(checkIfPreferenceProductMilkExists(preference));
		handleException(checkIfPreferenceLanguageExists(preference));
		handleException(checkIfPreferencePreferredTimeSlotExists(preference));
		return preferenceRespository.findByMilkAndChannelOfCommunicationAndPreferredTimeSlot(milk,
				channelOfCommunication, preferredTimeSlot);
	}

	public List<Preference> getPreferenceByMilkAndLanguageAndChannelOfCommunicationAndPreferredTimeSlot(Product milk,
			String language, String channelOfCommunication, Time preferredTimeSlot) {
		Preference preference = new Preference();
		preference.setMilk(milk);
		preference.setChannelOfCommunication(channelOfCommunication);
		preference.setLanguage(language);
		preference.setPreferredTimeSlot(preferredTimeSlot);

		handleException(checkIfPreferenceProductMilkExists(preference));
		handleException(checkIfPreferenceLanguageExists(preference));
		handleException(checkIfPreferencePreferredTimeSlotExists(preference));
		return preferenceRespository.findByMilkAndLanguageAndChannelOfCommunicationAndPreferredTimeSlot(milk, language,
				channelOfCommunication, preferredTimeSlot);
	}

	public List<Preference> getPreferenceByWater(Product water) {
		Preference preference = new Preference();
		preference.setWater(water);

		handleException(checkIfPreferenceProductWaterExists(preference));
		return preferenceRespository.findByWater(water);
	}

	public List<Preference> getPreferenceByWaterAndLanguage(Product water, String language) {
		Preference preference = new Preference();
		preference.setWater(water);
		preference.setLanguage(language);

		handleException(checkIfPreferenceProductWaterExists(preference));
		handleException(checkIfPreferenceLanguageExists(preference));
		return preferenceRespository.findByWaterAndLanguage(water, language);
	}

	public List<Preference> getPreferenceByWaterAndChannelOfCommunication(Product water, String channelOfCommunication) {
		Preference preference = new Preference();
		preference.setWater(water);
		preference.setChannelOfCommunication(channelOfCommunication);

		handleException(checkIfPreferenceProductWaterExists(preference));
		handleException(checkIfPreferenceChannelOfCommunicationExists(preference));
		return preferenceRespository.findByWaterAndChannelOfCommunication(water, channelOfCommunication);
	}

	public List<Preference> getPreferenceByWaterAndLanguageAndChannelOfCommunication(Product water, String language,
			String channelOfCommunication) {
		Preference preference = new Preference();
		preference.setWater(water);
		preference.setLanguage(language);
		preference.setChannelOfCommunication(channelOfCommunication);

		handleException(checkIfPreferenceProductWaterExists(preference));
		handleException(checkIfPreferenceLanguageExists(preference));
		handleException(checkIfPreferenceChannelOfCommunicationExists(preference));
		return preferenceRespository.findByWaterAndLanguageAndChannelOfCommunication(water, language,
				channelOfCommunication);
	}

	public List<Preference> getPreferenceByWaterAndPreferredTimeSlot(Product water, Time preferredTimeSlot) {
		Preference preference = new Preference();
		preference.setWater(water);
		preference.setPreferredTimeSlot(preferredTimeSlot);

		handleException(checkIfPreferenceProductWaterExists(preference));
		handleException(checkIfPreferencePreferredTimeSlotExists(preference));
		return preferenceRespository.findByWaterAndPreferredTimeSlot(water, preferredTimeSlot);
	}

	public List<Preference> getPreferenceByWaterAndLanguageAndPreferredTimeSlot(Product water, String language,
			Time preferredTimeSlot) {
		Preference preference = new Preference();
		preference.setWater(water);
		preference.setLanguage(language);
		preference.setPreferredTimeSlot(preferredTimeSlot);

		handleException(checkIfPreferenceProductWaterExists(preference));
		handleException(checkIfPreferenceLanguageExists(preference));
		handleException(checkIfPreferencePreferredTimeSlotExists(preference));
		return preferenceRespository.findByWaterAndLanguageAndPreferredTimeSlot(water, language, preferredTimeSlot);
	}

	public List<Preference> getPreferenceByWaterAndChannelOfCommunicationAndPreferredTimeSlot(Product water,
			String channelOfCommunication, Time preferredTimeSlot) {
		Preference preference = new Preference();
		preference.setWater(water);
		preference.setChannelOfCommunication(channelOfCommunication);
		preference.setPreferredTimeSlot(preferredTimeSlot);

		handleException(checkIfPreferenceProductWaterExists(preference));
		handleException(checkIfPreferenceLanguageExists(preference));
		handleException(checkIfPreferencePreferredTimeSlotExists(preference));
		return preferenceRespository.findByWaterAndChannelOfCommunicationAndPreferredTimeSlot(water,
				channelOfCommunication, preferredTimeSlot);
	}

	public List<Preference> getPreferenceByWaterAndLanguageAndChannelOfCommunicationAndPreferredTimeSlot(Product water,
			String language, String channelOfCommunication, Time preferredTimeSlot) {
		Preference preference = new Preference();
		preference.setWater(water);
		preference.setChannelOfCommunication(channelOfCommunication);
		preference.setLanguage(language);
		preference.setPreferredTimeSlot(preferredTimeSlot);

		handleException(checkIfPreferenceProductWaterExists(preference));
		handleException(checkIfPreferenceLanguageExists(preference));
		handleException(checkIfPreferencePreferredTimeSlotExists(preference));
		return preferenceRespository.findByWaterAndLanguageAndChannelOfCommunicationAndPreferredTimeSlot(water, language,
				channelOfCommunication, preferredTimeSlot);
	}

	public List<Preference> getPreferenceByNewspaper(Product newspaper) {
		Preference preference = new Preference();
		preference.setNewspaper(newspaper);

		handleException(checkIfPreferenceProductNewspaperExists(preference));
		return preferenceRespository.findByNewspaper(newspaper);
	}

	public List<Preference> getPreferenceByNewspaperAndLanguage(Product newspaper, String language) {
		Preference preference = new Preference();
		preference.setNewspaper(newspaper);
		preference.setLanguage(language);

		handleException(checkIfPreferenceProductNewspaperExists(preference));
		handleException(checkIfPreferenceLanguageExists(preference));
		return preferenceRespository.findByNewspaperAndLanguage(newspaper, language);
	}

	public List<Preference> getPreferenceByNewspaperAndChannelOfCommunication(Product newspaper, String channelOfCommunication) {
		Preference preference = new Preference();
		preference.setNewspaper(newspaper);
		preference.setChannelOfCommunication(channelOfCommunication);

		handleException(checkIfPreferenceProductNewspaperExists(preference));
		handleException(checkIfPreferenceChannelOfCommunicationExists(preference));
		return preferenceRespository.findByNewspaperAndChannelOfCommunication(newspaper, channelOfCommunication);
	}

	public List<Preference> getPreferenceByNewspaperAndLanguageAndChannelOfCommunication(Product newspaper, String language,
			String channelOfCommunication) {
		Preference preference = new Preference();
		preference.setNewspaper(newspaper);
		preference.setLanguage(language);
		preference.setChannelOfCommunication(channelOfCommunication);

		handleException(checkIfPreferenceProductNewspaperExists(preference));
		handleException(checkIfPreferenceLanguageExists(preference));
		handleException(checkIfPreferenceChannelOfCommunicationExists(preference));
		return preferenceRespository.findByNewspaperAndLanguageAndChannelOfCommunication(newspaper, language,
				channelOfCommunication);
	}

	public List<Preference> getPreferenceByNewspaperAndPreferredTimeSlot(Product newspaper, Time preferredTimeSlot) {
		Preference preference = new Preference();
		preference.setNewspaper(newspaper);
		preference.setPreferredTimeSlot(preferredTimeSlot);

		handleException(checkIfPreferenceProductNewspaperExists(preference));
		handleException(checkIfPreferencePreferredTimeSlotExists(preference));
		return preferenceRespository.findByNewspaperAndPreferredTimeSlot(newspaper, preferredTimeSlot);
	}

	public List<Preference> getPreferenceByNewspaperAndLanguageAndPreferredTimeSlot(Product newspaper, String language,
			Time preferredTimeSlot) {
		Preference preference = new Preference();
		preference.setNewspaper(newspaper);
		preference.setLanguage(language);
		preference.setPreferredTimeSlot(preferredTimeSlot);

		handleException(checkIfPreferenceProductNewspaperExists(preference));
		handleException(checkIfPreferenceLanguageExists(preference));
		handleException(checkIfPreferencePreferredTimeSlotExists(preference));
		return preferenceRespository.findByNewspaperAndLanguageAndPreferredTimeSlot(newspaper, language, preferredTimeSlot);
	}

	public List<Preference> getPreferenceByNewspaperAndChannelOfCommunicationAndPreferredTimeSlot(Product newspaper,
			String channelOfCommunication, Time preferredTimeSlot) {
		Preference preference = new Preference();
		preference.setNewspaper(newspaper);
		preference.setChannelOfCommunication(channelOfCommunication);
		preference.setPreferredTimeSlot(preferredTimeSlot);

		handleException(checkIfPreferenceProductNewspaperExists(preference));
		handleException(checkIfPreferenceLanguageExists(preference));
		handleException(checkIfPreferencePreferredTimeSlotExists(preference));
		return preferenceRespository.findByWaterAndChannelOfCommunicationAndPreferredTimeSlot(newspaper,
				channelOfCommunication, preferredTimeSlot);
	}

	public List<Preference> getPreferenceByNewspaperAndLanguageAndChannelOfCommunicationAndPreferredTimeSlot(Product newspaper,
			String language, String channelOfCommunication, Time preferredTimeSlot) {
		Preference preference = new Preference();
		preference.setNewspaper(newspaper);
		preference.setChannelOfCommunication(channelOfCommunication);
		preference.setLanguage(language);
		preference.setPreferredTimeSlot(preferredTimeSlot);

		handleException(checkIfPreferenceProductNewspaperExists(preference));
		handleException(checkIfPreferenceLanguageExists(preference));
		handleException(checkIfPreferencePreferredTimeSlotExists(preference));
		return preferenceRespository.findByNewspaperAndLanguageAndChannelOfCommunicationAndPreferredTimeSlot(newspaper, language,
				channelOfCommunication, preferredTimeSlot);
	}

	public List<Preference> getPreferenceByMilkAndWater(Product milk, Product water) {
		Preference preference = new Preference();
		preference.setMilk(milk);
		preference.setWater(water);

		handleException(checkIfPreferenceProductMilkExists(preference));
		handleException(checkIfPreferenceProductWaterExists(preference));
		return preferenceRespository.findByMilkAndWater(milk, water);
	}

	public List<Preference> getPreferenceByMilkAndNewspaper(Product milk, Product newspaper) {
		Preference preference = new Preference();
		preference.setMilk(milk);
		preference.setNewspaper(newspaper);

		handleException(checkIfPreferenceProductMilkExists(preference));
		handleException(checkIfPreferenceProductNewspaperExists(preference));
		return preferenceRespository.findByMilkAndNewspaper(milk, newspaper);
	}

	public List<Preference> getPreferenceByWaterAndNewspaper(Product water, Product newspaper) {
		Preference preference = new Preference();
		preference.setWater(water);
		preference.setNewspaper(newspaper);

		handleException(checkIfPreferenceProductWaterExists(preference));
		handleException(checkIfPreferenceProductNewspaperExists(preference));
		return preferenceRespository.findByWaterAndNewspaper(water, newspaper);
	}

	public List<Preference> getPreferenceByMilkAndWaterAndNewspaper(Product milk, Product water, Product newspaper) {
		Preference preference = new Preference();
		preference.setMilk(milk);
		preference.setWater(water);
		preference.setNewspaper(newspaper);

		handleException(checkIfPreferenceProductMilkExists(preference));
		handleException(checkIfPreferenceProductWaterExists(preference));
		handleException(checkIfPreferenceProductNewspaperExists(preference));
		return preferenceRespository.findByMilkAndWaterAndNewspaper(milk, water, newspaper);
	}

	public List<Preference> getPreferenceByPreferredTimeSlot(Time preferredTimeSlot) {
		Preference preference = new Preference();
		preference.setPreferredTimeSlot(preferredTimeSlot);

		handleException(checkIfPreferencePreferredTimeSlotExists(preference));
		return preferenceRespository.findByPreferredTimeSlot(preferredTimeSlot);
	}

	public List<Preference> getPreferenceByStatus(String status) {
		Preference preference = new Preference();
		preference.setStatus(status);

		handleException(checkIfPreferenceStatusExists(preference));
		return preferenceRespository.findByStatus(status);
	}

	public List<Preference> getPreferenceByCredentialAndStatus(Credential credential, String status) {
		Preference preference = new Preference();
		preference.setCredential(credential);
		preference.setStatus(status);

		handleException(checkIfPreferenceCredentialExists(preference));
		handleException(checkIfPreferenceStatusExists(preference));
		return preferenceRespository.findByCredentialAndStatus(credential, status);
	}

}