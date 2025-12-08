package ie.valerie.ca2.repository;

import ie.valerie.ca2.entity.Emission;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class EmissionRepository implements PanacheRepository<Emission> {
	
	// Find by category code
    public Emission findByCategoryCode(String code) {
        return find("categoryCode", code).firstResult();
    }


}
