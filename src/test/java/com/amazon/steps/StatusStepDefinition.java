package com.amazon.steps;

import static io.restassured.RestAssured.given;

import com.amazon.constants.RestUtilities;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StatusStepDefinition {

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	String tweetId = "";
	

	@Inject
	private RestUtilities restUtilities;
	
	@Inject
	@Named("STATUSES")
	private String STATUSES;

	@Inject
	@Named("STATUSES_TWEET_POST")
	private String STATUSES_TWEET_POST;

	@Inject
	@Named("STATUSES_TWEET_DESTROY")
	private String STATUSES_TWEET_DESTROY;
	
	@Inject
	@Named("STATUSES_USER_TIMELINE")
	private String STATUSES_USER_TIMELINE;
	
	@Inject
	@Named("STATUSES_TWEET_READ_SINGLE")
	private String STATUSES_TWEET_READ_SINGLE;
	

	@When("^a user post the tweet - \"([^\"]*)\"$")
	public void a_user_post_the_tweet(String tweetMessage) {
		// https://api.twitter.com/1.1/statuses/update.json?status=Hi There!!
	
		reqSpec = restUtilities.getRequestSpecification();
		reqSpec.basePath(STATUSES);
		
		resSpec = restUtilities.getResponseSpecification();
		
		Response response =
				given()
					.spec(RestUtilities.createQueryParam(reqSpec, "status", tweetMessage))
				.when()
					.post(STATUSES_TWEET_POST)
				.then()
					.spec(resSpec)
					.extract()
					.response();
		JsonPath jsPath = RestUtilities.getJsonPath(response);
		tweetId = jsPath.get("id_str");
		System.out.println("The response.path i.e tweetId: " + tweetId);

	}
	
	@When("^user read the tweet$")
	public void user_read_the_tweet() {
		reqSpec = restUtilities.getRequestSpecification();
		reqSpec.basePath(STATUSES);
		
		resSpec = restUtilities.getResponseSpecification();
		
		RestUtilities.setEndPoint(STATUSES_TWEET_READ_SINGLE);
		Response res = RestUtilities.getResponse(
				RestUtilities.createQueryParam(reqSpec, "id", tweetId), "get");
		String text = res.path("text");
		System.out.println("The tweet text is: " + text);

	}

	@When("^User delete the tweet$")
	public void user_delete_the_tweet() {
		reqSpec = restUtilities.getRequestSpecification();
		reqSpec.basePath(STATUSES);
		
		resSpec = restUtilities.getResponseSpecification();
		
		given()
		.spec(RestUtilities.createPathParam(reqSpec, "id", tweetId))
	.when()
		.post(STATUSES_TWEET_DESTROY)
	.then()
		.spec(resSpec);

	}

}