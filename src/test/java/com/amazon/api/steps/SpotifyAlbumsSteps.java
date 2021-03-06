package com.amazon.api.steps;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import org.apache.log4j.Logger;

import com.amazon.world.RestUtilities;
import com.amazon.world.World;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpotifyAlbumsSteps {
	private Logger log = Logger.getLogger(this.getClass());
	private World world;

	@Inject
	public SpotifyAlbumsSteps(World world) {
		this.world = world;
	}

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;

	@Inject
	@Named("SPOTIFY_ALBUMS_TRACKS")
	private String SPOTIFY_ALBUMS_TRACKS;

	@Inject
	@Named("SPOTIFY_ALBUMS")
	private String SPOTIFY_ALBUMS;

	@Inject
	@Named("SPOTIFY_BASE_PATH")
	private String SPOTIFY_BASE_PATH;

	@Inject
	@Named("SPOTIFY_ARTISTS")
	private String SPOTIFY_ARTISTS;
	

	@Inject
	@Named("SPOTIFY_SEVERAL_ARTISTS")
	private String SPOTIFY_SEVERAL_ARTISTS;
	

	@SuppressWarnings("unchecked")
	@When("^a user read the album track$")
	public void a_user_read_the_album_track() {
		reqSpec = world.api.getSpotifyRequestSpecification();
		reqSpec.basePath(SPOTIFY_BASE_PATH);

		resSpec = world.api.getResponseSpecification();

		RestUtilities.setEndPoint(SPOTIFY_ALBUMS_TRACKS);
		Response res = RestUtilities.getResponse(reqSpec, "get");

		res.then().body("items.size()", equalTo(18))
				.body("href",
						equalToIgnoringCase(
								"https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy/tracks?offset=0&limit=20"))
				.body("items.name", hasItem("Party Ain't Over"))
				.body("items.name", hasItems("Party Ain't Over", "Drinks for You (Ladies Anthem)"))
				.body("items[0].artists[0]", hasKey("uri"))
				.body("items.findAll{it.name=='Get It Started'}", hasItems(hasEntry("name", "Get It Started")))
				.body("items.name", hasItem("Get It Started")).body("items.size()", equalTo(18))
				.body("items.size()", greaterThan(17)).body("items.size()", lessThan(19))
				.body("items.size()", greaterThanOrEqualTo(18)).body("items.size()", lessThanOrEqualTo(18))
				.statusCode(200);

		res.then().assertThat().body(matchesJsonSchemaInClasspath("schema/album-track.json"));

		log.info("https://api.spotify.com/v1/albums/{id} is: " + res.prettyPrint());

	}

	@When("^a user read the album$")
	public void a_user_read_the_album() {
		reqSpec = world.api.getSpotifyRequestSpecification();
		reqSpec.basePath(SPOTIFY_BASE_PATH);

		resSpec = world.api.getResponseSpecification();

		RestUtilities.setEndPoint(SPOTIFY_ALBUMS);
		Response res = RestUtilities.getResponse(reqSpec, "get");

		res.then().assertThat().body(matchesJsonSchemaInClasspath("schema/album.json"));

		log.info("https://api.spotify.com/v1/albums/{id} is: " + res.prettyPrint());
	}

	@When("^a user get an Artist Album for id \"([^\"]*)\"$")
	public void a_user_get_an_Artist_Album_for_id(String artistId) throws Throwable {
		// https://api.spotify.com/v1/artists/{id}/albums
		reqSpec = world.api.getSpotifyRequestSpecification();
		reqSpec.basePath(SPOTIFY_BASE_PATH);

		resSpec = world.api.getResponseSpecification();

		RestUtilities.setEndPoint(SPOTIFY_ARTISTS);
		Response res = RestUtilities.getResponse(RestUtilities.createPathParam(reqSpec, "id", artistId), "get");
		res.then().assertThat().body(matchesJsonSchemaInClasspath("schema/artist.json"));

		log.info("https://api.spotify.com/v1/artists/{id}/albums is: " + res.prettyPrint());
	}
	

@When("^a user get an several Artist id \"([^\"]*)\"$")
public void a_user_get_an_several_Artist_id(String artistId) throws Throwable {
	
	//https://api.spotify.com/v1/artists
	reqSpec = world.api.getSpotifyRequestSpecification();
	reqSpec.basePath(SPOTIFY_BASE_PATH);

	resSpec = world.api.getResponseSpecification();

	RestUtilities.setEndPoint(SPOTIFY_SEVERAL_ARTISTS);
	Response res = RestUtilities.getResponse(RestUtilities.createQueryParam(reqSpec, "ids", artistId), "get");
	res.then().assertThat().body(matchesJsonSchemaInClasspath("schema/several_artist.json"));

	log.info("https://api.spotify.com/v1/artists is: " + res.prettyPrint());
}

}