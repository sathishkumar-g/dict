package com.sat.dict;

public class Dict {
private int id;
private String word;
private String meaning;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getWord() {
	return word;
}
public void setWord(String word) {
	this.word = word;
}
public String getMeaning() {
	return meaning;
}
public void setMeaning(String meaning) {
	this.meaning = meaning;
}
public Dict(int id, String word, String meaning) {
	super();
	this.id = id;
	this.word = word;
	this.meaning = meaning;
}
public Dict() {
	super();
}
@Override
public String toString() {
	return "Dict [id=" + id + ", word=" + word + ", meaning=" + meaning + "]";
}

}
