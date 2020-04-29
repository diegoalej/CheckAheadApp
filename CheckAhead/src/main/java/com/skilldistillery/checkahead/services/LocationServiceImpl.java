package com.skilldistillery.checkahead.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.checkahead.entities.Location;
import com.skilldistillery.checkahead.entities.User;
import com.skilldistillery.checkahead.repositories.LocationRepository;
import com.skilldistillery.checkahead.repositories.UserRepository;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	LocationRepository locationRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<Location> findAllLocations() {
		return locationRepo.findAll();
	}
	
	@Override
	public Location findLocationById(int id) {
		Optional<Location> location = locationRepo.findById(id);
		if(location.isPresent()) {
			return location.get();
		}
		else {
			return null;
		}
	}
	
//	@Override
//	public List<Location> findLocationByName(String name) {
//		List<Location> locations = locationRepo.findByName(name);
//		if(locations.size() > 0) {
//			return locations;
//		}
//		else {
//			return null;
//		}
//	}
	
//	@Override
//	public List<Location> findLocationByAddressId(int id) {
//		List<Location> locations = locationRepo.findByAddressId(id);
//		if(locations.size() > 0) {
//			return locations;
//		}
//		else {
//			return null;
//		}
//	}
	
	@Override
	public Location updateLocation(int id, Location location) {
		Optional<Location> oldLocation = locationRepo.findById(id);
		Location managedLocation = null;
		if (oldLocation.isPresent()) {
			managedLocation = oldLocation.get();
			managedLocation.setId(id);
			managedLocation.setName(location.getName());
			managedLocation.setDescription(location.getDescription());
			managedLocation.setDateUpdated(location.getDateUpdated());//LocalDateTime.now()
			managedLocation.setAddress(location.getAddress());
			return locationRepo.saveAndFlush(managedLocation);			
		}
		return null;
	}
	
	@Override
	public Location createLocation(int userId, Location location) {
		Optional<User> creator = userRepo.findById(userId);
		location.setCreator(creator.get());
		Location newLocation = locationRepo.saveAndFlush(location);
		if (newLocation != null) {
			return newLocation;
		}
			return null;			
	}
	
	@Override
	public boolean deleteLocation(int id) {
		boolean answer = false;
		Optional<Location> location = locationRepo.findById(id);
		if (location.isPresent()) {
			locationRepo.deleteById(id);
			answer = true;
		}
		
		return answer;
	}

}
