package com.afkl.travel.exercise.domain;

import java.util.Collection;

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
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
	@SequenceGenerator(name="seq_generator", sequenceName = "hibernate_sequence", allocationSize=1)
	@Column(name = "id", nullable=false)
	private int id;
	
	@Column(nullable=false)
	private String code;
	
	@Column(nullable=false)
	private String type;
	
	@Column(nullable=false)
	private Double longitude;
	
	@Column(nullable=false)
	private Double latitude;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Location parent;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "location")
	private Collection<Translation> translations;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "parent")
	private Collection<Location> childLocations;
	
	public int getId() {
		return id;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getType() {
		return this.type;
	}
	
	public Double getLongitude() {
		return this.longitude;
	}
	
	public Double getLatitude() {
		return this.latitude;
	}
	
	public Location getParent() {
		return this.parent;
	}

	public Collection<Translation> getTranslations() {
		return this.translations;
	}
	
	public Collection<Location> getChildLocations() {
		return this.childLocations;
	}
}
