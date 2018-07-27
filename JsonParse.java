package com.parse.jsonfile;

import org.json.*;
import org.json.simple.JSONArray;

public class JsonParse {
	public static void main(String[] args) {
		JsonParse jsonParser = new JSONParser();

		Object object;



		try {



			object = jsonParser.parse(new FileReader("nestedobjects.json"));

			JSONObject jsonObject = (JSONObject) object;


	

}
