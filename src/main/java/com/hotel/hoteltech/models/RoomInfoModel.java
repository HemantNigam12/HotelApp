package com.hotel.hoteltech.models;

import lombok.Data;
/*Model for room details*/
@Data
public class RoomInfoModel implements BusinessModel {

	private static final long serialVersionUID = -5816028299306529174L;
	
	String roomUrl;
	String price;
	String availableRooms;
	String roomType;

}
