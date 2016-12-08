package ua.epam.spring.hometask.aspects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

import ua.epam.spring.hometask.dataManipulation.EventDao;
import ua.epam.spring.hometask.domain.Counter;
import ua.epam.spring.hometask.domain.CounterType;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.CounterService;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Component
@Aspect
public class CounterAspect {
	@Autowired
	private CounterService counterService;
	@Autowired
	private EventDao event;
	private String eventName;
	private Map<String, Integer> counterGetEventByName = Maps.newHashMap();

	private Map<String, Integer> counterGetTicketPrices = Maps.newHashMap();

	private Map<String, Integer> counterBookTicket = Maps.newHashMap();

	@Pointcut("execution(* ua.epam.spring.hometask.dao.EventServiceDao.getByName(..))")
	private void eventServiceGetByNameMethod() {
	}

	@AfterReturning("eventServiceGetByNameMethod() && args(name)")
	public void countEventGetByName(JoinPoint jp, String name) {
		eventName = name;
		if (!counterGetEventByName.containsKey(name)) {
			counterGetEventByName.put(name, 0);
		}
		counterGetEventByName.put(name, counterGetEventByName.get(name) + 1);
		System.out.println("Event " + name + " executed " + counterGetEventByName.get(name));
		createCounter(CounterType.GET_EVENT_BY_NAME, counterGetEventByName);
	}

	@Pointcut("execution(* ua.epam.spring.hometask.dao.*.getTicketsPrice(..))")
	private void bookingServiceGetTicketPricesMethod() {
	}

	@AfterReturning("bookingServiceGetTicketPricesMethod() && args(event,user)")
	public void countGetTicketPrice(JoinPoint jp, Event event, User user) {
		if (!counterGetTicketPrices.containsKey(eventName)) {
			counterGetTicketPrices.put(eventName, 0);
		}
		counterGetTicketPrices.put(eventName, counterGetTicketPrices.get(eventName) + 1);
		System.out.println("Ticket price for " + user.getFirstName() + " " + counterGetTicketPrices.get(eventName));
		createCounter(CounterType.GET_TICKET_PRICES,counterGetTicketPrices);
	}

	@Pointcut("execution(* ua.epam.spring.hometask.dao.*.bookTickets(..))")
	private void bookingServiceBookTicketMethod() {
	}

	@After("bookingServiceBookTicketMethod() && args(user,ticket)")
	public void countBookTicket(JoinPoint jp, User user, Ticket ticket) {
		String eventId = event.getById(ticket.getEventId()).getName();
		if (!counterBookTicket.containsKey(eventId)) {
			counterBookTicket.put(eventId, 0);
		}
		counterBookTicket.put(eventId, counterBookTicket.get(eventId) + 1);
		createCounter(CounterType.BOOK_TICKET, counterBookTicket);
	}
	
	  private void createCounter(CounterType counterType, Map<String, Integer> counterMap) {
	        for (String key : counterMap.keySet()) {
	            counterService.save(new Counter(counterType, key, counterMap.get(key)));
	        }
	    }

}
