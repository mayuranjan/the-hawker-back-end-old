package co.in.hawker.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "brand")
public class Brand implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "brand_id", unique = true, nullable = false)
	private Long brandId;

	@Column(name = "brand_name", unique = true, nullable = false)
	private String brandName;

	@Column(name = "brand_description", nullable = true)
	private String brandDescription;

	@Column(name = "brand_code", unique = true, nullable = false, length = 4)
	@Size(max = 4, min = 4)
	@NotNull
	private String brandCode;

	public Brand() {

	}

	public Brand(String brandName, String brandDescription, String brandCode) {
		this.brandName = brandName;
		this.brandDescription = brandDescription;
		this.brandCode = brandCode;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandDescription() {
		return brandDescription;
	}

	public void setBrandDescription(String brandDescription) {
		this.brandDescription = brandDescription;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
