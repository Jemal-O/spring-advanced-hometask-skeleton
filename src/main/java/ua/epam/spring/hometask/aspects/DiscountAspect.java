package ua.epam.spring.hometask.aspects;

import java.time.LocalDateTime;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

import ua.epam.spring.hometask.domain.Counter;
import ua.epam.spring.hometask.domain.CounterType;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.CounterService;

@Component
@Aspect
public class DiscountAspect {

	private CounterService counterService;

	@Autowired
	public void setCounterService(CounterService counterService) {
		this.counterService = counterService;
	}

	private Map<String, Integer> discountCounter = Maps.newHashMap();

	@Pointcut("execution(* ua.epam.spring.hometask.dao.DiscountServiceDao.getDiscount(..))")
	private void everyDiscountForUserMethod() {
	}

	@AfterReturning("everyDiscountForUserMethod() && args(user, event, airDateTime, numberOfTickets)")
	public void allDiscounts(JoinPoint jp, User user, Event event, LocalDateTime airDateTime, long numberOfTickets) {
		String userEmail = user.getEmail();
		if (!discountCounter.containsKey(userEmail)) {
			discountCounter.put(userEmail, 0);
		}
		discountCounter.put(userEmail, discountCounter.get(userEmail) + 1);
		System.out.println("Discount tickets for " + userEmail + " " + discountCounter.get(userEmail));
		// createCounter(CounterType.DISCOUNT_TOTAL,discountCounter);
	}

	private void createCounter(CounterType counterType, Map<String, Integer> counterMap) {
		for (String key : counterMap.keySet()) {
			counterService.save(new Counter(counterType, key, counterMap.get(key)));
		}
	}

}
