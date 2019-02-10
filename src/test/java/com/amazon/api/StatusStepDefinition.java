package com.amazon.api;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;

import com.amazon.constants.EndPoints;
import com.amazon.constants.Path;

import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StatusStepDefinition {

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	String tweetId = "";

	@BeforeClass
	public void setup() {
		reqSpec = RestUtilities.getRequestSpecification();
		reqSpec.basePath(Path.STATUSES);

		resSpec = RestUtilities.getResponseSpecification();
	}

	@When("^a user post the tweet$")
	public void a_user_post_the_tweet(String tweetMessage) {
		// https://api.twitter.com/1.1/statuses/update.json?status=Hi There!!
		Response response = given().spec(RestUtilities.createQueryParam(reqSpec, "status", tweetMessage)).when()
				.post(EndPoints.STATUSES_TWEET_POST).then().spec(resSpec).extract().response();
		JsonPath jsPath = RestUtilities.getJsonPath(response);
		tweetId = jsPath.get("id_str");

	}

	@When("^User delete the tweet$")
	public void user_delete_the_tweet() {
		given().spec(RestUtilities.createPathParam(reqSpec, "id", tweetId)).when()
				.post(EndPoints.STATUSES_TWEET_DESTROY).then().spec(resSpec);

	}

}