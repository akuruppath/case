package com.afkl.travel.exercise.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
	@SequenceGenerator(name="seq_generator", sequenceName = "seq", allocationSize=10)
	@Column(name = "id", nullable=false)
	private int id;
	
	@Column(nullable=false)
	private String code;
	
	@Column(nullable=false)
	private String type;
	
	@Column(nullable=false)
	private double longitude;
	
	@Column(nullable=false)
	private double latitude;
	
	@OneToOne
	private Location parent;
	
	@OneToOne(mappedBy = "location")
	private Translation translation;
	
	@OneToOne(mappedBy = "parent")
	@JoinColumn(name = "parent_id")
	private Location locationTranslation;
	
	public int getId() {
		return id;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getType() {
		return type;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
//	public Location getParentId() {
//		return parentId;
//	}

	public Translation getTranslation() {
		return translation;
	}
}
