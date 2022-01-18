package com.hotel.hoteltech.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.hotel.hoteltech.models.HotelModel;
import com.hotel.hoteltech.services.SearchHotelService;

@RestController
public class SearchController {

	@Autowired
	SearchHotelService searchHotelService;

	@RequestMapping({ "/searchhotels" })
	public ModelAndView searchotels() {
		return new ModelAndView("search_hotels");
	}

	@RequestMapping({ "/getHotelsDetails" })
	public ModelAndView getHotelsResults(@RequestParam(value = "city", required = true) String city,
			@RequestParam(value = "checkindt", required = true) String checkindt) throws Exception {

		ModelAndView model = new ModelAndView("hotels_details");
		try {
			List<HotelModel> hotelDetails = searchHotelService.getHotelsDetails(city, checkindt);
			if(hotelDetails != null && !hotelDetails.isEmpty()) {
			model.addObject("hotelDetails", hotelDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model; 
	}

}
