package com.sat.heroes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HeroesJdbcRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Hero> getHeroes() {
		try {
			return jdbcTemplate.query("select * from sat_heroes", new BeanPropertyRowMapper<Hero>(Hero.class));

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Hero getHero(int id) {
		try {
			return jdbcTemplate.queryForObject("select * from sat_heroes where id=?", new Object[] { id },
					new BeanPropertyRowMapper<Hero>(Hero.class));

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
