package com.mab.merchantapi.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mab.merchantapi.audit.AuditLog;

@Aspect
@Component
public class LoggingAspect {

	@Autowired
	AuditLog service;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(* com.mab.intercom.fe.restController.*.*(..))")
	private void forControllerPackage() {
	}

	@Pointcut("execution(* com.mab.intercom.fe.service.impl.*.*(..))")
	private void forServicePackage() {
		
	}

	@Pointcut("execution(* com.mab.intercom.fe.dao.impl.*.*(..))")
	private void forDaoPackage() {
	}

	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {
	}

	@Before("forServicePackage()")
	public void beforeService(JoinPoint joinPoint) {
//		try {
//			service.log();
//			logger.info("Test");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {			
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		String URI = request.getRequestURI();
		String apiMethodType = request.getMethod();
		String theMethod = joinPoint.getSignature().toShortString();

		logger.info("\n");
		logger.info("-------------------------------------------------------------");
		logger.info("@Before : " + URI + " , Method Type : " + apiMethodType);
		logger.info("Method : " + theMethod);
		logger.info("Arguments :  " + Arrays.toString(joinPoint.getArgs()));

//		if (null != request) {
//            logger.info("Start Header Section of request ");
//            Enumeration headerNames = request.getHeaderNames();
//            while (headerNames.hasMoreElements()) {
//                String headerName = (String) headerNames.nextElement();
//                String headerValue = request.getHeader(headerName);
//                logger.info("Header Name: " + headerName + " Header Value : " + headerValue);
//            }
//            logger.info("Request Path info :" + request.getServletPath());
//            logger.info("End Header Section of request ");
//        }
	}

	@AfterReturning(pointcut = "forAppFlow()", returning = "theResult")
	public void after(JoinPoint joinPoint, Object theResult) {

		String theMethod = joinPoint.getSignature().toShortString();
		logger.info("@After, Method : " + theMethod);
		
		try {
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getResponse();
			logger.info("Return http status :" + response.getStatus());
			
			//try to parse result to json if want more detail
			//Gson gson = new Gson();
		    //logger.info("Return result : " + gson.toJson(theResult));
		}catch(Exception ex) {
			//logger.info("Return result : " + theResult);			
		}
		
		logger.info("-------------------------------------------------------------\n");
	}
}
