package co.in.hawker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.in.hawker.entities.Credential;

/**
 * @author Siva
 * 
 */
public interface CredentialRepository extends JpaRepository<Credential, Integer> {
	
	Credential findByEmailId(String emailId);

	Credential findByUsername(String username);

	Credential findByMobileNumber(int mobileNumber);

	Credential findByCredentialId(Long credentialId);

	List<Credential> findByMasterCredentialId(Long masterCredentialId);
	
}