package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Band extends Model {

	@Id
	public Long id;
	public String name;
	@ManyToOne
	public User operator;
	@ManyToMany
	public List<User> members;

	public Band(String name, User operator) {
		this.name = name;
		this.operator = operator;
		members = new ArrayList<>();
		members.add(operator);
	}

	public static Finder<Long, Band> find = new Finder<>(Long.class, Band.class);

	public static List<Band> findBands(Long userID) {
		return find.where()
				.eq("members.id", userID)
				.findList();
	}

	public static Band create(String name, Long creatorID) {
		Band band = new Band(name, User.find.ref(creatorID));
		band.save();
//		band.saveManyToManyAssociations("members");     // onkohan tarpeellinen?
		return band;
	}

	public void add(Long userID) {
		members.add(User.find.ref(userID));
		this.save();
	}

	public static void add(Long bandID, Long userID) {
		Band.find.ref(bandID).add(userID);
	}

	public String toString() {
		StringBuilder builder = new StringBuilder("\n");

		for (User user : members)
			if (user == operator)
				builder.append("  * "+user.name+"\n");
			else
				builder.append("  - "+user.name+"\n");
		builder.deleteCharAt(builder.length() - 1);

		return id+": "+name+" ("+members.size()+")"+builder;
	}
}
