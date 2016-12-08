package ua.epam.spring.hometask.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;

@Controller
public class AuditoriumController {
	private static final String SUCCESS_SAVING = "Auditorium was successfully saved";
	@Autowired
	private AuditoriumService auditoriumService;

	@RequestMapping(value = "/auditoriums", method = RequestMethod.GET)
	public ModelAndView goToAuditoriumsAdmin() {
		ModelAndView mav = new ModelAndView("auditoriums");
		mav.addObject("auditoriums", auditoriumService.getAll());
		return mav;
	}

	@RequestMapping(value = "/auditoriums/{auditoriumId}", method = RequestMethod.GET)
	public ModelAndView getAuditoriumById(@PathVariable int auditoriumId) {
		ModelAndView modelAndView = new ModelAndView("auditoriumById");
		Auditorium auditorium = auditoriumService.getById(auditoriumId);
		return modelAndView.addObject("auditorium", auditorium);
	}

	@RequestMapping(value = "/auditorium/{name}", method = RequestMethod.GET)
	public ModelAndView getAuditoriumByName(@PathVariable String name) {
		ModelAndView modelAndView = new ModelAndView("auditoriumByName");
		Auditorium auditorium = auditoriumService.getByName(name);
		return modelAndView.addObject("auditorium", auditorium);
	}

	@RequestMapping(value = "/auditoriums/assigned", method = RequestMethod.GET)
	public ModelAndView getAssigned() {
		List<Auditorium> auditoriums = auditoriumService.getAssignedAuditoriums();
		ModelAndView modelAndView = new ModelAndView("auditoriumsList");
		return modelAndView.addObject("auditoriumsList", auditoriums);
	}

	@RequestMapping(value = "/auditoriums/get-all/pdf", method = RequestMethod.GET, headers = "accept=application/pdf")
	public ModelAndView getAllAuditoriums() {
		Map<String, String> data = new LinkedHashMap<String, String>();
		data.put("ID", "Auditorium");
		for (Auditorium auditorium : auditoriumService.getAll()) {
			data.put(auditorium.getId().toString(), auditorium.toString());
		}
		return new ModelAndView("PdfViewResolver", "data", data);
	}

	@RequestMapping(value = "/auditoriums/auditorium/create/", method = RequestMethod.POST)
	public ModelAndView createAndSaveAuditorium(@RequestParam Integer numberOfSeats,
			@RequestParam String auditoriumName, @RequestParam Boolean isAssign) {
		auditoriumService.add(new Auditorium(auditoriumName, numberOfSeats, isAssign));
		ModelAndView modelAndView = new ModelAndView("output");
		return modelAndView.addObject("output", SUCCESS_SAVING);
	}

}
