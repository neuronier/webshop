package hu.neuron.ier.core;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
	private static final Logger logger = Logger.getLogger(LoggerAspect.class);

	@Before("execution(* hu.neuron.ier.core.*.*.*(..))")
	public void initElements(JoinPoint joinPoint) {
		
		logger.info(System.getProperty("weblogic.Name") +" " +joinPoint.getSignature().toShortString());
	}
}