package com.joshi.manufacturer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table (name = "manufacturer")
public class Manufacturer {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "MNFR_ID")
	private Long id;

	@Column(name = "MNFR_NAME")
	private String manufacturerName;
	
	@Column(name = "MNFR_ADDRESS_1")
	private String address1;
	
	@Column(name = "MNFR_ADDRESS_2")
	private String address2;
	
	@Column(name = "MNFR_CITY_CODE")
	private String city;

	@Column(name = "MNFR_STATE")
	private String state;
	
	@Column(name = "MNFR_COUNTRY")
	private String country;

	public Manufacturer() {
		
	}
	
	public Manufacturer(com.joshi.manufacturer.model.Manufacturer mnfr) {
		this.id = mnfr.id();
		this.manufacturerName = mnfr.manufacturerName();
		this.address1 = mnfr.address1();
		this.address2 = mnfr.address2();
		this.city = mnfr.city();
		this.state = mnfr.state();
		this.country = mnfr.country();
	}
	
	public com.joshi.manufacturer.model.Manufacturer toManufacturer() {

		com.joshi.manufacturer.model.Manufacturer mnfr = new com.joshi.manufacturer.model.Manufacturer(id, manufacturerName, address1, address2, city, state, country);
		return mnfr;
	}
}
