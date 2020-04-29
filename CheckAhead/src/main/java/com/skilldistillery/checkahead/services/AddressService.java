package com.skilldistillery.checkahead.services;

import java.util.List;

import com.skilldistillery.checkahead.entities.Address;

public interface AddressService {

	List<Address> findAllAddresses();
	
	Address findAddressById(Integer addressId);
	
	Address createAddress(Address address);

	Address updateAddress(Integer addressId, Address address);
	
	boolean deleteAddress(Integer addressId);
	
	
	
}
