package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class User extends Model {

	@Id
	public Long id;
	public String email;
	public String name;
	public String password;
//	@ManyToMany(mappedBy = "members")
//	public List<Band> bands;

	// TODO: salasanan hastagaaminen
	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public static Finder<Long,User> find = new Finder<>(Long.class, User.class);

	public static User create(String email, String name, String password) {
		User user = new User(email, name, password);
		user.save();
		return user;
	}

	public String toString() {
		List<Band> bands = Band.findBands(id);
		StringBuilder builder = new StringBuilder();

		if (bands.size() > 0) {
			builder.append("[ ");
			for (Band band : Band.findBands(id))
				builder.append(band.name+", ");
			builder.deleteCharAt(builder.length()-2);
			builder.append("]");
		}

		return "User "+id+": "+name+"\t"+builder;
	}
}
