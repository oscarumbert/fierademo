package com.fiera.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiera.demo.model.Tracker;

public interface TrackerRepository extends JpaRepository<Tracker,String>{

}
