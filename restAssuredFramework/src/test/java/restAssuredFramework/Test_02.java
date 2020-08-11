package restAssuredFramework;

import static io.restassured.RestAssured.*;


import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;


public class Test_02 {
@Test
public void test_01() {
	baseURI= "http://localhost:3000/";
	given().get("/users").then().statusCode(200).log().all();
}

@Test
public void test_02() {
	baseURI= "http://localhost:3000/";
	given().param("subjectname","Automation").get("/subjects").then().statusCode(200).log().all();
}

@Test
public void test_03() {
	JSONObject response = new JSONObject();
	response.put( "firstname", "Tom");
	response.put("lastname", "Felton");
	response.put("subjectid", 1);
	baseURI= "http://localhost:3000/";
	given().contentType(ContentType.JSON).accept(ContentType.JSON).body(response.toJSONString()).when().post("/users").then().statusCode(201).log().all();
	
	
}

@Test
public void test_04() {
	JSONObject response = new JSONObject();
	response.put("lastname","Felton");
	baseURI= "http://localhost:3000/";
	given().contentType(ContentType.JSON).accept(ContentType.JSON).body(response.toJSONString()).when().patch("/users/2").then().statusCode(200).log().all();
	
	
}






}
