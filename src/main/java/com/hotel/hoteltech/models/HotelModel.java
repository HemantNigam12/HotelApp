package com.hotel.hoteltech.models;

import lombok.Data;

@Data
public class HotelModel implements BusinessModel {

	private static final long serialVersionUID = 1L;
	String hotelName;
	String hotelUrl;
	String rating;
	String currency;
	String minPrice;
}
