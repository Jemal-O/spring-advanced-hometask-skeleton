package ua.epam.spring.hometask.domain;

import java.util.Objects;

import org.springframework.stereotype.Component;

/**
 * @author Yuriy_Tkach
 */
@Component
public class Ticket extends DomainObject implements Comparable<Ticket> {
	private Integer id;

	private Integer userId;

	private Integer eventId;

	private Integer seat;

	private Integer ticketPrice;

	private Boolean isPurchased;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

	public void setTicketPrice(Integer ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Boolean getIsPurchased() {
		return isPurchased;
	}

	public void setIsPurchased(Boolean isPurchased) {
		this.isPurchased = isPurchased;
	}

	public Ticket() {

	}

	public Integer getTicketPrice() {
		return ticketPrice;
	}

	public long getSeat() {
		return seat;
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventId, seat, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isPurchased == null) {
			if (other.isPurchased != null)
				return false;
		} else if (!isPurchased.equals(other.isPurchased))
			return false;
		if (seat == null) {
			if (other.seat != null)
				return false;
		} else if (!seat.equals(other.seat))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public int compareTo(Ticket other) {
		if (other == null) {
			return 1;
		}
		int result = getId().compareTo(other.getId());
		return result;
	}
	
	public String toString() {
        return "Ticket [id=" + id + ", eventId=" + eventId + ", userId=" + userId + ", seat=" + seat + ", isPurchased="
                + isPurchased + "]";
    }

}
