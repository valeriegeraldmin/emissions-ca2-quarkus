package ie.valerie.ca2.resource;

import ie.valerie.ca2.service.EmissionImportService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/xml")
public class XMLImportResource {

	 @Inject
	    EmissionImportService service;

	    @GET
	    @Path("/import")
	    public Response importXml() {

	    	String filePath = getClass()
	                .getClassLoader()
	                .getResource("data/projections.xml")
	                .getFile();
	    	
	    	System.out.println("FILE PATH = " + filePath);

	        service.importFromXML(filePath);

	        return Response.ok("XML imported successfully").build();
	    }
	}