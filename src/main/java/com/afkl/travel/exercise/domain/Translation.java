package com.afkl.travel.exercise.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Translation {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
	@SequenceGenerator(name="seq_generator", sequenceName = "seq", allocationSize=10)
	@Column(name = "id", nullable=false)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
	private Location location;
	
	@Column(nullable=false)
	private String language;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String description;
	
	public int getId() {
		return id;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
}
