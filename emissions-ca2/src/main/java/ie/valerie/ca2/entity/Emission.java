package ie.valerie.ca2.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

public class Emission extends PanacheEntity{
	
	@Column(length = 50, unique = true, nullable=false)
    public String catergoryCode;
	
	 @Column(nullable = false)
	    public String predictedValues;
	 
	 @Column(nullable = false)
	    public String actualValues;
	 
	 @Column(nullable=false)
	    public int year;;

    public Emission() {
    }

    public Emission(String catergoryCode, String predictedValues, String estimatedValues) {
    	 this.catergoryCode = catergoryCode;
         this.predictedValues = predictedValues;
         this.actualValues = actualValues;
    }
}



