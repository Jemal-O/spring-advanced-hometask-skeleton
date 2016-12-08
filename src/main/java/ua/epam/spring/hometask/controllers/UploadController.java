package ua.epam.spring.hometask.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.AuditoriumService;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.EventService;
import ua.epam.spring.hometask.service.UserService;
import ua.epam.spring.hometask.utils.UploadParser;

@Controller
public class UploadController {
	public static final String IMPORT_SUCCESS = "File successfully imported";
	@Autowired
	private UserService userService;
	@Autowired
	private EventService eventService;
	@Autowired
	private AuditoriumService auditoriumService;
	@Autowired
	private BookingService bookingService;

	@RequestMapping(value = "/import", method = RequestMethod.GET)
	public ModelAndView goToImportPage() {
		return new ModelAndView("import");
	}

	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public ModelAndView importJson(@RequestParam MultipartFile userFile, @RequestParam MultipartFile eventFile,
			@RequestParam MultipartFile auditoriumFile, @RequestParam MultipartFile ticketFile) {
		ModelAndView modelAndView = new ModelAndView("output");
		try {
			if (!userFile.isEmpty()) {
				userService.addAll(UploadParser.parseJson(userFile, User.class));
			}
			if (!eventFile.isEmpty()) {
				eventService.addAll(UploadParser.parseJson(eventFile, Event.class));
			}
			if (!auditoriumFile.isEmpty()) {
				auditoriumService.addAll(UploadParser.parseJson(auditoriumFile, Auditorium.class));
			}
			if (!ticketFile.isEmpty()) {
				bookingService.addAll(UploadParser.parseJson(ticketFile, Ticket.class));
			}
			modelAndView.addObject("output", IMPORT_SUCCESS);
		} catch (ClassNotFoundException | IOException e) {
			modelAndView.addObject("output", e.getMessage());
		}
		return modelAndView;
	}

}
