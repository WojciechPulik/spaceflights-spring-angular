package pl.wpulik.spaceflights.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="tourist")
public class Tourist implements Serializable{
	private static final long serialVersionUID =1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tourist")
	private Long id;
	@Column(name="firstname", nullable=false)
	private String firstName;
	@Column(name="lastname", nullable=false)
	private String lastName;
	@Column(nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private Gender gender;
	@Column(nullable=false)
	private String country;
	@Column(length=512)
	private String note;
	@Column(name="dateofbirth", nullable=false)
	private String dateOfBirth;
	@ManyToMany(mappedBy="tourists", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Flight> flights = new HashSet<>();
	
	public Tourist() {}
		
	public Tourist(String firstName, String lastName, Gender gender, String country, String note, String dateOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.country = country;
		this.note = note;
		this.dateOfBirth = dateOfBirth;
	}
	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Set<Flight> getFlights() {
		return flights;
	}
	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}

	@Override
	public String toString() {
		return "Tourist [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", country=" + country + ", note=" + note + ", dateOfBirth=" + dateOfBirth
				+ "]";
	}

	

	
	
	

}
