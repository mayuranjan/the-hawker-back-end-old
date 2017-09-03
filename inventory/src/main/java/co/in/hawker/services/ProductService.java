package co.in.hawker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.in.hawker.entities.Product;
import co.in.hawker.repositories.ProductRepository;
import co.in.hawker.services.validations.ProductValidations;

@Service
@Transactional
public class ProductService extends ProductValidations {
	@Autowired
	private ProductRepository productRepository;

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	public Product getProductByProductId(Long productId) {
		Product product = new Product();
		product.setProductId(productId);

//		handleException(checkIfProductIdDoesntExists(product));
		return productRepository.findByProductId(productId);
	}

}