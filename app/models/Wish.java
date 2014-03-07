package models;

import play.db.ebean.Model;

import javax.persistence.*;

@Entity
public class Wish extends Model {

	@Id
	public Long id;
	@ManyToOne
	public Participation participation;
	public String description;

	public Wish() {
	}

	public Wish(Participation participation, String description) {
		this.participation = participation;
		this.description = description;
	}

	public static Wish create(Participation participation, String description) {
		Wish wish = new Wish(participation, description);
		wish.save();

		return wish;
	}

	public static Wish create(Wish wish, Participation participation, String description) {
		wish.participation = participation;
		wish.description = description;
		wish.save();
		return wish;
	}

	public static Finder<Long, Wish> find = new Finder<Long, Wish>(Long.class, Wish.class);

	public static boolean isOwner(Long wishID, Long userID) {
		return find.where()
				.eq("id", wishID)
				.eq("participation.participant.id", userID)
				.findRowCount() > 0;
	}

	@Override
	public String toString() {
		return id+": "+description+
				"\n  - Participation "+participation.id+": "+participation.participant.name+" "+participation.band.name;
	}
}
