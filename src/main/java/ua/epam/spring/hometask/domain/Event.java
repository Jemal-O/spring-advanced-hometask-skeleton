package ua.epam.spring.hometask.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.stereotype.Component;

/**
 * @author Yuriy_Tkach
 */
@Component
public class Event extends DomainObject {

	private Integer id;

	private String name;

	private LocalDateTime airDateTime;

	private double basePrice;

	private EventRating rating;

	private Integer auditoriumId;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public EventRating getRating() {
		return rating;
	}

	public void setRating(EventRating rating) {
		this.rating = rating;
	}

	public Integer getAuditoriumId() {
		return auditoriumId;
	}

	public void setAuditoriumId(Integer auditoriumId) {
		this.auditoriumId = auditoriumId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Event other = (Event) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public LocalDateTime getAirDateTime() {
		return airDateTime;
	}

	public void setAirDateTime(LocalDateTime airDateTime) {
		this.airDateTime = airDateTime;
	}

	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", airDateTime=" + airDateTime + ", basePrice=" + basePrice
				+ ", rating=" + rating.getRatingName() + ", auditoriumId= " + auditoriumId + "]";
	}

}
