package ie.valerie.ca2.resource;

import ie.valerie.ca2.entity.Emission;
import ie.valerie.ca2.repository.EmissionRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/emissions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmissionResource {

    @Inject
    EmissionRepository emissionRepository;

    // CREATE emission
    @POST
    @Transactional
    public Response create(Emission emission) {
        emissionRepository.persist(emission);
        return Response.status(Response.Status.CREATED).entity(emission).build();
    }

    // LIST all emissions
    @GET
    public Response listAll() {
        return Response.ok(emissionRepository.listAll()).build();
    }

    // GET by ID
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Emission emission = emissionRepository.findById(id);
        if (emission == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(emission).build();
    }

    // DELETE emission
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = emissionRepository.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    // APPROVE emission
    @POST
    @Path("/{id}/approve/{userId}")
    @Transactional
    public Response approve(@PathParam("id") Long id, @PathParam("userId") Long userId) {

        Emission emission = emissionRepository.findById(id);
        if (emission == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Emission not found")
                    .build();
        }

        emission.approved = true;
        emission.approvedBy = userId;

        return Response.ok("Emission approved").build();
    }
}
