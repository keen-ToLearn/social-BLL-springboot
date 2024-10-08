package com.palamahen.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reelId;
	
	private String title;
	private String video;
	
	@ManyToOne
	private User reelBy;
	
}
