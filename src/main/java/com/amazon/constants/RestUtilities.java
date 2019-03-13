package com.amazon.constants;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestUtilities {

	@Inject
	@Named("CONSUMER_KEY")
	private String CONSUMER_KEY;

	@Inject
	@Named("CONSUMER_SECRET")
	private String CONSUMER_SECRET;

	@Inject
	@Named("ACCESS_TOKEN")
	private String ACCESS_TOKEN;

	@Inject
	@Named("ACCESS_SECRET")
	private String ACCESS_SECRET;

	@Inject
	@Named("BASE_URI")
	private String BASE_URI;

	public static String ENDPOINT;
	public static RequestSpecBuilder REQUEST_BUILDER;
	public static RequestSpecification REQUEST_SPEC;
	public static ResponseSpecBuilder RESPONSE_BUILDER;
	public static ResponseSpecification RESPONSE_SPEC;

	public static void setEndPoint(String epoint) {
		ENDPOINT = epoint;
	}

	public RequestSpecification getRequestSpecification() {
		AuthenticationScheme authScheme = RestAssured.oauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_SECRET);
		REQUEST_BUILDER = new RequestSpecBuilder();
		REQUEST_BUILDER.setBaseUri(BASE_URI);
		REQUEST_BUILDER.setAuth(authScheme);
		REQUEST_SPEC = REQUEST_BUILDER.build();
		return REQUEST_SPEC;
	}

	public ResponseSpecification getResponseSpecification() {
		RESPONSE_BUILDER = new ResponseSpecBuilder();
		RESPONSE_BUILDER.expectStatusCode(200);
		RESPONSE_BUILDER.expectResponseTime(lessThan(4L), TimeUnit.SECONDS);
		RESPONSE_SPEC = RESPONSE_BUILDER.build();
		return RESPONSE_SPEC;
	}

	public static RequestSpecification createQueryParam(RequestSpecification rspec, String param, String value) {
		return rspec.queryParam(param, value);
	}

	public static RequestSpecification createQueryParam(RequestSpecification rspec, Map<String, String> queryMap) {
		return rspec.queryParams(queryMap);
	}

	public static RequestSpecification createPathParam(RequestSpecification rspec, String param, String value) {
		return rspec.pathParam(param, value);
	}

	public static Response getResponse() {
		return given().get(ENDPOINT);
	}

	public static Response getResponse(RequestSpecification reqSpec, String type) {
		REQUEST_SPEC.spec(reqSpec);
		Response response = null;
		if (type.equalsIgnoreCase("get")) {
			response = given().spec(REQUEST_SPEC).get(ENDPOINT);
		} else if (type.equalsIgnoreCase("post")) {
			response = given().spec(REQUEST_SPEC).post(ENDPOINT);
		} else if (type.equalsIgnoreCase("put")) {
			response = given().spec(REQUEST_SPEC).put(ENDPOINT);
		} else if (type.equalsIgnoreCase("delete")) {
			response = given().spec(REQUEST_SPEC).delete(ENDPOINT);
		} else {
			System.out.println("Type is not supported");
		}
		// response.then().log().all();
		response.then().spec(RESPONSE_SPEC);
		return response;
	}

	public static JsonPath getJsonPath(Response res) {
		String path = res.asString();
		return new JsonPath(path);
	}

	public static XmlPath getXmlPath(Response res) {
		String path = res.asString();
		return new XmlPath(path);
	}

	public static void resetBathPath() {
		RestAssured.basePath = null;
	}

	public static void setContentType(ContentType type) {
		given().contentType(type);
	}

}
