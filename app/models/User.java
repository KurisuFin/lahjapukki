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
	private User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);

	public static User create(String email, String name, String password) {
		User user = new User(email, name, password);
		user.save();
		return user;
	}

	public static User authenticate(String email, String password) {
		return find.where()
				.eq("email", email)
				.eq("password", password)
				.findUnique();
	}

	public static Long findID(String email) {
		return find.where()
				.eq("email", email)
				.findUnique()
				.id;
	}



	@Override
	public String toString() {
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

		return id+": "+name+"\t"+builder;
	}

}
