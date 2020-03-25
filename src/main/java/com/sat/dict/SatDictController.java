package com.sat.dict;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST,
		RequestMethod.DELETE })
public class SatDictController {
	@Autowired
	DictJdbcRepository repository;

	@RequestMapping(value = "/dict/find")
	public ResponseEntity<Object> getVocabulary(@RequestParam(value = "word") String word) {
		Dict dict = null;
		String result = null;
		dict = repository.findByWord(word);
		/*
		 * if (null != dict) { result =
		 * word.concat(" -- ").concat(repository.findByWord(word).getMeaning()); } else
		 * { result = "No Result"; }
		 */
		return new ResponseEntity<>(dict, HttpStatus.OK);
	}

	@RequestMapping(value = "/dict/search")
	public ResponseEntity<Object> searchVocabulary(@RequestParam(value = "word") String word) {
		List<Dict> dictList = null;
		String result = null;
		System.out.println("inside search --> " + word);
		dictList = repository.searchByWord(word);
		/*
		 * if (null != dict) { result =
		 * word.concat(" -- ").concat(repository.findByWord(word).getMeaning()); } else
		 * { result = "No Result"; }
		 */
		return new ResponseEntity<>(dictList, HttpStatus.OK);
	}

	@PostMapping(value = "/dict/add")
	public ResponseEntity<Object> addVocabulary(@RequestBody Dict dict) {
		int key = 0;
		key = repository.addWord(dict);
		if (key == 1) {
			getVocabulary(dict.getWord());
			return new ResponseEntity<>(dict, HttpStatus.OK);
		}
		return new ResponseEntity<>(key, HttpStatus.OK);
	}

	@PutMapping(value = "/dict/save")
	public ResponseEntity<Object> updateVocabulary(@RequestBody Dict dict) {
		int key = 0;
		key = repository.updateWord(dict);
		if (key == 1) {
			getVocabulary(dict.getWord());
			return new ResponseEntity<>(dict, HttpStatus.OK);
		}
		return new ResponseEntity<>(key, HttpStatus.OK);
	}

	@DeleteMapping(value = "/dict/delete")
	public ResponseEntity<Object> deleteVocabulary(@RequestParam(value = "id") String id) {
		int key = 0;
		key = repository.deleteWord(id);
		return new ResponseEntity<>(key, HttpStatus.OK);
	}

	@RequestMapping(value = "/dict/get", method = RequestMethod.GET)
	public ResponseEntity<Object> getVocabulary() {
		List<Dict> dictList = null;
		dictList = repository.getVocabulary();
		return new ResponseEntity<>(dictList, HttpStatus.OK);
	}
}
