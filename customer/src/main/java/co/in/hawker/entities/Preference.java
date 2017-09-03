package co.in.hawker.entities;

import java.io.Serializable;
import java.sql.Time;

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
@Table(name = "preference", schema = "customer_db", catalog="customer_db")
public class Preference implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long preferenceId;

	@Column(unique = false, nullable = true, length = 5)
	private String language;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Product.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "milkProductId")
	private Product milk;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Product.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "waterProductId")
	private Product water;

	@Column(unique = false, nullable = true)
	private Time preferredTimeSlot;

	@Column(unique = false, nullable = true, length = 20)
	private String paymentMode;

	@Column(unique = false, nullable = true, length = 20)
	private String channelOfCommunication;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Credential.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "credentialId")
	private Credential credential;

	@Column(unique = false, nullable = false)
	private String status;

	public Preference() {

	}

	public Long getPreferenceId() {
		return preferenceId;
	}

	public void setPreferenceId(Long preferenceId) {
		this.preferenceId = preferenceId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Product getMilk() {
		return milk;
	}

	public void setMilk(Product milk) {
		this.milk = milk;
	}

	public Product getWater() {
		return water;
	}

	public void setWater(Product water) {
		this.water = water;
	}

	public Time getPreferredTimeSlot() {
		return preferredTimeSlot;
	}

	public void setPreferredTimeSlot(Time preferredTimeSlot) {
		this.preferredTimeSlot = preferredTimeSlot;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getChannelOfCommunication() {
		return channelOfCommunication;
	}

	public void setChannelOfCommunication(String channelOfCommunication) {
		this.channelOfCommunication = channelOfCommunication;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
