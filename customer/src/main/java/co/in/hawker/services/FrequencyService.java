package co.in.hawker.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.in.hawker.entities.Credential;
import co.in.hawker.entities.Frequency;
import co.in.hawker.entities.Product;
import co.in.hawker.repositories.FrequencyRepository;
import co.in.hawker.services.validations.FrequencyValidations;

@Service
@Transactional
public class FrequencyService extends FrequencyValidations {

	@Autowired
	private FrequencyRepository frequencyRespository;

	public Frequency addFrequency(Frequency frequency) {

		// duplicate check
		handleException(checkIfFrequencyIdDoesntExists(frequency));
		return frequencyRespository.save(frequency);
	}

	public List<Frequency> getAllFrequencies() {
		return frequencyRespository.findAll();
	}

	public Frequency getFrequencyByFrequencyId(Long frequencyId) {
		Frequency frequency = new Frequency();
		frequency.setFrequencyId(frequencyId);

		handleException(checkIfFrequencyIdDoesntExists(frequency));
		return frequencyRespository.findByFrequencyId(frequencyId);
	}

	public List<Frequency> getFrequenciesByCredential(Credential credential) {
		return frequencyRespository.findByCredential(credential);
	}

	public List<Frequency> getFrequenciesByCredentialAndStatus(Credential credential, boolean active) {
		return frequencyRespository.findByCredentialAndActive(credential, active);
	}

	public List<Frequency> getFrequenciesByCredentialAndProduct(Credential credential, Product product) {
		return frequencyRespository.findByCredentialAndProduct(credential, product);
	}

	public List<Frequency> getFrequenciesByCredentialAndStartDate(Credential credential, Date startDate) {
		return frequencyRespository.findByCredentialAndStartDate(credential, startDate);
	}

	public List<Frequency> getFrequenciesByCredentialAndEndDate(Credential credential, Date endDate) {
		return frequencyRespository.findByCredentialAndEndDate(credential, endDate);
	}

	public Frequency getFrequencyByCredentialAndProductAndStatus(Credential credential, Product product,
			boolean active) {
		return frequencyRespository.findByCredentialAndProductAndActive(credential, product, active);
	}

	public List<Frequency> getFrequenciesByCredentialAndStartDateAndStatus(Credential credential, Date startDate,
			boolean active) {
		return frequencyRespository.findByCredentialAndStartDateAndActive(credential, startDate, active);
	}

	public List<Frequency> getFrequenciesByCredentialAndEndDateAndStatus(Credential credential, Date endDate,
			boolean active) {
		return frequencyRespository.findByCredentialAndEndDateAndActive(credential, endDate, active);
	}

	public Frequency updateFrequencyByFrequencyId(Frequency frequency) {
		handleException(checkIfFrequencyIdExists(frequency));

		frequency.setFrequencyId(this.getFrequencyByFrequencyId(frequency.getFrequencyId()).getFrequencyId());
		return frequencyRespository.save(frequency);
	}

