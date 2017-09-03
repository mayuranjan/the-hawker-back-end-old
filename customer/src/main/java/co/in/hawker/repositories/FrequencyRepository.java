package co.in.hawker.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.in.hawker.entities.Credential;
import co.in.hawker.entities.Frequency;
import co.in.hawker.entities.Product;

/**
 * @author Siva
 * 
 */
public interface FrequencyRepository extends JpaRepository<Frequency, Integer> {
	
	List<Frequency> findByCredential(Credential credential);

	Frequency findByFrequencyId(Long frequencyId);
	
	List<Frequency> findByCredentialAndActive(Credential credential, boolean active);

	List<Frequency> findByCredentialAndProduct(Credential credential, Product product);

	List<Frequency> findByCredentialAndStartDate(Credential credential, Date startDate);

	List<Frequency> findByCredentialAndEndDate(Credential credential, Date endDate);

	Frequency findByCredentialAndProductAndActive(Credential credential, Product product, boolean active);

	List<Frequency> findByCredentialAndStartDateAndActive(Credential credential, Date startDate, boolean active);

	List<Frequency> findByCredentialAndEndDateAndActive(Credential credential, Date endDate, boolean active);
	
}