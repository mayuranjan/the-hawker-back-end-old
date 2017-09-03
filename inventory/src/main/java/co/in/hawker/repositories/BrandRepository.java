package co.in.hawker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.in.hawker.entities.Brand;

/**
 * @author Siva
 * 
 */
public interface BrandRepository extends JpaRepository<Brand, Integer> {

	Brand findByBrandCode(String brandCode);

	Brand findByBrandName(String brandName);

	Brand findByBrandId(Long brandId);
}