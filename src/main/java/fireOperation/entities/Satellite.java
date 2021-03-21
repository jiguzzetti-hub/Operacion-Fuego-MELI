package fireOperation.entities;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@SequenceGenerator(name = "SEQ_SATELLITE", initialValue = 1, allocationSize = 1, sequenceName = "SEQ_SATELLITE")
@Table(name = "SATELLITE")

public class Satellite {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SATELLITE")
	private Long id;
	@Column(nullable = false)
	private float distance;
	@Column(nullable = false)
	private ArrayList<String> message;
	@Column(nullable = false)
	private String name;
	@Column(name = "INSERTION_DATE", updatable = false, nullable = false)
	@CreationTimestamp
	private Date insertionDate;

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public ArrayList<String> getMessage() {
		return message;
	}

	public void setMessage(ArrayList<String> message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Satellite(float distance, ArrayList<String> message, String name) {
		super();
		this.distance = distance;
		this.message = message;
		this.name = name;
	}

	public Satellite() {

	}

}
