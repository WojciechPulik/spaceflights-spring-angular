package pl.wpulik.spaceflights.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="flight")
public class Flight implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_flight")
	private Long id;
	@Column(name="departure", nullable=false)
	private String departureDate;
	@Column(name="arrival", nullable=false)
	private String arrivalDate;
	@Column(name="seatsnumber", nullable=false)
	private Integer seatsNumber;
	@Column(nullable=false)
	private Double price;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="flight_tourists",
			joinColumns = @JoinColumn(name="flights_id_flight"),
			inverseJoinColumns = @JoinColumn(name = "tourists_id_tourist")
			)
	@JsonIgnore
	private Set<Tourist> tourists = new HashSet<>();
	
	
	public Flight() {}

	public Flight(String departureDate, String arrivalDate, Integer seatsNumber, Double price) {
		super();
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.seatsNumber = seatsNumber;
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	
	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Integer getSeatsNumber() {
		return seatsNumber;
	}

	public void setSeatsNumber(Integer seatsNumber) {
		this.seatsNumber = seatsNumber;
	}

	public Set<Tourist> getTourists() {
		return tourists;
	}

	public void setTourists(Set<Tourist> tourists) {
		this.tourists = tourists;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", departureDate=" + departureDate + ", arrivalDate=" + arrivalDate
				+ ", seatsNumber=" + seatsNumber + ", taken seats=" + tourists.size() + ", price=" + price + "]";
	}

	
	
	
}
