package co.in.hawker.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "brand", schema = "inventory_db", catalog="inventory_db", uniqueConstraints = @UniqueConstraint(columnNames = { "brandName",
		"brandCode" }))
public class Brand implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long brandId;

	@Column(unique = true, nullable = false)
	private String brandName;

	@Column(nullable = true)
	private String brandDescription;

	@Column(unique = true, nullable = false, length = 4)
	@Size(max = 4, min = 4)
	@NotNull
	private String brandCode;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = Product.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "brandId", referencedColumnName = "brandId")
	private Set<Product> products;

	public Brand() {

	}

	public Brand(String brandName, String brandDescription, String brandCode) {
		this.brandName = brandName;
		this.brandDescription = brandDescription;
		this.brandCode = brandCode;
	}

	public Brand(String brandName, String brandDescription, String brandCode, Set<Product> products) {
		this.brandName = brandName;
		this.brandDescription = brandDescription;
		this.brandCode = brandCode;
		this.products = products;
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

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
