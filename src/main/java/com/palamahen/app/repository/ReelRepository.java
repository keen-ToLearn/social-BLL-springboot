package com.palamahen.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.palamahen.app.model.Reel;

public interface ReelRepository extends JpaRepository<Reel, Integer> {

	public List<Reel> findByReelById(Integer userId);
}
