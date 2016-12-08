package ua.epam.spring.hometask.dao;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;

@Component
public class DiscountServiceDao implements DiscountService {

	@Override
	public byte getDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets) {
		byte allDiscount = 0;
		if (numberOfTickets > 10) {
			allDiscount += 5;
		}
		if (event.getRating() == EventRating.MID) {
			allDiscount += 1;
		}
		return allDiscount;
	}

}
