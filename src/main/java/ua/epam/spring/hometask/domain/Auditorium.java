package ua.epam.spring.hometask.domain;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.springframework.stereotype.Component;

/**
 * @author Yuriy_Tkach
 */
@Component
public class Auditorium {

	private Integer id;

	private String name;

	private Integer numberOfSeats;

	private Boolean isAssign;

	public Auditorium() {
	}

	public Auditorium(String name, Integer numberOfSeats, Boolean isAssign) {
		this.name = name;
		this.numberOfSeats = numberOfSeats;
		this.isAssign = isAssign;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		Auditorium other = (Auditorium) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public Boolean getIsAssign() {
		return isAssign;
	}

	public void setAssign(Boolean isAssign) {
		this.isAssign = isAssign;
	}

	public Set<Long> getAllSeats() {
		return LongStream.range(1, numberOfSeats + 1).boxed().collect(Collectors.toSet());
	}

	public String toString() {
		return "Auditorium [id=" + id + ", name=" + name + ", numberOfSeats=" + numberOfSeats + ", isAssign=" + isAssign
				+ "]";
	}
}
