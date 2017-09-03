package co.in.hawker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.in.hawker.entities.Product;

/**
 * @author Siva
 * 
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
	Product findByProductCode(String productCode);

	Product findByManufacturersCode(String manufacturersCode);

	Product findByProductId(Long productId);

	Product findByBrandId(Long brandId);
}