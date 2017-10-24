package co.in.hawker.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "frequency_type", schema = "customer_db", catalog = "customer_db")
public class FrequencyType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long frequencyTypeId;

	@Column(unique = true, nullable = false)
	private String frequencyType;

	public FrequencyType() {

	}
	
	public FrequencyType(String frequencyType) {
		this.frequencyType = frequencyType;
	}

	public Long getFrequencyTypeId() {
		return frequencyTypeId;
	}

	public void setFrequencyTypeId(Long frequencyTypeId) {
		this.frequencyTypeId = frequencyTypeId;
	}

	public String getFrequencyType() {
		return frequencyType;
	}

	public void setFrequencyType(String frequencyType) {
		this.frequencyType = frequencyType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
