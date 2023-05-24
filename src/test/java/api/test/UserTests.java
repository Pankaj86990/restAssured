package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import api.endpoints.UserEndpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	 Faker f;
	  User userPayload; 
	//  Logger log = Logger.getLogger(UserTests.class.getName());
	  @BeforeClass
	  public void setupData() {
		  f =new Faker();
		  userPayload = new User();
		  userPayload.setId(f.idNumber().hashCode());
		  userPayload.setFirstname(f.name().firstName());
		  userPayload.setUsername(f.name().username());
		  userPayload.setLastname(f.name().lastName());
		  userPayload.setEmail(f.internet().emailAddress());
		  userPayload.setPhone(f.phoneNumber().cellPhone());
		 
		
	  }
	  @Test(priority =1)
	  public void testPostUser() {
		//log.info("**********************************sdxsdsfsdgdfgdfgdgfdsgsdbsdbsd");
		  Response respose= UserEndpoints.createUser(userPayload);
		  respose.then().log().all();
		  Assert.assertEquals(respose.getStatusCode(),200);
		  
	  }
	  @Test(priority=2)
	  public void getUserByName() {
		  Response respose= UserEndpoints.readUser(this.userPayload.getUsername());
		  respose.then().log().all();
		  Assert.assertEquals(respose.getStatusCode(),200);
}
	  
	  @Test(priority=3)
	  public void upadateUser() {
		  Response respose= UserEndpoints.updateUser(userPayload,this.userPayload.getUsername());
		  respose.then().log().all();
		  Assert.assertEquals(respose.getStatusCode(),200);
	  }
	  @Test(priority =4)
	  public void deleteUser() {
		  Response respose= UserEndpoints.deleteUser(this.userPayload.getUsername());
		  respose.then().log().all();
		  Assert.assertEquals(respose.getStatusCode(),200);
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

}
