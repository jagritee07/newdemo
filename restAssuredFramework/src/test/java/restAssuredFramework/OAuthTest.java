package restAssuredFramework;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import POJO.Api;
import POJO.GetCourses;
import POJO.webautomation;

public class OAuthTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		/*String exePath = "//C:\\Users\\jagriti\\Desktop\\driver\\chromedriver_win32/chromedriver.exe";
		 System.setProperty("webdriver.chrome.driver", exePath);
		 WebDriver driver = new ChromeDriver();
		 driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
			driver.findElement(By.cssSelector("input[type='email']")).sendKeys("thakurstuti07@gmail.com");
			driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("input[type='password']")).sendKeys("jag140798");
			driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
			Thread.sleep(4000);
			String url=driver.getCurrentUrl();
			System.out.println(url);*/
		
		String[] courseTitles= { "Selenium Webdriver Java","Cypress","Protractor"};
		String url="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F2wGE9qW8UGbQyVa16W2GETKyLfqrRrMZtmFPr5flOXI0Sv70_7xvTmHVVHvO9HFcWijT2lNd8SLlEZIDUFlNmbk&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=1&prompt=none#";
		
		
		
		/*String accessTokenResponse=	given().
				queryParams("code","")
				.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type","authorization_code")
				.when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();
			JsonPath js=new JsonPath(accessTokenResponse);
			String accessToken=js.getString("access_token");
			

			String response=given().queryParam("access_token", accessToken)
			.when().log().all()
			.get("https://rahulshettyacademy.com/getCourse.php").asString();
			
			System.out.println(response);*/

		String partialcode=url.split("code=")[1];
		String code=partialcode.split("&scope")[0];

		System.out.println(code);

		String response =

				given() .urlEncodingEnabled(false).queryParams("code",code)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("grant_type", "authorization_code")
				.queryParams("state", "verifyfjdss")
				.queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")

				// .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		// System.out.println(response);

		JsonPath jsonPath = new JsonPath(response);
		String accessToken = jsonPath.getString("access_token");
		System.out.println(accessToken);

		String r2=    given().contentType("application/json").

				queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
				.when().get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(r2);
		GetCourses gc=given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourses.class);
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		

		List<Api> apiCourses=gc.getCourses().getApi();
		for(int i=0;i<apiCourses.size();i++)
		{
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
					{
				System.out.println(apiCourses.get(i).getPrice());
					}
		}
		
		//Get the course names of WebAutomation
				ArrayList<String> a= new ArrayList<String>();
				
				
				List<webautomation> w=gc.getCourses().getWebAutomation();
				
				for(int j=0;j<w.size();j++)
				{
					a.add(w.get(j).getCourseTitle());
				}
				List<String> expectedList=	Arrays.asList(courseTitles);
				
				Assert.assertTrue(a.equals(expectedList));
				
				
		
		
		
		

		



		}
			
			
			

	}

