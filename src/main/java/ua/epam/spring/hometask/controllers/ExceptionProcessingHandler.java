package ua.epam.spring.hometask.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionProcessingHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView exception(Exception e) {
		ModelAndView modelAndView = new ModelAndView("error");
		String message = e.getMessage();
		modelAndView.addObject("mes", message);
		return modelAndView;
	}
}
