package com.prograWeb3.demoCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prograWeb3.demoCrud.models.City;

public interface CityRepository extends JpaRepository<City, Integer>  {

}
