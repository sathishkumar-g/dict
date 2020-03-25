package com.sat.heroes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HeroesController {
	@Autowired
	HeroesJdbcRepository repository;

	@RequestMapping(value = "/heroes")
	public ResponseEntity<Object> getHeroes() {
		List<Hero> heroes = null;
		heroes = repository.getHeroes();
		return new ResponseEntity<>(heroes, HttpStatus.OK);
	}

	@RequestMapping(value = "/hero")
	public ResponseEntity<Object> getHero(@RequestParam(value = "id") int id) {
		Hero hero = null;
		hero = repository.getHero(id);
		return new ResponseEntity<>(hero, HttpStatus.OK);
	}
}
