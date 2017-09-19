package co.in.hawker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.in.hawker.entities.Brand;
import co.in.hawker.entities.Product;
import co.in.hawker.entities.Type;

/**
 * @author Siva
 * 
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
	Product findByProductCode(String productCode);

	Product findByManufacturersCode(String manufacturersCode);

	Product findByProductId(Long productId);

	List<Product> findByBrand(Brand brand);

	Product findByType(Type type);
}