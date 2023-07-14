package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Player;
import com.example.demo.repository.GamersRepository;

@RestController
public class PlayerController {
	
	@Autowired
	private GamersRepository repo;
	
	
	//add player
	@PostMapping("/save")
	public Player addNewPlayer(@RequestBody Player pl) {
		return repo.save(pl);
	}
	
	// add multiple player details
	@PostMapping("/saveAll")
	public List<Player> addMultiplePlayers(@RequestBody List<Player>pl){
		
		return repo.saveAll(pl);
		
	}
	
	// find player using id
	@GetMapping("/findById/{id}")
	public Player findUsingId(@PathVariable int id ) {
		return repo.findById(id).orElse(null);
	}
	// find all player details
	
	@GetMapping("/findAll")
	public List<Player>findAll(){
		return repo.findAll();
	}
	//update player details
	
	public Player updatePlayer(@PathVariable Player pl) {
		Player oldPlayer=repo.findById(pl.getId()).orElse(null);
		oldPlayer.setName(pl.getName());
		oldPlayer.setEmail(pl.getEmail());
		oldPlayer.setAge(pl.getAge());
		
		return repo.save(oldPlayer);
	}
	//delete player
	
	@DeleteMapping("/deleteById/{id}")
	public String deleteUsingId(@PathVariable int id) {
		
		repo.deleteById(id);
		return "Successfully Deleted";
	
	}
	

}
