package co.in.hawker.web.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.in.hawker.entities.Brand;
import co.in.hawker.services.BrandService;
import co.in.hawker.util.FailureStatus;
import co.in.hawker.util.Status;
import co.in.hawker.util.SuccessStatus;

/**
 * @author Siva
 * 
 */
@Controller
@RequestMapping(value = "/brands/")
public class BrandController extends MasterController {
	@Autowired
	private BrandService brandService;

	@InitBinder
	private void dataBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/createBrand", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> addBrand(@RequestBody Brand brand) {

		try {
			Brand addedBrand = brandService.addBrand(brand);

			Status body = new SuccessStatus("Brand added Successfully !", addedBrand);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to add Brand", "Exception", e.getMessage(), brand);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getAllBrands() {
		try {
			List<Brand> brands = brandService.getAllBrands();

			Status body = new SuccessStatus("Brand fetched Successfully !", brands);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch brand", "Exception", e.getMessage(), null);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByBrandCode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getBrandByBrandCode(@RequestParam("brandCode") String brandCode) {
		try {
			Brand brand = brandService.getBrandByBrandCode(brandCode);

			Status body = new SuccessStatus("Brand fetched by Brand Code Successfully !", brand);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Brand by Brand Code", "Exception", e.getMessage(),
					brandCode);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByBrandId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getBrandByBrandId(@PathVariable("id") Long brandId) {
		try {
			Brand brand = brandService.getBrandByBrandId(brandId);

			Status body = new SuccessStatus("Brand fetched by Brand Id Successfully !", brand);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Brand by Brand Id.", "Exception", e.getMessage(), brandId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getByBrandName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> getBrandByBrandName(@RequestParam("brandName") String brandName) {
		try {
			Brand brand = brandService.getBrandByBrandName(brandName);

			Status body = new SuccessStatus("Brand fetched by Brand Name Successfully !", brand);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to fetch Brand by Brand Name.", "Exception", e.getMessage(),
					brandName);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateByBrandId/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateBrandByBrandId(@PathVariable("id") Long brandId, @RequestBody Brand brand) {
		brand.setBrandId(brandId);

		try {
			Brand updatedBrand = brandService.updateBrandByBrandId(brand);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedBrand);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(), brandId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateByBrandCode/{code}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateBrandByBrandCode(@PathVariable("code") String brandCode,
			@RequestBody Brand brand) {
		brand.setBrandCode(brandCode);

		try {
			Brand updatedBrand = brandService.updateBrandByBrandCode(brand);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedBrand);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), brandCode);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), brandCode);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					brandCode);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(), brandCode);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/updateByBrandName/{name}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> updateBrandByBrandName(@PathVariable("name") String brandName,
			@RequestBody Brand brand) {
		brand.setBrandName(brandName);

		try {
			Brand updatedBrand = brandService.updateBrandByBrandName(brand);

			Status body = new SuccessStatus("Brand updated Successfully !", updatedBrand);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to update Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), brandName);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to update Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), brandName);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to update Brand.", "RuntimeException", rException.getMessage(),
					brandName);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to update Brand.", "Exception", e.getMessage(), brandName);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByBrandId/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteBrandByBrandId(@PathVariable("id") Long brandId) {
		Brand brand = new Brand();
		brand.setBrandId(brandId);

		try {
			brandService.deleteBrandByBrandId(brand);

			Status body = new SuccessStatus("Brand deleted Successfully !", brandId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Brand.", "Exception", e.getMessage(), brandId);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByBrandCode/{code}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteBrandByBrandCode(@PathVariable("code") String brandCode) {
		Brand brand = new Brand();
		brand.setBrandCode(brandCode);

		try {
			brandService.deleteBrandByBrandCode(brand);

			Status body = new SuccessStatus("Brand deleted Successfully !", brandCode);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), brandCode);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), brandCode);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete Brand.", "Exception", rException.getMessage(), brandCode);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Brand.", "Exception", e.getMessage(), brandCode);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/deleteByBrandName/{name}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Status> deleteBrandByBrandName(@PathVariable("name") String brandName) {
		Brand brand = new Brand();
		brand.setBrandName(brandName);

		try {
			brandService.deleteBrandByBrandName(brand);

			Status body = new SuccessStatus("Brand deleted Successfully !", brandName);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.OK;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (ConstraintViolationException cVException) {

			Status body = new FailureStatus("Failed to delete Brand", "ConstraintViolationException",
					cVException.getCause().toString(), cVException.getMessage(), brandName);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (DataIntegrityViolationException dIVException) {

			Status body = new FailureStatus("Failed to delete Brand", "DataIntegrityViolationException",
					dIVException.getRootCause().toString(), dIVException.getMessage(), brandName);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (RuntimeException rException) {

			Status body = new FailureStatus("Failed to delete Brand.", "Exception", rException.getMessage(), brandName);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		} catch (Exception e) {

			Status body = new FailureStatus("Failed to delete Brand.", "Exception", e.getMessage(), brandName);
			MultiValueMap<String, String> headers = new HttpHeaders();
			HttpStatus status = HttpStatus.BAD_REQUEST;

			return new ResponseEntity<Status>(body, headers, status);
		}
	}
}
