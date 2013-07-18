package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Band extends Model {

	@Id
	public Long id;
	public String name;
	public Date created;
	public Date dueDate;
	@ManyToOne
	public User operator;
	@OneToMany(mappedBy="band", cascade=CascadeType.ALL)
	public List<Participation> participations;

	private Band(String name, User operator) {
		this.name = name;
		this.created = new Date();
		this.operator = operator;

		participations = new ArrayList<>();
		participations.add(Participation.create(this, operator));
	}

	public static Finder<Long, Band> find = new Finder<>(Long.class, Band.class);

	public static Band create(String name, Long creatorID) {
		Band band = new Band(name, User.find.ref(creatorID));
		band.save();
		return band;
	}

	public void add(Long userID) {
		User user = User.find.ref(userID);
		Participation participation = Participation.create(this, user);
		participations.add(participation);
//		user.add(participation);                // Probably not needed
		participation.save();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("\n");

		for (Participation participation : participations) {
			User user = participation.participant;
			if (user == operator) {
				builder.append("  * ");
			} else {
				builder.append("  - ");
			}
			builder.append(user.name);
			builder.append("\n");
		}
		builder.deleteCharAt(builder.length() - 1);

		return id+": "+name+" ("+ participations.size()+")"+builder;
	}
}
