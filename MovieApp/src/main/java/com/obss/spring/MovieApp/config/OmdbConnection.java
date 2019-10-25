package com.obss.spring.MovieApp.config;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

import com.obss.spring.MovieApp.controller.request.AddMovieFromImdb;
import com.obss.spring.MovieApp.model.Message;

public class OmdbConnection {

	private static final String PREFIXTITLE = "http://www.omdbapi.com/?t=";
	private static final String PREFIXID = "http://www.omdbapi.com/?i=";
	private static final String SUFFIX = "&apikey=b4223f1d";

	public OmdbConnection() {
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();

	}

	public static Double getImdbRatingFromImdb(String imdbId) {
		try (InputStream is = new URL(PREFIXID + imdbId + SUFFIX).openStream();) {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			if (json.has("imdbRating")) {
				return Double.parseDouble(json.getString("imdbRating"));
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public static Object getMovieInfo(String movieName) {
		OmdbConnection om = new OmdbConnection();
		JSONObject json = null;
		String name = movieName;
		try {
			json = new JSONObject(om.sendGet(name.replace(" ", "%20")));
		} catch (Exception e) {
			e.printStackTrace();
			return new Message("Movie name couldn't found");
		}
		if (json.has("Title")) {
			AddMovieFromImdb addMovieRequest = new AddMovieFromImdb();
			addMovieRequest.setName(movieName);
			addMovieRequest.setDirectorName(json.has("Director") ? json.getString("Director") : null);
			addMovieRequest.setDuration(
					json.has("Runtime") ? Integer.parseInt(json.getString("Runtime").replaceAll("[^\\d]", "")) : null);
			addMovieRequest.setReleasedate(
					json.has("Year") ? Integer.parseInt(json.getString("Year").replaceAll("[^\\d]", "")) : null);
			addMovieRequest.setGenre(json.has("Genre") ? json.getString("Genre") : null);
			addMovieRequest
					.setImdbRating(json.has("imdbRating") ? Double.parseDouble(json.getString("imdbRating")) : null);
			return addMovieRequest;

		}

		return new Message("Movie name not found");
	}

	private String sendGet(String movieName) throws Exception {

		String url = PREFIXTITLE + movieName + SUFFIX;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}

}

//URL url = new URL(uri);
//try(InputStream connection = url.openStream();) {
//	
//	BufferedReader rd = new BufferedReader(new InputStreamReader(connection, Charset.forName("UTF-8")));
//	String jsonText = readAll(rd);
