package co.in.hawker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.in.hawker.entities.Brand;
import co.in.hawker.repositories.BrandRepository;
import co.in.hawker.services.validations.BrandValidations;

@Service
@Transactional
public class BrandService extends BrandValidations {
	@Autowired
	private BrandRepository brandRepository;

	public Brand addBrand(Brand brand) {

		// null check
		handleException(checkIfBrandNameIsNotNull(brand));
		handleException(checkIfBrandCodeIsNotNull(brand));

		// duplicate check
		handleException(checkIfBrandCodeDoesntExists(brand));
		handleException(checkIfBrandNameDoesntExists(brand));
		handleException(checkIfBrandIdDoesntExists(brand));

		return brandRepository.save(brand);
	}

	public List<Brand> getAllBrands() {
		return brandRepository.findAll();
	}

	public Brand getBrandByBrandCode(String brandCode) {
		Brand brand = new Brand();
		brand.setBrandCode(brandCode);

		handleException(checkIfBrandCodeDoesntExists(brand));
		return brandRepository.findByBrandCode(brandCode);
	}

	public Brand getBrandByBrandName(String brandName) {
		Brand brand = new Brand();
		brand.setBrandName(brandName);

		handleException(checkIfBrandNameDoesntExists(brand));
		return brandRepository.findByBrandName(brandName);
	}

	public Brand getBrandByBrandId(Long brandId) {
		Brand brand = new Brand();
		brand.setBrandId(brandId);

		handleException(checkIfBrandIdDoesntExists(brand));
		return brandRepository.findByBrandId(brandId);
	}

	public Brand updateBrandByBrandId(Brand brand) {

		handleException(checkIfBrandIdExists(brand));
		return brandRepository.save(brand);
	}

	public Brand updateBrandByBrandCode(Brand brand) {
		handleException(checkIfBrandCodeExists(brand));
		brand.setBrandId(this.getBrandByBrandCode(brand.getBrandCode()).getBrandId());

		return brandRepository.save(brand);
	}

	public Brand updateBrandByBrandName(Brand brand) {
		handleException(checkIfBrandNameExists(brand));
		brand.setBrandId(this.getBrandByBrandName(brand.getBrandName()).getBrandId());

		return brandRepository.save(brand);
	}

	public void deleteBrandByBrandId(Brand brand) {
		handleException(checkIfBrandIdExists(brand));
		brandRepository.delete(brand);
		return;
	}

	public void deleteBrandByBrandCode(Brand brand) {
		handleException(checkIfBrandCodeExists(brand));
		brand.setBrandId(this.getBrandByBrandCode(brand.getBrandCode()).getBrandId());

		brandRepository.delete(brand);
		return;
	}

	public void deleteBrandByBrandName(Brand brand) {
		handleException(checkIfBrandNameExists(brand));
		brand.setBrandId(this.getBrandByBrandName(brand.getBrandName()).getBrandId());

		brandRepository.delete(brand);
		return;
	}
}
