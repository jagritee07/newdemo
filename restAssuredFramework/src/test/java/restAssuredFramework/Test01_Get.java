package restAssuredFramework;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Test01_Get {
	@Test
	void test_01() {
		Response response=RestAssured.get("https://reqres.in/api/users/2");
		System.out.println(response.asString());
		System.out.println(response.getBody());
		System.out.println(response.getStatusCode());
	}
	@Test
	void test_02() {
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body("{\r\n" + 
						"  \"location\": {\r\n" + 
						"    \"lat\": -38.383494,\r\n" + 
						"    \"lng\": 33.427362\r\n" + 
						"  },\r\n" + 
						"  \"accuracy\": 50,\r\n" + 
						"  \"name\": \"Frontline house\",\r\n" + 
						"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
						"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
						"  \"types\": [\r\n" + 
						"    \"shoe park\",\r\n" + 
						"    \"shop\"\r\n" + 
						"  ],\r\n" + 
						"  \"website\": \"http://google.com\",\r\n" + 
						"  \"language\": \"French-IN\"\r\n" + 
						"}\r\n" + 
						"")
				.when().post("maps/api/place/add/json")
				.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
				System.out.println(response);
				//Parsing the Json Response body using JsonPath class
				JsonPath js=new JsonPath(response); //for parsing Json i.e converting string to json
				String placeId=js.getString("place_id");
				System.out.println(placeId);
		
	}

}
