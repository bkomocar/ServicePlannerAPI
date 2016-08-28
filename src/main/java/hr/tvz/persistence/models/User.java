package hr.tvz.persistence.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

	@Entity
	@Table(name = "users")
	public class User implements Serializable{

	  // The entity fields (private)  
	  
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long id;
	  
	  @NotNull
	  @Column(columnDefinition = "varchar(255)")
	  private String email;
	  
	  @NotNull
	  @Column(columnDefinition = "varchar(20)")
	  private String name;

	  @NotNull
	  @Column(columnDefinition = "varchar(30)")
	  private String password;

	  // Public methods
	  
	  public User() { }

	  public User(long id) { 
	    this.id = id;
	  }

	  public User(String email, String name, String password) {
	    this.email = email;
	    this.name = name;
	    this.password = password;
	  }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		  
	}
