package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	public List<String> wishes;
//	@ManyToMany
//	public List<User> targets;


	public Participation(Band band, User participant) {
		this.band = band;
		this.participant = participant;
		wishes = new ArrayList<>();
//		targets = new ArrayList<>();
	}

	public static Finder<Long,Participation> find = new Finder<>(Long.class, Participation.class);

	public static List<Participation> findParticipations(Long userID) {
		return find.where()
				.eq("participant.id", userID)
				.findList();
	}

	public static Participation create(Band band, User participant) {
		return new Participation(band, participant);
	}

	public void addWish(String wish) {
		wishes.add(wish);
	}
/*
	public void addTarget(User target) {
		targets.add(target);
	}
*/
}
