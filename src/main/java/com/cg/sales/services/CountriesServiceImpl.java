package com.cg.sales.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sales.entities.Country;
import com.cg.sales.exceptions.CountryNotFoundException;
import com.cg.sales.repositories.CountriesRepository;
import com.cg.sales.repositories.CustomerRepository;

@Service
public class CountriesServiceImpl implements CountriesService {
	
	private CountriesRepository countriesRepository;
	private CustomerRepository customerRepository;
	
	@Autowired
	public void setCountriesRepository(CountriesRepository countriesRepository) {
		this.countriesRepository = countriesRepository;
	}
	
	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Country saveCountries(Country country) {
		return countriesRepository.save(country);
	}

	@Override
	public List<Country> getAllCountries() {
		return countriesRepository.findAll();
	}

	@Override
	public Country updateCountry(Integer countryId,Country country) {
		Country countries = new Country(country.getCountryId(),country.getCountryIsoCode(),country.getCountryName(),country.getCountryRegion(),country.getCountryRegionId(),country.getCountrySubregion(),country.getCountrySubregionId(),country.getCountryTotal(),country.getCountryTotalId());
		return countriesRepository.save(countries);
	}

	@Override
	public Country getCountry(Integer countryId) {
		return countriesRepository.findById(countryId).orElseThrow(()->new CountryNotFoundException("Country with ID: "+countryId+",not available"));
	}

	@Override
	public void deleteCountry(Integer countryId) {
		Country country=getCountry(countryId);
		countriesRepository.deleteById(country.getCountryId());
		
	}

	@Override
	public Map<String, Integer> getCustomerCountByCountry() {
		List<Object[]> result = customerRepository.getCustomerCountByCountry();
		Map<String, Integer> customerCountMap = new HashMap<>();
		for(Object[] row : result) {
			String countryName = (String) row[0];
			Long customerCount = (Long) row[1];
			customerCountMap.put(countryName,customerCount.intValue());
		}
		return customerCountMap;
	}
}
