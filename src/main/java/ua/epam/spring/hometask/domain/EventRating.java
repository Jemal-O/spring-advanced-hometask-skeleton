package ua.epam.spring.hometask.domain;

/**
 * @author Yuriy_Tkach
 */
public enum EventRating {

	LOW("low"),

	MID("mid"),

	HIGH("high");

	private String name;

	EventRating(String name) {
		this.name = name;
	}

	public String getRatingName() {
		return name;
	}

}
