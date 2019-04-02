package com.sindhu.springboot.model;

import java.util.List;

public class Question {

	private String id;
	private String description;
	private String correctANswer;
	private List<String> options;
	
	//Needed by Caused by: com.fasterxml.jackson.databind.jsonMappingException:
	//Cannot construct instance of com.sindhu.springboot.model.Question:
	//no suitable constructor found, can not deserialize from object value
	//(missing default constructor or creator, or perhaps need to add/enable type information 
	public Question() {
		
	}
	
	public Question(String id, String description, String correctANswer, List<String> options) {
		super();
		this.id = id;
		this.description = description;
		this.correctANswer = correctANswer;
		this.options = options;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCorrectANswer() {
		return correctANswer;
	}

	public void setCorrectANswer(String correctANswer) {
		this.correctANswer = correctANswer;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", description=" + description + ", correctANswer=" + correctANswer + ", options="
				+ options + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
