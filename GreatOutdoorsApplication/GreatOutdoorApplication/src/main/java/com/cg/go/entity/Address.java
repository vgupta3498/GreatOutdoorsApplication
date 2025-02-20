package com.cg.go.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import com.sun.istack.NotNull;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADD_SEQ")
    @SequenceGenerator(sequenceName = "address_seq", allocationSize = 1, name = "ADD_SEQ")//Starting With 1
	private int addressId;
	@NotEmpty(message="Please enter building number")
	private int buildingNo;
	@NotEmpty(message="Please enter street name")
	private String streetName;
	@NotEmpty(message="Please enter area")
	private String area;
	@NotEmpty(message="Please enter city")
	private String city;
	@NotEmpty(message="Please enter state")
	private String state;
	@NotNull
	private int zip;

	public Address(int addressId, int buildingNo, String streetName, String area, String city, String state,int zip) {			
		super();
		this.addressId = addressId;
		this.buildingNo = buildingNo;
		this.streetName = streetName;
		this.area = area;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	public Address() {
		super();
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public int getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(int buildingNo) {
		this.buildingNo = buildingNo;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", buildingNo=" + buildingNo + ", streetName=" + streetName
				+ ", area=" + area + ", city=" + city + ", state=" + state + ", zip=" + zip + "]";
	}
	
}
