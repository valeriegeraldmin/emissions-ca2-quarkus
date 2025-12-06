package ie.valerie.ca2.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class User extends PanacheEntity {
	
	@Column(length = 50, unique = true, nullable=false)
    public String username;
	
	 @Column(nullable = false)
	    public String password;
	 
	 @Column(length = 20)
	    public String role;

    public User() {
    }

    public User(String username, String password, String role) {
    	 this.username = username;
         this.password = password;
         this.role = role;
    }
}


