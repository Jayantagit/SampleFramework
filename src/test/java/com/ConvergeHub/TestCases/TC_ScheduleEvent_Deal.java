package com.ConvergeHub.TestCases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ConvergeHub.Base.Base;
import com.ConvergeHub.Pages.DealPage;
import com.ConvergeHub.Pages.LeadPage;
import com.ConvergeHub.Pages.LoginPage;


public class TC_ScheduleEvent_Deal extends Base  
{    
	@Test(groups={"Regression"},description="Schedule Event for Deal")
	
	public static void ScheduleEventforDeal() throws InterruptedException
	{
		LeadPage lead=new LeadPage();
		DealPage deal=new DealPage();
		
		/*-------------Login section needed if executed this TC separately
		LoginPage login=new LoginPage();		
		login.username.clear();
	    login.username.sendKeys(config.getProperty("UserName"));
		login.password.sendKeys(config.getProperty("Password"));
	    login.login.click();
	    System.out.println("Successfully Logged");
	    wait=new WebDriverWait(driver,20); 
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'My Dashboard')]")));
	    */
	  
	    driver.get("https://staging.convergehub.com/deals/");
	    
	    try
	    {
	        List<WebElement> Leads_num=driver.findElements(By.xpath(OR.getProperty("LeadCheckbox")));
	          
	        if(Leads_num.size()>0)
	        {
	        	Leads_num.get(0).click();//Click the First Deal in the List
	        }
	        else
	        {
	        	System.out.println("No deal is present in the Deal List");      
	        }
	    	
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    
	    //Click the Select dropdown for the First Deal in the List
	    lead.LeadListSelection.click();
	    
	    //Select the ---Schedule Event option
	    driver.findElement(By.linkText("Schedule Event")).click();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	  //Set Focus on Event Subject
	    new Actions(driver).moveToElement(lead.EventSubject).build().perform();
	    
	  //Enter the value in the Subject
	    lead.EventSubject.sendKeys("Test Event for Deal");
	    
	  //Enter the description in Subject
	    lead.EventDescription.sendKeys("Sample Description");
	    
	    //Click the Save button for the new Event
	    lead.NotesSaveBtn.click();	
	    
	  //Validation Step-Event Schedule added successfully
	    Assert.assertEquals(driver.findElement(By.xpath("//span[@id='header_notification_msg']")).getText(), "Event Relation Added");
	    
	}
      
}
