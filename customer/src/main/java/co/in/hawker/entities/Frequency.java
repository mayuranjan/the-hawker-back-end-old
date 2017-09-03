package co.in.hawker.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;

import org.springframework.format.annotation.DateTimeFormat;

import co.in.hawker.util.PresentOrFuture;

@Entity
@Table(name = "frequency", schema = "customer_db", catalog = "customer_db")
public class Frequency implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long frequencyId;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false)
	private Product product;

	@Column(unique = false, nullable = false, length = 3)
	private int quantity;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = FrequencyType.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "frequencyTypeId")
	private FrequencyType frequencyType;

	@PresentOrFuture
	@Column(unique = false, nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MMM-yyyy")
	private Date startDate;

	@Future
	@Column(unique = false, nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MMM-yyyy")
	private Date endDate;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Credential.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "credentialId")
	private Credential credential;

	@Column(unique = false, nullable = false)
	private boolean active;

	public Frequency() {

	}

	private Frequency(Product product, int quantity, Date startDate, Date endDate) {
		this.product = product;
		this.quantity = quantity;
		this.startDate = startDate;
		this.endDate = endDate;
		this.active = true;
	}

	private Frequency(Product product, int quantity, Date startDate, Date endDate, boolean active) {
		this(product, quantity, startDate, endDate);
		this.active = active;
	}

	public Frequency(Product product, int quantity, Date startDate, Date endDate, FrequencyType frequencyType) {
		this(product, quantity, startDate, endDate);
		this.frequencyType = frequencyType;
	}

	public Frequency(Product product, int quantity, Date startDate, Date endDate, boolean active,
			FrequencyType frequencyType) {
		this(product, quantity, startDate, endDate, active);
		this.frequencyType = frequencyType;
	}

	public Long getFrequencyId() {
		return frequencyId;
	}

	public void setFrequencyId(Long frequencyId) {
		this.frequencyId = frequencyId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public FrequencyType getFrequencyType() {
		return frequencyType;
	}

	public void setFrequencyType(FrequencyType frequencyType) {
		this.frequencyType = frequencyType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
