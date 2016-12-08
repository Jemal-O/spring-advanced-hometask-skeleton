package ua.epam.spring.hometask.aspects;

import java.util.Map;
import java.util.Random;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

import ua.epam.spring.hometask.domain.Counter;
import ua.epam.spring.hometask.domain.CounterType;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.CounterService;

@Component
@Aspect
public class LuckyWinnerAspect {

	private CounterService counterService;

	@Autowired
	public void setCounterService(CounterService counterService) {
		this.counterService = counterService;
	}

	private Map<String, Integer> counterLuckyWinner = Maps.newHashMap();

	@Pointcut("execution(* ua.epam.spring.hometask.dao.*.checkLucky(..))")
	private void checkLuckyTicketMethod() {
	}

	@After("checkLuckyTicketMethod () && args(ticket, user)")
	public void countSetTicketPriceToZero(JoinPoint jp, Ticket ticket, User user) {
		String userEmail = user.getEmail();
		if (!counterLuckyWinner.containsKey(userEmail)) {
			counterLuckyWinner.put(userEmail, 0);
		}
		counterLuckyWinner.put(userEmail, counterLuckyWinner.get(userEmail) + 1);
		System.out.println("Lucky winner is " + userEmail + " " + counterLuckyWinner.get(userEmail));
		createCounter(CounterType.LUCKY_WINNER, counterLuckyWinner);
	}

	public boolean getRandomBoolean() {
		Random random = new Random();
		return random.nextBoolean();
	}

	private void createCounter(CounterType counterType, Map<String, Integer> counterMap) {
		for (String key : counterMap.keySet()) {
			counterService.save(new Counter(counterType, key, counterMap.get(key)));
		}
	}

}