	public List<Frequency> updateFrequenciesStatusByCredential(Frequency frequency) {
		handleException(checkIfFrequencyCredentialExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredential(frequency.getCredential())) {
			eachFrequency.setActive(frequency.isActive());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredential(frequency.getCredential());
	}

	public List<Frequency> updateFrequenciesStartDateByCredential(Frequency frequency) {
		handleException(checkIfFrequencyCredentialExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredential(frequency.getCredential())) {
			eachFrequency.setStartDate(frequency.getStartDate());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredential(frequency.getCredential());
	}

	public List<Frequency> updateFrequenciesEndDateByCredential(Frequency frequency) {
		handleException(checkIfFrequencyCredentialExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredential(frequency.getCredential())) {
			eachFrequency.setEndDate(frequency.getEndDate());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredential(frequency.getCredential());
	}

	public List<Frequency> updateFrequenciesFrequencyTypeByCredential(Frequency frequency) {
		handleException(checkIfFrequencyCredentialExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredential(frequency.getCredential())) {
			eachFrequency.setFrequencyType(frequency.getFrequencyType());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredential(frequency.getCredential());
	}

	public List<Frequency> updateFrequenciesStatusByCredentialAndStatus(Frequency frequency) {
		handleException(checkIfFrequencyCredentialAndStatusExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndStatus(frequency.getCredential(),
				frequency.isActive())) {
			eachFrequency.setActive(frequency.isActive());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredentialAndStatus(frequency.getCredential(), frequency.isActive());
	}

	public List<Frequency> updateFrequenciesStartDateByCredentialAndStatus(Frequency frequency) {
		handleException(checkIfFrequencyCredentialAndStatusExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndStatus(frequency.getCredential(),
				frequency.isActive())) {
			eachFrequency.setStartDate(frequency.getStartDate());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredentialAndStatus(frequency.getCredential(), frequency.isActive());
	}

	public List<Frequency> updateFrequenciesEndDateByCredentialAndStatus(Frequency frequency) {
		handleException(checkIfFrequencyCredentialAndStatusExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndStatus(frequency.getCredential(),
				frequency.isActive())) {
			eachFrequency.setEndDate(frequency.getEndDate());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredentialAndStatus(frequency.getCredential(), frequency.isActive());
	}

	public List<Frequency> updateFrequenciesFrequencyTypeByCredentialAndStatus(Frequency frequency) {
		handleException(checkIfFrequencyCredentialAndStatusExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndStatus(frequency.getCredential(),
				frequency.isActive())) {
			eachFrequency.setFrequencyType(frequency.getFrequencyType());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredentialAndStatus(frequency.getCredential(), frequency.isActive());
	}

	public List<Frequency> updateFrequenciesStatusByCredentialAndProduct(Frequency frequency) {
		handleException(checkIfFrequencyCredentialExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndProduct(frequency.getCredential(),
				frequency.getProduct())) {
			eachFrequency.setActive(frequency.isActive());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredentialAndProduct(frequency.getCredential(), frequency.getProduct());
	}

	public List<Frequency> updateFrequenciesStartDateByCredentialAndProduct(Frequency frequency) {
		handleException(checkIfFrequencyCredentialExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndProduct(frequency.getCredential(),
				frequency.getProduct())) {
			eachFrequency.setStartDate(frequency.getStartDate());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredentialAndProduct(frequency.getCredential(), frequency.getProduct());
	}

	public List<Frequency> updateFrequenciesEndDateByCredentialAndProduct(Frequency frequency) {
		handleException(checkIfFrequencyCredentialExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndProduct(frequency.getCredential(),
				frequency.getProduct())) {
			eachFrequency.setEndDate(frequency.getEndDate());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredentialAndProduct(frequency.getCredential(), frequency.getProduct());
	}

	public List<Frequency> updateFrequenciesFrequencyTypeByCredentialAndProduct(Frequency frequency) {
		handleException(checkIfFrequencyCredentialExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndProduct(frequency.getCredential(),
				frequency.getProduct())) {
			eachFrequency.setFrequencyType(frequency.getFrequencyType());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredentialAndProduct(frequency.getCredential(), frequency.getProduct());
	}

	public List<Frequency> updateFrequenciesStatusByCredentialAndStartDate(Frequency frequency) {
		handleException(checkIfFrequencyCredentialExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndStartDate(frequency.getCredential(),
				frequency.getStartDate())) {
			eachFrequency.setActive(frequency.isActive());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredentialAndStartDate(frequency.getCredential(), frequency.getStartDate());
	}

	public List<Frequency> updateFrequenciesStartDateByCredentialAndStartDate(Frequency frequency) {
		handleException(checkIfFrequencyCredentialExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndStartDate(frequency.getCredential(),
				frequency.getStartDate())) {
			eachFrequency.setStartDate(frequency.getStartDate());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredentialAndStartDate(frequency.getCredential(), frequency.getStartDate());
	}

	public List<Frequency> updateFrequenciesEndDateByCredentialAndStartDate(Frequency frequency) {
		handleException(checkIfFrequencyCredentialExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndStartDate(frequency.getCredential(),
				frequency.getStartDate())) {
			eachFrequency.setEndDate(frequency.getEndDate());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredentialAndStartDate(frequency.getCredential(), frequency.getStartDate());
	}

	public List<Frequency> updateFrequenciesFrequencyTypeByCredentialAndStartDate(Frequency frequency) {
		handleException(checkIfFrequencyCredentialExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndStartDate(frequency.getCredential(),
				frequency.getStartDate())) {
			eachFrequency.setFrequencyType(frequency.getFrequencyType());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredentialAndStartDate(frequency.getCredential(), frequency.getStartDate());
	}

	public List<Frequency> updateFrequenciesStatusByCredentialAndEndDate(Frequency frequency) {
		handleException(checkIfFrequencyCredentialExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndEndDate(frequency.getCredential(),
				frequency.getEndDate())) {
			eachFrequency.setActive(frequency.isActive());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredentialAndEndDate(frequency.getCredential(), frequency.getEndDate());
	}

	public List<Frequency> updateFrequenciesStartDateByCredentialAndEndDate(Frequency frequency) {
		handleException(checkIfFrequencyCredentialExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndEndDate(frequency.getCredential(),
				frequency.getEndDate())) {
			eachFrequency.setStartDate(frequency.getStartDate());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredentialAndEndDate(frequency.getCredential(), frequency.getEndDate());
	}

	public List<Frequency> updateFrequenciesEndDateByCredentialAndEndDate(Frequency frequency) {
		handleException(checkIfFrequencyCredentialExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndEndDate(frequency.getCredential(),
				frequency.getEndDate())) {
			eachFrequency.setEndDate(frequency.getEndDate());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredentialAndEndDate(frequency.getCredential(), frequency.getEndDate());
	}

	public List<Frequency> updateFrequenciesFrequencyTypeByCredentialAndEndDate(Frequency frequency) {
		handleException(checkIfFrequencyCredentialExists(frequency));

		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndEndDate(frequency.getCredential(),
				frequency.getEndDate())) {
			eachFrequency.setFrequencyType(frequency.getFrequencyType());
			frequencyRespository.save(frequency);
		}
		return this.getFrequenciesByCredentialAndEndDate(frequency.getCredential(), frequency.getEndDate());
	}

	public Frequency updateFrequencyByCredentialAndProductAndStatus(Frequency frequency) {
		handleException(checkIfFrequencyCredentialExists(frequency));

		return frequencyRespository.save(this.getFrequencyByCredentialAndProductAndStatus(frequency.getCredential(),
				frequency.getProduct(), frequency.isActive()));
	}

	public void deleteFrequenciesByCredential(Frequency frequency) {
		for (Frequency eachFrequency : this.getFrequenciesByCredential(frequency.getCredential())) {
			frequencyRespository.delete(eachFrequency);
		}
		return;
	}

	public void deleteFrequenciesByCredentialAndStatus(Frequency frequency) {
		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndStatus(frequency.getCredential(),
				frequency.isActive())) {
			frequencyRespository.delete(eachFrequency);
		}
		return;
	}

	public void deleteFrequenciesByCredentialAndProduct(Frequency frequency) {
		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndProduct(frequency.getCredential(),
				frequency.getProduct())) {
			frequencyRespository.delete(eachFrequency);
		}
		return;
	}

	public void deleteFrequenciesByCredentialAndStartDate(Frequency frequency) {
		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndStartDate(frequency.getCredential(),
				frequency.getStartDate())) {
			frequencyRespository.delete(eachFrequency);
		}
		return;
	}

	public void deleteFrequenciesByCredentialAndEndDate(Frequency frequency) {
		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndProduct(frequency.getCredential(),
				frequency.getProduct())) {
			frequencyRespository.delete(eachFrequency);
		}
		return;
	}

	public void deleteFrequencyByCredentialAndProductAndStatus(Frequency frequency) {
		frequency.setFrequencyId(this.getFrequencyByCredentialAndProductAndStatus(frequency.getCredential(),
				frequency.getProduct(), frequency.isActive()).getFrequencyId());
		frequencyRespository.delete(frequency);
		return;
	}

	public void deleteFrequenciesByCredentialAndStartDateAndStatus(Frequency frequency) {
		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndStartDateAndStatus(frequency.getCredential(),
				frequency.getStartDate(), frequency.isActive())) {
			frequencyRespository.delete(eachFrequency);
		}
		return;
	}

	public void deleteFrequenciesByCredentialAndEndDateAndStatus(Frequency frequency) {
		for (Frequency eachFrequency : this.getFrequenciesByCredentialAndEndDateAndStatus(frequency.getCredential(),
				frequency.getEndDate(), frequency.isActive())) {
			frequencyRespository.delete(eachFrequency);
		}
		return;
	}

	public void deleteFrequencyByFrequencyId(Frequency frequency) {
		handleException(checkIfFrequencyIdExists(frequency));
		frequencyRespository.delete(frequency);
		return;
	}
}
