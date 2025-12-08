package ie.valerie.ca2.resource;

import ie.valerie.ca2.entity.User;
import ie.valerie.ca2.repository.UserRepository;
import ie.valerie.ca2.security.PasswordService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class UserResource {

    @Inject
    UserRepository userRepository;
    
 //REGISTER
    @POST
    @Path("/register")
    @Transactional
    public Response register(User user) {

        // Username already exists?
        if (userRepository.findByUsername(user.username) != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Username already exists")
                    .build();
        }

        // Hash password before storing
        user.password = PasswordService.hash(user.password);

        // Default role
        if (user.role == null) {
            user.role = "user";
        }

        userRepository.persist(user);
        return Response.ok("User registered").build();
    }


    //LOGIN
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User userRequest) {

        User user = userRepository.findByUsername(userRequest.username);

        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid username")
                    .build();
        }

        boolean correct = PasswordService.verify(userRequest.password, user.password);

        if (!correct) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid password")
                    .build();
        }

        return Response.ok("Login successful").build();
    }
}

