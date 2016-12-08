package ua.epam.spring.hometask.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.AccountsService;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.EventService;
import ua.epam.spring.hometask.service.UserService;

@Controller
public class BookingController {
	private static final String SUCCESS_SAVING = "Ticket was succesfully booked";
	@Autowired
	private BookingService bookingService;

	@Autowired
	private EventService eventService;

	@Autowired
	private UserService userService;

	@Autowired
	private AccountsService accountsService;

	@RequestMapping(value = "/tickets", method = RequestMethod.GET)
	public ModelAndView goToTicketsAdmin(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = userService.getUserByEmail(request.getUserPrincipal().getName());
		double balance = accountsService.getByUser(user).getMoney();
		mav.addObject("eventsName", eventService.getAllEvents());
		mav.addObject("usersEmail", userService.getAllUsers());
		mav.addObject("balance", balance);
		mav.setViewName("tickets");
		return mav;
	}

	@RequestMapping(value = "/tickets/purchased/{eventName}", method = RequestMethod.GET)
	public ModelAndView purchasedTickets(@PathVariable String eventName) {
		List<Ticket> purchasedTickets = bookingService.getPurchasedTicketsForEvent(eventService.getByName(eventName));
		ModelAndView modelAndView = new ModelAndView("ticketsList");
		modelAndView.addObject("ticketsList", purchasedTickets);
		return modelAndView;
	}

	@RequestMapping(value = "/tickets/book", method = RequestMethod.POST)
	public ModelAndView book(@RequestParam String email, @RequestParam String eventName, @RequestParam Integer seat) {
		ModelAndView model = new ModelAndView("output");
		try {
			bookingService.bookTickets(userService.getUserByEmail(email), fillingTicket(email, eventName, seat));
			model.addObject("output", SUCCESS_SAVING);
		} catch (Exception e) {
			model.addObject("output", e.getMessage());
		}
		;
		return model;
	}

	private Ticket fillingTicket(String email, String eventName, Integer seat) {
		Ticket ticket = new Ticket();
		System.out.println("EVENT " + eventService.getByName(eventName).getId());
		ticket.setEventId(eventService.getByName(eventName).getId());
		ticket.setSeat(seat);
		ticket.setUserId(userService.getUserByEmail(email).getId());
		ticket.setIsPurchased(false);
		ticket.setTicketPrice((int) (eventService.getByName(eventName).getBasePrice() * seat));
		return ticket;
	}

	@RequestMapping(value = "/tickets/get-all/pdf", method = RequestMethod.GET, headers = "accept=application/pdf")
	public ModelAndView exportToPDF() {
		Map<String, String> data = new LinkedHashMap<String, String>();
		data.put("ID", "Ticket");
		for (Ticket ticket : bookingService.getAllTickets()) {
			data.put(ticket.getId().toString(), ticket.toString());
		}
		return new ModelAndView("PdfViewResolver", "data", data);
	}
}
