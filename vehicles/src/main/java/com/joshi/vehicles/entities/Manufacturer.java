package com.joshi.vehicles.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="manufacturer")
public class Manufacturer {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "MNFR_ID")
	private Long id;
	
	@Column(name = "MNFR_NAME")
	private String name;
	
	@Column(name = "MNFR_ADDRESS_1")
	private String address1;
	
	@Column(name = "MNFR_ADDRESS_2")
	private String address2;
	
	@Column(name = "MNFR_CITY_CODE")
	private String city;
	
	@Column(name = "MNFR_STATE_CODE")
	private String state;
	
	@Column(name = "MNFR_COUNTRY_CODE")
	private String country;
	
	public Manufacturer() {
		
	}
	
	public Manufacturer(com.joshi.vehicles.model.Manufacturer mnfr) {
		this.name = mnfr.name();
		this.address1 = mnfr.address1();
		this.address2 = mnfr.address2();
		this.city = mnfr.city();
		this.state = mnfr.state();
		this.country = mnfr.country();
	}
	
	public com.joshi.vehicles.model.Manufacturer toModel() {
		com.joshi.vehicles.model.Manufacturer mnfr = new com.joshi.vehicles.model.Manufacturer(
				this.id, this.name, this.address1, this.address2, this.city, this.state, this.country);
		return mnfr;
	}
}
