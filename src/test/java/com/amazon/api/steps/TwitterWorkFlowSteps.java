package com.amazon.api.steps;

import static io.restassured.RestAssured.given;

import org.apache.log4j.Logger;

import com.amazon.constants.RestUtilities;
import com.amazon.enums.Context;
import com.amazon.world.World;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TwitterWorkFlowSteps {
	private Logger log = Logger.getLogger(this.getClass());

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	private World world;
	
	@Inject
	public TwitterWorkFlowSteps(World world) {
		this.world = world;
	}
	
	
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
	@Named("STATUSES_TWEET_READ_SINGLE")
	private String STATUSES_TWEET_READ_SINGLE;
	

	@When("^a user post the tweet - \"([^\"]*)\"$")
	public void a_user_post_the_tweet(String tweetMessage) {
		// https://api.twitter.com/1.1/statuses/update.json?status=Hi There!!
	
		reqSpec = world.api.getRequestSpecification();
		reqSpec.basePath(STATUSES);
		
		resSpec = world.api.getResponseSpecification();
		
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
		String tweetId = jsPath.get("id_str");
		log.info("The response.path i.e tweetId: " + tweetId);
		world.scenarioContext.setContext(Context.TWEET_ID, tweetId);

	}
	
	@When("^user read the tweet$")
	public void user_read_the_tweet() {
		reqSpec = world.api.getRequestSpecification();
		reqSpec.basePath(STATUSES);
		
		resSpec = world.api.getResponseSpecification();
		
		RestUtilities.setEndPoint(STATUSES_TWEET_READ_SINGLE);
		String tweetId=(String)world.scenarioContext.getContext(Context.TWEET_ID);
		Response res = RestUtilities.getResponse(
				RestUtilities.createQueryParam(reqSpec, "id", tweetId), "get");
		String text = res.path("text");
		log.info("The tweet text is: " + text);

	}

	@When("^User delete the tweet$")
	public void user_delete_the_tweet() {
		reqSpec = world.api.getRequestSpecification();
		reqSpec.basePath(STATUSES);
		
		resSpec = world.api.getResponseSpecification();
		String tweetId=(String)world.scenarioContext.getContext(Context.TWEET_ID);
		given()
		.spec(RestUtilities.createPathParam(reqSpec, "id", tweetId))
	.when()
		.post(STATUSES_TWEET_DESTROY)
	.then()
		.spec(resSpec);
		
		log.info("Tweet deleted successfully");

	}

}