package co.in.hawker.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.in.hawker.entities.Credential;
import co.in.hawker.entities.Profile;
import co.in.hawker.repositories.ProfileRepository;
import co.in.hawker.services.validations.ProfileValidations;

@Service
@Transactional
public class ProfileService extends ProfileValidations {
	// public class CredentialService extends CredentialValidations {
	@Autowired
	private ProfileRepository profileRespository;

	public Profile addPremise(Profile profile) {

		// null check

		// duplicate check
		handleException(checkIfProfileIdDoesntExists(profile));
		return profileRespository.save(profile);
	}

	public List<Profile> getAllProfiles() {
		return profileRespository.findAll();
	}

	public Profile getProfileByProfileId(Long premiseId) {
		Profile profile = new Profile();
		profile.setProfileId(premiseId);

		handleException(checkIfProfileIdExists(profile));
		return profileRespository.findByProfileId(premiseId);
	}

	public Profile getProfileByEmailId(String emailId) {
		Profile profile = new Profile();
		profile.setEmailId(emailId);

		handleException(checkIfEmailIdExists(profile));
		return profileRespository.findByEmailId(emailId);
	}

	public Profile getProfileByMobileNumber(int mobileNumber) {
		Profile profile = new Profile();
		profile.setMobileNumber(mobileNumber);

		handleException(checkIfEmailIdExists(profile));
		return profileRespository.findByMobileNumber(mobileNumber);
	}

	public List<Profile> getProfilesByDateOfBirth(Date dateOfBirth) {
		Profile profile = new Profile();
		profile.setDateOfBirth(dateOfBirth);

		handleException(checkIfEmailIdExists(profile));
		return profileRespository.findByDateOfBirth(dateOfBirth);
	}

	public List<Profile> getProfilesBySex(String sex) {
		Profile profile = new Profile();
		profile.setSex(sex);

		handleException(checkIfSexExists(profile));
		return profileRespository.findBySex(sex);
	}

	public List<Profile> getProfilesByFirstName(String firstName) {
		Profile profile = new Profile();
		profile.setFirstName(firstName);

		handleException(checkIfSexExists(profile));
		return profileRespository.findByFirstName(firstName);
	}

	public List<Profile> getProfilesByLastName(String lastName) {
		Profile profile = new Profile();
		profile.setLastName(lastName);

		handleException(checkIfSexExists(profile));
		return profileRespository.findByLastName(lastName);
	}

	public List<Profile> getProfilesByFirstNameAndLastName(String firstName, String lastName) {
		Profile profile = new Profile();
		profile.setFirstName(firstName);
		profile.setLastName(lastName);

		handleException(checkIfFirstNameAndLastNameExists(profile));
		return profileRespository.findByFirstNameAndLastName(firstName, lastName);
	}

	public Profile getProfileByCredential(Credential credential) {
		Profile profile = new Profile();
		profile.setCredential(credential);
		
		handleException(checkIfCredentialExists(profile));
		return profileRespository.findByCredential(credential);
	}

	public void deleteProfileByProfileId(Profile profile) {
		profileRespository.delete(profile);
		return;
	}

	public void deleteProfileByCredential(Profile profile) {
		profileRespository.delete(this.getProfileByCredential(profile.getCredential()));
		return;
	}

	public void deleteProfilesByFirstName(Profile profile) {
		for (Profile eachProfile : this.getProfilesByFirstName(profile.getFirstName())) {
			profileRespository.delete(eachProfile);
		}
		return;
	}

	public void deleteProfilesByLastName(Profile profile) {
		for (Profile eachProfile : this.getProfilesByLastName(profile.getLastName())) {
			profileRespository.delete(eachProfile);
		}
		return;
	}

	public void deleteProfilesByFirstNameAndLastName(Profile profile) {
		for (Profile eachProfile : this.getProfilesByFirstNameAndLastName(profile.getFirstName(), profile.getLastName())) {
			profileRespository.delete(eachProfile);
		}
		return;
	}

	public void deleteProfilesByDateOfBirth(Profile profile) {
		for (Profile eachProfile : this.getProfilesByDateOfBirth(profile.getDateOfBirth())) {
			profileRespository.delete(eachProfile);
		}
		return;
	}

	public void deleteProfilesBySex(Profile profile) {
		for (Profile eachProfile : this.getProfilesBySex(profile.getSex())) {
			profileRespository.delete(eachProfile);
		}
		return;
	}
}
