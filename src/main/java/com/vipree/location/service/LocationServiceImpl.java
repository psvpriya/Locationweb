package com.vipree.location.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vipree.location.entities.Location;
import com.vipree.location.repos.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationRepository repo;

	@Override
	public Location saveLocation(Location location) {
		return repo.save(location);
	}

	@Override
	public Location updateLocation(Location location) {
		return repo.save(location);
	}

	@Override
	public void deleteLocation(Location location) {
		repo.delete(location);
	}

	@Override
	public Location getLocationById(int id) {
		return repo.findById(id).orElse(null);
	}
	
	@Override
	public List<Location> getAllLocations() {
		return repo.findAll();
	}

}
