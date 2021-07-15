package controller;

import controller.dao.FolderDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/folder")
public class FolderResource {
    S3Storage s3Storage = new S3Storage();
    FolderDAO folders = new FolderDAO();

    public FolderResource() throws IOException {
    }

    @GET
    @Path("/download/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getDownloadUrl(@PathParam("key") String key) {
        var url = s3Storage.getGetPresignedUrl(key);
        return Response.ok(url.toString()).build();
    }

    @GET
    @Path("/startUpload/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUploadUrl(@PathParam("key") String key) {
        var existingFolder = folders.getFolder(key);
        if (existingFolder != null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            var url = s3Storage.getPutPresignedUrl(key);
            return Response.ok(url.toString()).build();
        }
    }

    @GET
    @Path("/finishUpload/{key}")
    public Response finishUpload(@PathParam("key") String key) {
        var existingFolder = folders.getFolder(key);
        if (existingFolder == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            existingFolder.setFinished(true);
            folders.persistEntity(existingFolder);
            return Response.ok().build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFolders() {
        return Response.ok(folders.readFolders()).build();
    }
}
