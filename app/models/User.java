package models;

import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User extends Model {

	@Id
	public Long id;
	public String email;
	public String name;
	public String password;
	@OneToMany(mappedBy="participant", cascade=CascadeType.ALL) // I don't know is cascade needed
	public List<Participation> participations;

	// TODO: salasanan hastagaaminen
	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public static Finder<Long, User> find = new Finder<>(Long.class, User.class);

	public static User create(String email, String name, String password) {
		User user = new User(email, name, password);
		user.save();
		return user;
	}

//	public void add(Participation participation) {
//		participations.add(participation);
//	}

	@Override
	public String toString() {
//		List<Participation> participations = Participation.findParticipations(id);
		StringBuilder builder = new StringBuilder();

		if (participations.size() > 0) {
			builder.append("[ ");
			for (Participation participation : participations) {
				builder.append(participation.band.name);
				builder.append(", ");
			}
			builder.deleteCharAt(builder.length()-2);
			builder.append("]");
		} else {
			builder.append("[]");
		}

		return "User "+id+": "+name+"\t"+builder;
	}
}
