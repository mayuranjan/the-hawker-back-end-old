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
import javax.validation.constraints.Size;

@Entity
@Table(name = "type", schema = "inventory_db", catalog="inventory_db", uniqueConstraints = @UniqueConstraint(columnNames = { "typeCode" }))
public class Type implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long typeId;

	@Column(unique = true, nullable = false)
	private String typeName;

	@Column(nullable = false)
	private String typeCategory;

	@Column(nullable = true)
	private String typeDescription;

	@Column(unique = true, nullable = false, length = 4)
	@Size(max = 4, min = 4)
	private String typeCode;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = Product.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "typeId", referencedColumnName = "typeId")
	private Set<Product> products;

	public Type() {

	}

	public Type(String typeName, String typeCategory, String typeDescription, String typeCode) {
		this.typeName = typeName;
		this.typeCategory = typeCategory;
		this.typeDescription = typeDescription;
		this.typeCode = typeCode;
	}

	public Type(String brandName, String typeName, String typeCategory, String typeDescription, String typeCode,
			String brandCode, Set<Product> products) {
		this.typeName = typeName;
		this.typeCategory = typeCategory;
		this.typeDescription = typeDescription;
		this.typeCode = typeCode;
		this.products = products;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeCategory() {
		return typeCategory;
	}

	public void setTypeCategory(String typeCategory) {
		this.typeCategory = typeCategory;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
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
