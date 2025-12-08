package ie.valerie.ca2.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;


@Cacheable
@Entity
public class Emission extends PanacheEntity{
	
	@Column(length = 50, nullable=false)
    public String categoryCode;
	
	@Column(length = 255)
    public String categoryDescription;
	
	 @Column(nullable = false)
	    public double predictedValue;
	 
	 
	 @Column(nullable = false)
	    public double actualValue;
	 
	 @Column(nullable=false)
	    public int year;
	 
	 @Column(length = 10, nullable = false)
	    public String scenario;         // Must be "WEM"

	    public boolean approved = false;   // default false

	    public Long approvedBy;            // userId of approver (FK)


    public Emission() {
    }

    public Emission(String categoryCode, double predictedValue, double actualValue) {
    	 this.categoryCode = categoryCode;
         this.predictedValue = predictedValue;
         this.actualValue = actualValue;
         this.year = year;
         this.scenario = scenario;
    }
}



