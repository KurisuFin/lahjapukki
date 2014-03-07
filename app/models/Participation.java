package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Participation extends Model {

	@Id
	public Long id;
	@ManyToOne
	public Band band;
	@ManyToOne
	public User participant;
	@OneToMany(mappedBy="participation", cascade=CascadeType.ALL)
	public List<Wish> wishes;


	public Participation(Band band, User participant) {
		this.band = band;
		this.participant = participant;
		wishes = new ArrayList<Wish>();
	}

	public static Finder<Long,Participation> find = new Finder<Long,Participation>(Long.class, Participation.class);

	public static List<Participation> findParticipations(Long userID) {
		return find.where()
				.eq("participant.id", userID)
				.findList();
	}

	public static Participation findParticipation(Long bandID, Long userID) {
		return find.where()
				.eq("band.id", bandID)
				.eq("participant.id", userID)
				.findUnique();
	}

	public static Participation create(Band band, User participant) {
		return new Participation(band, participant);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (wishes.size() > 0) {
			builder.append("[ ");
			for (Wish wish : wishes)     {
				builder.append(wish.id);
				builder.append(", ");
			}
			builder.deleteCharAt(builder.length()-2);
			builder.append("]");
		} else {
			builder.append("[]");
		}

		return  "Participation: "+id+
				"\n  - Band: \t\t"+band.id+" "+band.name+
				"\n  - Participant: \t"+participant.id+" "+participant.name+
				"\n  - Wishes ("+wishes.size()+"): \t"+builder;
	}
}
