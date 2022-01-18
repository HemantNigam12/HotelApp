package com.hotel.hoteltech.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.hotel.hoteltech.config.Credentials;
import com.hotel.hoteltech.models.HotelModel;

@Service
public class SearchHotelService {

	public List<HotelModel> getHotelsDetails(String city, String checkinDate) throws Exception {

		List<HotelModel> hotelList = new ArrayList<HotelModel>();
		try {
			URL url = new URL(
					"https://rest.reserve-online.net/availability?location=" + city + "&checkin=" + checkinDate);
			String credentials = Credentials.USER_NAME + ":" + Credentials.PASS_WORD;
			String encoding3 = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + encoding3);
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			String output;
			StringBuilder builder = new StringBuilder();
			while ((output = br.readLine()) != null) {
				builder.append(output + "\n");
			}
			JSONObject json = new JSONObject(builder.toString());
			JSONObject curObj = (JSONObject) json.get("data");
			System.out.println(curObj.get("hotels"));
			JSONArray array = curObj.getJSONArray("hotels");
			for (int i = 0; i < array.length(); i++) {
				HotelModel data = new HotelModel();
				data.setHotelName(array.getJSONObject(i).getString("name"));
				data.setHotelUrl(array.getJSONObject(i).getString("url"));
				data.setRating(String.valueOf(array.getJSONObject(i).getInt("rating")));
				data.setCurrency(array.getJSONObject(i).getString("currency"));
				hotelList.add(data);
			}
			System.out.println("hotelList--" + hotelList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hotelList;
	}

}
