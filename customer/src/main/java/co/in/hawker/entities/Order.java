package co.in.hawker.entities;

import java.io.Serializable;
import java.sql.Timestamp;
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
import javax.persistence.Table;

@Entity
@Table(name = "order", schema = "customer_db", catalog="customer_db")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long orderId;

	@Column(unique = false, nullable = false)
	private Timestamp timestamp;

	@Column(unique = false, nullable = false)
	private String status;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = OrderItem.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "orderItemId")
	private Set<OrderItem> orderItems;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Credential.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "credentialId")
	private Credential credential;

	public Order() {

	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
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
