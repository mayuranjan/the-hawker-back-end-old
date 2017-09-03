package co.in.hawker.entities;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = "wallet", schema = "customer_db", catalog="customer_db")
public class Wallet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long walletId;

	@Column(unique = false, nullable = false, length = 5)
	private double balance = 0.00;

	@Column(unique = false, nullable = false)
	private Timestamp timestamp;

	@Column(unique = false, nullable = false)
	private double transactionAmount;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Credential.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "credentialId")
	private Credential credential;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Order.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "orderId")
	private Order order;

	public Wallet() {

	}

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
