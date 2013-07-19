package models;

import javax.persistence.*;

@Entity
public class Wish {

	@Id
	public Long id;
	public String description;

	public Wish(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Wish "+id+": "+ description;
	}
}
