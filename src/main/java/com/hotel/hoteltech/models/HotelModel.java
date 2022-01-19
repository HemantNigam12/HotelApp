package com.hotel.hoteltech.models;

import java.util.List;
import lombok.Data;
/*Model for hotel details*/

@Data
public class HotelModel implements BusinessModel {

	private static final long serialVersionUID = 7848212620071982171L;
	String hotelName;
	String hotelUrl;
	String rating;
	String currency;
	String minPrice;
	List<RoomInfoModel> roomDetails;
}
