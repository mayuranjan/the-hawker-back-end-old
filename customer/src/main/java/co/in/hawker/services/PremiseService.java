package co.in.hawker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.in.hawker.entities.Premise;
import co.in.hawker.repositories.PremiseRepository;
import co.in.hawker.services.validations.PremiseValidations;

@Service
@Transactional
public class PremiseService extends PremiseValidations {
	// public class CredentialService extends CredentialValidations {
	@Autowired
	private PremiseRepository premiseRespository;

	public Premise addPremise(Premise premise) {

		// null check

		// duplicate check
		handleException(checkIfPremiseIdDoesntExists(premise));

		return premiseRespository.save(premise);
	}

	public List<Premise> getAllPremises() {
		return premiseRespository.findAll();
	}

	public Premise getPremiseByPremiseId(Long premiseId) {
		Premise premise = new Premise();
		premise.setPremiseId(premiseId);

		handleException(checkIfPremiseIdExists(premise));
		return premiseRespository.findByPremiseId(premiseId);
	}

	public List<Premise> getPremisesByArea(String area) {
		Premise premise = new Premise();
		premise.setArea(area);

		handleException(checkIfAreaExists(premise));
		return premiseRespository.findByArea(area);
	}

	public List<Premise> getPremisesByBuilder(String builder) {
		Premise premise = new Premise();
		premise.setBuilder(builder);

		handleException(checkIfBuilderExists(premise));
		return premiseRespository.findByBuilder(builder);
	}

	public List<Premise> getPremisesByCountry(String country) {
		Premise premise = new Premise();
		premise.setCountry(country);

		handleException(checkIfCountryExists(premise));
		return premiseRespository.findByCountry(country);
	}

	public List<Premise> getPremisesByName(String name) {
		Premise premise = new Premise();
		premise.setName(name);

		handleException(checkIfNameExists(premise));
		return premiseRespository.findByName(name);
	}

	public List<Premise> getPremisesByPincode(int pincode) {
		Premise premise = new Premise();
		premise.setPincode(pincode);

		handleException(checkIfPincodeExists(premise));
		return premiseRespository.findByPincode(pincode);
	}

	public List<Premise> getPremisesByState(String state) {
		Premise premise = new Premise();
		premise.setState(state);

		handleException(checkIfStateExists(premise));
		return premiseRespository.findByState(state);
	}

	public List<Premise> getPremisesByType(String type) {
		Premise premise = new Premise();
		premise.setType(type);

		handleException(checkIfTypeExists(premise));
		return premiseRespository.findByType(type);
	}

	public Premise getPremiseByNameAndBuilder(String name, String builder) {
		Premise premise = new Premise();
		premise.setName(name);
		premise.setBuilder(builder);

		handleException(checkIfNameAndBuilderExists(premise));
		return premiseRespository.findByNameAndBuilder(name, builder);
	}

	public Premise updatePremiseByPremiseId(Premise premise) {
		handleException(checkIfPremiseIdDoesntExists(premise));

		premise.setPremiseId(this.getPremiseByPremiseId(premise.getPremiseId()).getPremiseId());
		return premiseRespository.save(premise);
	}

	public List<Premise> updatePremisesByArea(Premise premise) {
		for (Premise eachPremise : this.getPremisesByArea(premise.getArea())) {
			eachPremise.setArea(premise.getArea());
			premiseRespository.save(eachPremise);
		}

		return this.getPremisesByArea(premise.getArea());
	}

	public List<Premise> updatePremisesByBuilder(Premise premise) {
		for (Premise eachPremise : this.getPremisesByBuilder(premise.getBuilder())) {
			eachPremise.setBuilder(premise.getBuilder());
			premiseRespository.save(eachPremise);
		}

		return this.getPremisesByBuilder(premise.getBuilder());
	}

	public List<Premise> updatePremisesByCountry(Premise premise) {
		for (Premise eachPremise : this.getPremisesByCountry(premise.getCountry())) {
			eachPremise.setBuilder(premise.getBuilder());
			premiseRespository.save(eachPremise);
		}

		return this.getPremisesByCountry(premise.getCountry());
	}

	public List<Premise> updatePremisesByName(Premise premise) {
		for (Premise eachPremise : this.getPremisesByName(premise.getName())) {
			eachPremise.setName(premise.getName());
			premiseRespository.save(eachPremise);
		}

		return this.getPremisesByName(premise.getName());
	}

	public List<Premise> updatePremisesByPincode(Premise premise) {
		for (Premise eachPremise : this.getPremisesByPincode(premise.getPincode())) {
			eachPremise.setPincode(premise.getPincode());
			premiseRespository.save(eachPremise);
		}

		return this.getPremisesByPincode(premise.getPincode());
	}

	public List<Premise> updatePremisesByState(Premise premise) {
		for (Premise eachPremise : this.getPremisesByState(premise.getState())) {
			eachPremise.setState(premise.getState());
			premiseRespository.save(eachPremise);
		}

		return this.getPremisesByState(premise.getState());
	}

	public List<Premise> updatePremisesByType(Premise premise) {
		for (Premise eachPremise : this.getPremisesByType(premise.getType())) {
			eachPremise.setType(premise.getType());
			premiseRespository.save(eachPremise);
		}

		return this.getPremisesByType(premise.getType());
	}

	public Premise updatePremiseByNameAndBuilder(Premise premise) {
		return premiseRespository.save(this.getPremiseByNameAndBuilder(premise.getName(), premise.getBuilder()));
	}

}