package com.palamahen.app.service;

import java.util.List;

import com.palamahen.app.model.Reel;

public interface ReelService {

	public Reel createReel(Reel reel, Integer userId);
	
	public List<Reel> findAllReels();
	
	public List<Reel> findReelsByUser(Integer userId);
}
