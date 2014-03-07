package models;

import controllers.Application;
import play.data.Form;
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
	public Date randomize;
	public Date reveal;
	@ManyToOne
	public User operator;
	@OneToMany(mappedBy="band", cascade=CascadeType.ALL)
	public List<Participation> participations;

	private Band(String name, User operator) {
		this.name = name;
		this.created = new Date();
		this.operator = operator;

		participations = new ArrayList<Participation>();
		participations.add(Participation.create(this, operator));
	}

	public static Finder<Long, Band> find = new Finder<Long, Band>(Long.class, Band.class);

	public static Band create(String name, Long creatorID) {
		Band band = new Band(name, User.find.ref(creatorID));
		band.save();
		return band;
	}

	public void add(Long userID) {
		User user = User.find.ref(userID);
		Participation participation = Participation.create(this, user);
		participations.add(participation);
		participation.save();
	}


	public List<Wish> getWishes(Long userID) {
		Participation participation = Participation.findParticipation(id, userID);
		if (participation != null)
			return participation.wishes;
		else
			return new ArrayList<Wish>();
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();

		for (Participation participation : participations) {
			users.add(participation.participant);
		}

		return users;
	}

	public boolean isMember() {
		for (Participation participation : participations)
			if (Application.getUserID().equals(participation.participant.id))
				return true;

		return false;
	}

	public boolean isOperator() {
		return Application.getUserID().equals(operator.id);
	}

	public Participation getParticipation(Long userID) {
		return Participation.findParticipation(id, userID);
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
