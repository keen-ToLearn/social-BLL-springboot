package com.palamahen.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palamahen.app.model.Reel;
import com.palamahen.app.model.User;
import com.palamahen.app.repository.ReelRepository;
import com.palamahen.app.repository.UserRepository;

@Service
public class ReelServiceImpl implements ReelService {
	
	@Autowired
	private ReelRepository rrepo;
	
	@Autowired
	private UserRepository urepo;

	@Override
	public Reel createReel(Reel reel, Integer userId) {
		
		User reelBy = urepo.findById(userId).orElse(null);
		
		Reel newReel = new Reel();
		newReel.setTitle(reel.getTitle());
		newReel.setVideo(reel.getVideo());
		newReel.setReelBy(reelBy);
		
		return rrepo.save(newReel);
	}

	@Override
	public List<Reel> findAllReels() {
		
		return rrepo.findAll();
	}

	@Override
	public List<Reel> findReelsByUser(Integer userId) {
		
		Optional<User> userWithReels = urepo.findById(userId);
		
		if(userWithReels.isPresent()) {
			List<Reel> reelsByUser = rrepo.findByReelById(userId);
			return reelsByUser;
		}

		return null;
	}

}
