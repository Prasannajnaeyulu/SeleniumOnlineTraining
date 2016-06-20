package com.google.gmail.common.listerners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.TestResult;


public class TestListener implements IInvokedMethodListener{
	public static int validationfailures = 0;

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		//Assert.assertEquals("validation failures shouldnot be zero",0, validationfailures);
		if(validationfailures>0)
		{
			System.out.println("Found one or more Validation Failures in test"+method.getTestMethod().getMethod().getName());
			result.setStatus(TestResult.FAILURE);
		}
		//write some code to connect to test result database
	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		validationfailures = 0;
	}

}
