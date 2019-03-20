package com.amazon.api.steps;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.amazon.world.RestUtilities;
import com.amazon.world.World;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TwitterTimelineSteps {
	private Logger log = Logger.getLogger(this.getClass());

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	private World world;

	@Inject
	public TwitterTimelineSteps(World world) {
		this.world = world;
	}

	@Inject
	@Named("STATUSES")
	private String STATUSES;

	@Inject
	@Named("STATUSES_USER_TIMELINE")
	private String STATUSES_USER_TIMELINE;

	@Inject
	@Named("STATUSES_MENTION_TIMELINE")
	private String STATUSES_MENTION_TIMELINE;

	@When("^a user read the user timeline$")
	public void a_user_read_the_user_timeline() {

		// https://api.twitter.com/1.1/statuses/home_timeline.json

		reqSpec = world.api.getTwitterRequestSpecification();
		reqSpec.basePath(STATUSES);

		resSpec = world.api.getResponseSpecification();

		RestUtilities.setEndPoint(STATUSES_USER_TIMELINE);
		Response res = RestUtilities.getResponse(reqSpec, "get");
		//log.info("User Timeline default count is 20 is: " + res.prettyPrint());
	}

	@When("^a user read the user timeline with count as \"([^\"]*)\"$")
	public void a_user_read_the_user_timeline_with_count_as(String count) {

		reqSpec = world.api.getTwitterRequestSpecification();
		reqSpec.basePath(STATUSES);

		resSpec = world.api.getResponseSpecification();

		RestUtilities.setEndPoint(STATUSES_USER_TIMELINE);
		Response res = RestUtilities.getResponse(RestUtilities.createQueryParam(reqSpec, "count", count), "get");
		//log.info("User Timeline with count as " + count + " is: " + res.prettyPrint());
		ArrayList<String> screenNameList = res.path("user.screen_name");
		Assert.assertTrue(screenNameList.contains("iam_sethi"));
	}

	@When("^a user read the mention timeline$")
	public void a_user_read_the_mention_timeline() {

		// https://api.twitter.com/1.1/statuses/home_timeline.json

		reqSpec = world.api.getTwitterRequestSpecification();
		reqSpec.basePath(STATUSES);

		resSpec = world.api.getResponseSpecification();

		RestUtilities.setEndPoint(STATUSES_MENTION_TIMELINE);
		Response res = RestUtilities.getResponse(reqSpec, "get");
		//log.info("User Mention Timeline default count is 20 is: " + res.prettyPrint());
	}

	@When("^a user read the mention timeline with count as \"([^\"]*)\"$")
	public void a_user_read_the_mention_timeline_with_count_as(String count) {

		reqSpec = world.api.getTwitterRequestSpecification();
		reqSpec.basePath(STATUSES);

		resSpec = world.api.getResponseSpecification();

		RestUtilities.setEndPoint(STATUSES_MENTION_TIMELINE);
		Response res = RestUtilities.getResponse(RestUtilities.createQueryParam(reqSpec, "count", count), "get");
	//	log.info("User Mention Timeline with count as " + count + " is: " + res.prettyPrint());
	}

}