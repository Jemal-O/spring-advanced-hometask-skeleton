package ua.epam.spring.hometask.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.service.AuditoriumService;
import ua.epam.spring.hometask.service.EventService;

@Controller
public class EventsController {

	private static final String SUCCESS_SAVING = "Event was successfully saved";
	private static final String SUCCESS_REMOVING = "Event was successfully removed";
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
	@Autowired
	private EventService eventService;

	@Autowired
	private AuditoriumService auditoriumService;

	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public ModelAndView goToEventsAdmin() {
		ModelAndView mav = new ModelAndView("events");
		mav.addObject("auditoriums", auditoriumService.getAll());
		mav.addObject("events", eventService.getAllEvents());
		return mav;
	}

	@RequestMapping(value = "/events/save/", method = RequestMethod.POST)
	public ModelAndView createAndSaveEvent(@RequestParam String name, @RequestParam String airDateTime,
			@RequestParam Integer basePrice, @RequestParam String auditoriumName) {
		eventService.save(fillingEvent(name, airDateTime, basePrice, auditoriumName));
		ModelAndView modelAndView = new ModelAndView("output");
		modelAndView.addObject("output", SUCCESS_SAVING);
		return modelAndView;
	}

	private Event fillingEvent(String name, String airDateTime, Integer basePrice, String auditoriumName) {
		Event event = new Event();
		event.setName(name);
		event.setAirDateTime(
				LocalDateTime.parse(airDateTime.replace("T", " "), DateTimeFormatter.ofPattern(DATE_FORMAT)));
		event.setBasePrice(basePrice);
		event.setRating(EventRating.MID);
		event.setAuditoriumId(auditoriumService.getByName(auditoriumName).getId());
		return event;
	}

	@RequestMapping(value = "/events/remove/{eventName}", method = RequestMethod.GET)
	public ModelAndView removeEvent(@PathVariable String eventName) {
		eventService.remove(eventService.getByName(eventName));
		ModelAndView modelAndView = new ModelAndView("output");
		return modelAndView.addObject("output", SUCCESS_REMOVING);

	}

	@RequestMapping(value = "/events/{eventId}", method = RequestMethod.GET)
	public ModelAndView getEventById(@PathVariable int eventId) {
		ModelAndView modelAndView = new ModelAndView("event");
		Event event = eventService.getById(eventId);
		return modelAndView.addObject("event", event.toString());
	}

	@RequestMapping(value = "/events/event/{name}", method = RequestMethod.GET)
	public ModelAndView getEventByName(@PathVariable String name) {
		ModelAndView modelAndView = new ModelAndView("event");
		Event event = eventService.getByName(name);
		return modelAndView.addObject("event", event.toString());
	}

	@RequestMapping(value = "/events/get-all/pdf", method = RequestMethod.GET, headers = "accept=application/pdf")
	public ModelAndView getAllEvents() {
		Map<String, String> data = new LinkedHashMap<String, String>();
		data.put("ID", "Event");
		for (Event event : eventService.getAllEvents()) {
			data.put(event.getId().toString(), event.getName());
		}
		return new ModelAndView("PdfViewResolver", "data", data);

	}
}
