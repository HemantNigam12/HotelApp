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
import com.hotel.hoteltech.models.RoomInfoModel;

@Service
public class SearchHotelService {

	/*Calling api to get hoteldetails*/
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
			/*Creating json for hoteldetails*/
			JSONArray array = curObj.getJSONArray("hotels");
			for (int i = 0; i < array.length(); i++) {
				HotelModel data = new HotelModel();
				List<RoomInfoModel> roomList = new ArrayList<RoomInfoModel>();
				/*Creating json for roomdetails*/
				JSONArray arrayRate = array.getJSONObject(i).getJSONArray("rates");
				for (int j = 0; j < arrayRate.length(); j++) {
					RoomInfoModel roomData = new RoomInfoModel();
					roomData.setRoomUrl(arrayRate.getJSONObject(j).getString("roomurl"));
					roomData.setRoomType(arrayRate.getJSONObject(j).getString("type"));
					roomData.setPrice(String.valueOf(arrayRate.getJSONObject(j).getFloat("price")));
					roomData.setAvailableRooms(String.valueOf(arrayRate.getJSONObject(j).getInt("remaining")));
					roomList.add(roomData);
				}
				data.setHotelName(array.getJSONObject(i).getString("name"));
				data.setHotelUrl(array.getJSONObject(i).getString("url"));
				data.setRating(String.valueOf(array.getJSONObject(i).getInt("rating")));
				data.setCurrency(array.getJSONObject(i).getString("currency"));
				data.setMinPrice(String.valueOf(array.getJSONObject(i).getFloat("minprice")));
				data.setRoomDetails(roomList);
				hotelList.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hotelList;
	}

}
