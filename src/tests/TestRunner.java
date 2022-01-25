package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Tanner Patterson - tpatterson4
 * CIS175 or CIS152 - SPRING
 * Jan 24, 2022
 */
public class TestRunner 
{
	public static void main(String[] args) //Class to run all tests
	{
		Result result = JUnitCore.runClasses(AllTests.class);
		
		for (Failure failure : result.getFailures())
		{
			System.out.println(failure.toString());
		}
		
		System.out.println(result.wasSuccessful());
	}
}
