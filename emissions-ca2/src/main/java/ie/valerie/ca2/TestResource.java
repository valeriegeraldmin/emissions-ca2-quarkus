package ie.valerie.ca2;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;


@Path("/test")
public class TestResource {
	
	 @GET
	    public String test() {
	        return "My CA2 backend is running!";
	    }

}
