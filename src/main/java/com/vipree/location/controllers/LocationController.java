package com.vipree.location.controllers;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vipree.location.entities.Location;
import com.vipree.location.repos.LocationRepository;
import com.vipree.location.service.LocationService;
import com.vipree.location.util.EmailUtil;
import com.vipree.location.util.ReportUtil;

@Controller
public class LocationController {
	
	@Autowired
	LocationService service;
	
	@Autowired
	EmailUtil util;
	
	@Autowired
	ReportUtil report;
	
	@Autowired
	LocationRepository repo;
	
	@Autowired
	ServletContext sc;
	
	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}
	
	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		Location loc = service.saveLocation(location);
		String msg = "Location saved for id "+loc.getId();
		modelMap.addAttribute("msg", msg);
		/*util.sendEmailString("vpmailconnect@gmail.com", "Location saved", 
				"location saved successfulyy and about to return response");*/
		return "createLocation";
	}

	@RequestMapping("/displayLocations")
	public String displayLocations(ModelMap modelMap) {
		List<Location> locs = service.getAllLocations();
		modelMap.addAttribute("locations", locs);
		return "displayLocations";
	}
	
	@RequestMapping("/deleteLocation")
	public String deleteLocation(@RequestParam("id") int id, ModelMap modelMap) {
		Location loc = new Location();
		loc.setId(id);
		service.deleteLocation(loc);
		List<Location> locs = service.getAllLocations();
		modelMap.addAttribute("locations", locs);
		return "displayLocations";
	}
	
	@RequestMapping("/updateLocation")
	public String showUpdate(@RequestParam("id") int id, ModelMap modelMap) {
		Location loc = service.getLocationById(id);
		modelMap.addAttribute("location", loc);
		return "editLocation";
	}
	
	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		service.saveLocation(location);
		List<Location> locs = service.getAllLocations();
		modelMap.addAttribute("locations", locs);
		return "displayLocations";
	}
	
	@RequestMapping("/generateReport")
	public String generateReport() {
		List<Object[]> data = repo.findTypeAndCount();
		report.generatePieChart(sc.getRealPath("/"), data);
		return "report";
	}
}
