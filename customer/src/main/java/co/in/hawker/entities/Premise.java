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

@Entity
@Table(name = "premise", schema = "customer_db", catalog = "customer_db")
public class Premise implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long premiseId;

	@Column(unique = false, nullable = false)
	private String type;

	@Column(unique = false, nullable = false)
	private String name;

	@Column(unique = false, nullable = false)
	private String builder;

	@Column(unique = false, nullable = false)
	private String addressLine1;

	@Column(unique = false, nullable = false)
	private String addressLine2;

	@Column(unique = false, nullable = false)
	private String area;

	@Column(unique = false, nullable = false)
	private String landmark;

	@Column(unique = false, nullable = false, length = 6)
	private int pincode;

	@Column(unique = false, nullable = false)
	private String state;

	@Column(unique = false, nullable = false)
	private String country;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = Address.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "premiseId")
	private Set<Address> addresses;

	public Premise() {

	}

	public Long getPremiseId() {
		return premiseId;
	}

	public void setPremiseId(Long premiseId) {
		this.premiseId = premiseId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
