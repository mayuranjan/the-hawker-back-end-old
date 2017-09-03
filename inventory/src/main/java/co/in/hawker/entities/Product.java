package co.in.hawker.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "product", schema = "inventory_db", catalog="inventory_db", uniqueConstraints = @UniqueConstraint(columnNames = { "productCode" }))
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column()
	private Long productId;

	@Column()
	private String productName;

	@Column()
	private String productCode;

	@Column()
	private BigDecimal costPrice;

	@Column()
	private BigDecimal retailSellingPrice;

	@Column()
	private BigDecimal retailDeliveryCharges;

	@Column()
	private BigDecimal wholsesaleSellingPrice;

	@Column()
	private BigDecimal wholesaleDeliveryCharges;

	@Column()
	private String productDescription;

	@Column()
	private int expiryInDays;

	@Column()
	private String volume;

	@Column()
	private String manufacturersCode;

	@Column()
	private Long brandId;

	@Column()
	private Long typeId;

	public Product() {

	}

	public Product(String productName, String productCode, BigDecimal costPrice, BigDecimal retailSellingPrice,
			BigDecimal retailDeliveryCharges, BigDecimal wholsesaleSellingPrice, BigDecimal wholesaleDeliveryCharges,
			String productDescription, int expiryInDays, String volume, String manufacturersCode, Long brandId,
			Long typeId) {
		this.productName = productName;
		this.productCode = productCode;
		this.costPrice = costPrice;
		this.retailSellingPrice = retailSellingPrice;
		this.retailDeliveryCharges = retailDeliveryCharges;
		this.wholsesaleSellingPrice = wholsesaleSellingPrice;
		this.wholesaleDeliveryCharges = wholesaleDeliveryCharges;
		this.productDescription = productDescription;
		this.expiryInDays = expiryInDays;
		this.volume = volume;
		this.manufacturersCode = manufacturersCode;
		this.brandId = brandId;
		this.typeId = typeId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getRetailSellingPrice() {
		return retailSellingPrice;
	}

	public void setRetailSellingPrice(BigDecimal retailSellingPrice) {
		this.retailSellingPrice = retailSellingPrice;
	}

	public BigDecimal getRetailDeliveryCharges() {
		return retailDeliveryCharges;
	}

	public void setRetailDeliveryCharges(BigDecimal retailDeliveryCharges) {
		this.retailDeliveryCharges = retailDeliveryCharges;
	}

	public BigDecimal getWholsesaleSellingPrice() {
		return wholsesaleSellingPrice;
	}

	public void setWholsesaleSellingPrice(BigDecimal wholsesaleSellingPrice) {
		this.wholsesaleSellingPrice = wholsesaleSellingPrice;
	}

	public BigDecimal getWholesaleDeliveryCharges() {
		return wholesaleDeliveryCharges;
	}

	public void setWholesaleDeliveryCharges(BigDecimal wholesaleDeliveryCharges) {
		this.wholesaleDeliveryCharges = wholesaleDeliveryCharges;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getExpiryInDays() {
		return expiryInDays;
	}

	public void setExpiryInDays(int expiryInDays) {
		this.expiryInDays = expiryInDays;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getManufacturersCode() {
		return manufacturersCode;
	}

	public void setManufacturersCode(String manufacturersCode) {
		this.manufacturersCode = manufacturersCode;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
