package ie.valerie.ca2;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;


@Path("/hello")
public class GreetingResource {

	 @Inject
	    GreetingService service;
	 
	 @GET
	    @Produces(MediaType.TEXT_PLAIN)
	    @Path("/greeting/{name}")
	    public String greeting(String name) {
	        return service.greeting(name);
	    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }
}

//It’s a very simple REST endpoint, 
//returning "Hello from Quarkus REST" to requests on "/hello".