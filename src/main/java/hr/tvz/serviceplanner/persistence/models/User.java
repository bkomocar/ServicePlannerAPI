package hr.tvz.serviceplanner.persistence.models;

import java.util.Date;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

	@Entity
	@Table(name = "users")
	public class User implements Serializable{

	  // The entity fields (private)  
	  
	private static final long serialVersionUID = 121234535L;

	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long id;
	  
	  @NotNull
	  @Column(columnDefinition = "varchar(255)")
	  private String email;
	  
	  @NotNull
	  @Column(columnDefinition = "varchar(20)", unique=true)
	  private String name;

	  @NotNull
	  @Column(columnDefinition = "varchar(255)")
	  private String password;

	  @Column
	  private Date lastPasswordReset;
	  
	  @Column
	  private String authorities;
	  
	  // Public methods
	  public User() { }

	  public User(long id) { 
	    this.id = id;
	  }

	  public User(String email, String username, String password) {
	    this.email = email;
	    this.name = username;
	    this.password = password;
	    this.lastPasswordReset = new Date();
	    this.authorities = "USER";
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

	public String getUsername() {
		return name;
	}

	public void setUsername(String username) {
		this.name = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastPasswordReset() {
		return lastPasswordReset;
	}

	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
		  
	}
