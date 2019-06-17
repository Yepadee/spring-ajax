package uk.james_hawkins.model.user;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedNativeQuery;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="perms", discriminatorType=DiscriminatorType.STRING)
@NamedNativeQuery(name="User.changePerms", query="UPDATE USER SET PERMS = ?, " + "VERSION = VERSION + 1 WHERE ID = ?")
public abstract class User {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	private String userName;
	private String userPassword;
	
	public User() {}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String toString() {
		ObjectMapper objectMapper = new ObjectMapper();
		String str = super.toString();
		try {
			str = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return str;
	}
}
