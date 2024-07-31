import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/")
public class RestController {

    @POST
    @Path("find")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response testEndpoint() {
        final String text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        TestDTO testDTO = new TestDTO();
        for (int i=0; i<100; i++) {
            testDTO.setText(testDTO.getText()+text);
            testDTO.setMoreText(testDTO.getMoreText()+text);
            testDTO.setEvenMoreText(testDTO.getEvenMoreText()+text);
        }
        //Simulate getting a chunked response from external microservice
        Response response = Response.ok().entity(testDTO).header("Transfer-Encoding", "chunked").build();
        //Forwarding the response
        return Response.fromResponse(response).build();
    }

}
