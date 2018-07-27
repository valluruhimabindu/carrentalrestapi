package com.parse.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//import com.wbl.api_automation.helper.RestResponse;

/*import REST_HTTPCLIENT.HttpClient_automation.ApiTesting;*/

public class ParseJson {

	public static JSONObject object;
	//public static RestResponse response = null;
	static JSONObject singleobj;
	static JSONObject carobj;
	// static int length = 0;
	static JSONArray jsonArray;
	String carname;
	Long car_price;
	Double yoymaintenancecost;
	Double depreciation;
	Long yeartodate;

	Long car_discount;

	public int getobject_no() throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		Object object;
		object = jsonParser.parse(new FileReader("E:/HImabindu/HttpClient.com/resources/resource.json"));
		jsonArray = (JSONArray) object;
		int object_size = jsonArray.size();
		return object_size;

	}

	public void Basedon_blue_tesla(int length) {

		for (int i = 0; i < length; i++) {
			singleobj = (JSONObject) jsonArray.get(i);

			JSONObject carobj = (JSONObject) singleobj.get("Car");

			String carname = (String) carobj.get("make");

			JSONObject metadataobj = (JSONObject) carobj.get("metadata");
			// now get the colour
			String colour = (String) metadataobj.get("Color");
			// System.out.println(colour);

			if (carname.equals("Tesla") & colour.equals("Blue")) {
				
				System.out.println("******colur-BLUE  &&  car-- Tesla********");
				System.out.println("CAR NAME IS----" + carname);
				System.out.println("CAR COLOUR IS----" + colour);
				System.out.println("NOTES IS---" + (String) metadataobj.get("Notes"));
				System.out.println();

			}
		} // close for

	}// close method

	public void lowprice_car(int length) {
		
		ArrayList al = new ArrayList<>();
		List<String> carname_lowestprice = new ArrayList<>();
		for (int i = 0; i < length; i++) {

			singleobj = (JSONObject) jsonArray.get(i);
			JSONObject carobj = (JSONObject) singleobj.get("Car");
			carname = (String) carobj.get("make");
			JSONObject perdayrent_obj = (JSONObject) carobj.get("perdayrent");
			// now get the colour

			Long car_price = (Long) perdayrent_obj.get("Price");

			al.add(car_price);
			// close for
		}
		// get lowest price
		Collections.sort(al);

		for (int i = 0; i < al.size(); i++) {

			carname_lowestprice.add("CAR NAME IS-" + carname + " , " + " PRICE IS-" + al.get(i));
			// System.out.println(carname_lowestprice);
			if (i != al.size() - 1) {
				if (!al.get(i).equals(al.get(i + 1)))

				{
					break;
				}
			}
		}
		System.out.println("***********The LOWEST  PRICE CAR DETAILS*********");
		System.out.println(carname_lowestprice);
		System.out.println();
	}

	public void price_afterDiscount(int length) {

		ArrayList actual_carprice = new ArrayList<>();
		List<String> carname_lowestprice_afterdiscount = new ArrayList<>();
		for (int i = 0; i < length; i++) {

			singleobj = (JSONObject) jsonArray.get(i);
			JSONObject carobj = (JSONObject) singleobj.get("Car");
			carname = (String) carobj.get("make");
			JSONObject perdayrent_obj = (JSONObject) carobj.get("perdayrent");
			// now get the colour

			car_price = (Long) perdayrent_obj.get("Price");
			car_discount = (Long) perdayrent_obj.get("Discount");

			actual_carprice.add(car_price - car_discount);
			// close for
		}
		// get lowest price
		Collections.sort(actual_carprice);

		for (int i = 0; i < actual_carprice.size(); i++) {

			carname_lowestprice_afterdiscount.add("CAR NAME IS-" + carname + " , " + "DISCOUNT IS-" + car_discount
					+ "   " + "After discount price is--" + (car_price - car_discount));
			// System.out.println(carname_lowestprice);
			if (i != actual_carprice.size() - 1) {
				if (!actual_carprice.get(i).equals(actual_carprice.get(i + 1)))

				{
					break;
				}
			}
		}
		System.out.println("*********The LOWSET DISCOUNT  CAR DETAILS********");
		System.out.println(carname_lowestprice_afterdiscount);
		System.out.println();

	}

	public void highestRevenue_car(int length) {
		ArrayList allRevenue_list = new ArrayList<>();
		List<String> carname_highest_revenue = new ArrayList<>();

		for (int i = 0; i < length; i++) {

			singleobj = (JSONObject) jsonArray.get(i);
			JSONObject carobj = (JSONObject) singleobj.get("Car");
			carname = (String) carobj.get("make");
			JSONObject perdayrent_obj = (JSONObject) carobj.get("perdayrent");
			// now get the colour

			car_price = (Long) perdayrent_obj.get("Price");
			

			car_discount = (Long) perdayrent_obj.get("Discount");

			JSONObject metrics_obj = (JSONObject) carobj.get("metrics");
			yoymaintenancecost = (Double) metrics_obj.get("yoymaintenancecost");

			depreciation = (Double) metrics_obj.get("depreciation");

			JSONObject rentalcount = (JSONObject) metrics_obj.get("rentalcount");
			yeartodate = (Long) rentalcount.get("yeartodate");

			allRevenue_list.add(((car_price - car_discount) * yeartodate) - ((yoymaintenancecost + depreciation)));

			// close for
		}
		// get lowest price

		Collections.reverse(allRevenue_list);

		for (int i = allRevenue_list.size() - 1; i >= 0; i--) {

			carname_highest_revenue.add("CAR NAME IS-" + carname + " , " + "TOTAL EXPENSIVE-"
					+ (yoymaintenancecost + depreciation) + "   " + "REVENUE IS--"
					+ (((car_price - car_discount) * yeartodate) - ((yoymaintenancecost + depreciation))));

			if (i != allRevenue_list.size() - 1) {
				if (!allRevenue_list.get(i).equals(allRevenue_list.get(i + 1)))

				{
					break;
				}
			}

		}
		System.out.println("**********HIGHEST REVENUE DETAILS*******");
		System.out.println(carname_highest_revenue);
		System.out.println();

	}

	/*public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

		ParseJson obj = new ParseJson();
		obj.Basedon_blue_tesla(obj.getobject_no());
		obj.lowprice_car(obj.getobject_no());
		obj.price_afterDiscount(obj.getobject_no());
		obj.highestRevenue_car(obj.getobject_no());

	}*/

}
