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

	        String filePath = "data/projections.xml";

	        service.importFromXML(filePath);

	        return Response.ok("XML imported successfully").build();
	    }
	}