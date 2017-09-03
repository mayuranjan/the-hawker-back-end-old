package co.in.hawker.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address", schema = "customer_db", catalog = "customer_db")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long addressId;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Premise.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "premiseId", nullable = false)
	private Premise premise;

	@Column(unique = false, nullable = false, length = 3)
	private short floorNumber;

	@Column(unique = false, nullable = false, length = 5)
	private String flatNumber;

	@Column(unique = false, nullable = true, length = 5)
	private String blockNumber;

	@Column(unique = false, nullable = true, length = 5)
	private String towerNumber;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Credential.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "credentialId", unique = true, nullable = false)
	private Credential credential;

	public Address() {

	}
	
	public Address(Premise premise, short floorNumber, String flatNumber, Credential credential) {
		this.premise = premise;
		this.floorNumber = floorNumber;
		this.flatNumber = flatNumber;
		this.credential = credential;
	}
	
	public Address(Premise premise, short floorNumber, String flatNumber, String blockNumber, Credential credential) {
		this(premise, floorNumber, flatNumber, credential);
		this.blockNumber = blockNumber;
	}
	
	public Address(Premise premise, short floorNumber, String flatNumber, String blockNumber, String towerNumber, Credential credential) {
		this(premise, floorNumber, flatNumber, blockNumber, credential);
		this.towerNumber = towerNumber;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Premise getPremise() {
		return premise;
	}

	public void setPremise(Premise premise) {
		this.premise = premise;
	}

	public short getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(short floorNumber) {
		this.floorNumber = floorNumber;
	}

	public String getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}

	public String getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(String blockNumber) {
		this.blockNumber = blockNumber;
	}

	public String getTowerNumber() {
		return towerNumber;
	}

	public void setTowerNumber(String towerNumber) {
		this.towerNumber = towerNumber;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
