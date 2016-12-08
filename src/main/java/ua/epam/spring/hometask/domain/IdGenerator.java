package ua.epam.spring.hometask.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
	private static IdGenerator idGenerator;
	private AtomicInteger userId = new AtomicInteger();
	private AtomicInteger eventId = new AtomicInteger();
	private AtomicInteger ticketId = new AtomicInteger();
	private AtomicInteger auditoriumId = new AtomicInteger();

	private IdGenerator() {

	}

	public static IdGenerator getInstance() {
		if (idGenerator == null) {
			return idGenerator = new IdGenerator();
		} else {
			return idGenerator;
		}
	}

	public int getUserId() {
		return userId.incrementAndGet();
	}

	public int getTicketId() {
		return ticketId.incrementAndGet();
	}

	public int getEventId() {
		return eventId.incrementAndGet();
	}

	public int getAuditoriumId() {
		return auditoriumId.incrementAndGet();
	}
}
