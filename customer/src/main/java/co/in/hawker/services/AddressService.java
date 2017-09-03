package co.in.hawker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.in.hawker.entities.Address;
import co.in.hawker.entities.Credential;
import co.in.hawker.repositories.AddressRepository;
import co.in.hawker.services.validations.AddressValidations;

@Service
@Transactional
public class AddressService extends AddressValidations {
	// public class CredentialService extends CredentialValidations {
	@Autowired
	private AddressRepository addressRespository;

	public Address addAddress(Address address) {

		// null check
		handleException(checkIfCredentialIsNotNull(address));

		// duplicate check
		handleException(checkIfAddressIdDoesntExists(address));

		return addressRespository.save(address);
	}

	public List<Address> getAllAddresses() {
		return addressRespository.findAll();
	}

	public List<Address> getAddressesByCredential(Credential credential) {
		Address address = new Address();
		address.setCredential(credential);

		handleException(checkIfCredentialExists(address));
		return addressRespository.findByCredential(address.getCredential());
	}

	public Address getAddressByAddressId(Long addressId) {
		Address address = new Address();
		address.setAddressId(addressId);

		handleException(checkIfAddressIdDoesntExists(address));
		return addressRespository.findByAddressId(addressId);
	}
	
	public Address updateAddressByAddressId(Address address) {
		handleException(checkIfAddressIdExists(address));

		address.setAddressId(this.getAddressByAddressId(address.getAddressId()).getAddressId());
		return addressRespository.save(address);
	}

	public void deleteAddressesByCredential(Address address) {
		handleException(checkIfCredentialExists(address));

		for (Address eachAddress : this.getAddressesByCredential(address.getCredential())) {
			address.setAddressId(eachAddress.getAddressId());
			addressRespository.delete(address);
		}
		return;
	}

	public void deleteAddressByAddressId(Address address) {
		handleException(checkIfAddressIdExists(address));
		addressRespository.delete(address);
		return;
	}

}
