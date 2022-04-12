package com.vipree.location.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vipree.location.entities.Location;
import com.vipree.location.repos.LocationRepository;

@RestController
@RequestMapping("/locations")
public class LocationRESTController {
	@Autowired
	LocationRepository repo;
	
	@GetMapping
	public List<Location> getLocations(){
		List<Location> locations = repo.findAll();
		return locations;
	}
	
	@PostMapping
	public Location createLocation(@RequestBody Location loc) {
		return repo.save(loc);
	}

	@PutMapping
	public Location updateLocation(@RequestBody Location loc) {
		return repo.save(loc);
	}
	
	@DeleteMapping("/{id}")
	public void deleteLocation(@PathVariable("id") int id) {
		repo.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Location getLocation(@PathVariable("id") int id) {
		return repo.findById(id).orElse(null);
	}
	
}
