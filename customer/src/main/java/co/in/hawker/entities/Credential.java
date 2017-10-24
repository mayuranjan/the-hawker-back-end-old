package co.in.hawker.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.ConstraintDefinitionException;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "credential", schema = "customer_db", catalog = "customer_db")
public class Credential implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long credentialId;

	@Column(unique = true, nullable = true)
	private String username;

	@Column(unique = true, nullable = true, length = 11)
	@Size(max = 11, min = 10)
	private int mobileNumber;

	@Column(unique = true, nullable = true, length = 60)
	@Email(message = "Please provide a valid e-mail")
	private String emailId;

	@Column(unique = true, nullable = false, length = 60)
	@Size(max = 60, min = 10)
	private String password;

	@Column(unique = false, nullable = false)
	private String accountType = "Master";

	@Column(unique = false, nullable = true)
	private Long masterCredentialId;
	
	@ManyToOne
    private Credential masterAccount;
	
	@OneToMany(mappedBy = "masterAccount")
    private Set<Credential> childAccounts = new HashSet<Credential>();

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Profile.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "credentialId")
	private Profile profile;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = Address.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "credentialId")
	private Set<Address> addresses;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = Frequency.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "credentialId")
	private Set<Frequency> frequencies;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = Order.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "credentialId")
	private Set<Order> orders;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Preference.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "credentialId")
	private Preference preference;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Wallet.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "credentialId")
	private Wallet wallet;

	public Credential() {

	}

	public Credential(String username, int mobileNumber, String emailId, String password) {
		this.username = username;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.password = password;
	}

	public Credential(String username, int mobileNumber, String emailId, String password, String accountType,
			Long masterCredentialId) {
		this(username, mobileNumber, emailId, password);
		this.accountType = accountType;
		this.masterCredentialId = masterCredentialId;
	}

	@PrePersist
	public void checkValidation() {
		if (isValid()) {
			// send to persist.
		} else {
			throw new ConstraintDefinitionException("One of Username, Mobile Number and Email ID should have value");
		}
		if (checkMasterCredentialId()) {
			// send to persist.
		} else {
			throw new ConstraintDefinitionException(
					"Master Credential Id should have value, if the account type is Child.");
		}

		encryptPassword();
	}

	private boolean isValid() {
		return username != null || mobileNumber != 0 || emailId != null;
	}

	private boolean checkMasterCredentialId() {
		if (this.accountType.equalsIgnoreCase("master")) {
			return true;
		} else {
			if (this.masterCredentialId == null) {
				return false;
			} else {
				return true;
			}
		}
	}

	private void encryptPassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.setPassword(passwordEncoder.encode(this.getPassword()));
	}

	public Long getCredentialId() {
		return credentialId;
	}

	public void setCredentialId(Long credentialId) {
		this.credentialId = credentialId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Long getMasterCredentialId() {
		return masterCredentialId;
	}

	public void setMasterCredentialId(Long masterCredentialId) {
		this.masterCredentialId = masterCredentialId;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<Frequency> getFrequencies() {
		return frequencies;
	}

	public void setFrequencies(Set<Frequency> frequencies) {
		this.frequencies = frequencies;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Preference getPreference() {
		return preference;
	}

	public void setPreference(Preference preference) {
		this.preference = preference;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	public Credential getMasterAccount() {
		return masterAccount;
	}

	public void setMasterAccount(Credential masterAccount) {
		this.masterAccount = masterAccount;
	}

	public Set<Credential> getChildAccounts() {
		return childAccounts;
	}

	public void setChildAccounts(Set<Credential> childAccounts) {
		this.childAccounts = childAccounts;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
