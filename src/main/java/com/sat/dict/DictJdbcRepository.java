package com.sat.dict;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class DictJdbcRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	String INSERT_MESSAGE_SQL = "insert into sat_dict (word,meaning) values(?,?) ";
	String UPDATE_MESSAGE_SQL = "update sat_dict set meaning = ? where word = ? ";
	String SELECT_MESSAGE_SQL = "select * from sat_dict";
	String DELETE_MESSAGE_SQL = "delete from sat_dict where id = ? ";
	String FIND_MESSAGE_SQL = "select * from sat_dict where word=?";
	String SEARCH_MESSAGE_SQL = "select * from sat_dict where word LIKE ?";

	public Dict findByWord(String word) {
		try {
			return jdbcTemplate.queryForObject(FIND_MESSAGE_SQL, new Object[] { word },
					new BeanPropertyRowMapper<Dict>(Dict.class));

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Dict> searchByWord(String word) {
		try {
			return jdbcTemplate.query(SEARCH_MESSAGE_SQL, new Object[] { "%" + word + "%" },
					new BeanPropertyRowMapper<Dict>(Dict.class));

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public int addWord(Dict dictDTO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		return jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_MESSAGE_SQL);
			ps.setString(1, dictDTO.getWord());
			ps.setString(2, dictDTO.getMeaning());
			return ps;
		}, keyHolder);

		/*
		 * return jdbcTemplate.update("insert into sat_dict (id, word, meaning) " +
		 * "values(?,  ?, ?)", new Object[] { dictDTO.getId(), dictDTO.getWord(),
		 * dictDTO.getMeaning() });
		 */
		// return (int)keyHolder.getKey();
	}

	public int updateWord(Dict dictDTO) {
		return jdbcTemplate.update(UPDATE_MESSAGE_SQL, dictDTO.getMeaning(), dictDTO.getWord());
	}

	public int deleteWord(String id) {
		return jdbcTemplate.update(DELETE_MESSAGE_SQL, id);
	}

	public List<Dict> getVocabulary() {
		try {
			return jdbcTemplate.query(SELECT_MESSAGE_SQL, new BeanPropertyRowMapper<Dict>(Dict.class));

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
