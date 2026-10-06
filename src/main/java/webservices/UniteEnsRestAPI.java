package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@Path("/ue")
public class UniteEnsRestAPI {
    static UniteEnseignementBusiness helper=new UniteEnseignementBusiness();//pour recuperer les methodes et les donnees et ajouter les donnes statefull
//web service to get the list of UE
    //getlistUEs
    @Path("/list")//path pour tester avec "/api/ue/list"
    @GET//pour le methode Get de recuperer les donnees
    @Produces(MediaType.APPLICATION_JSON)//pour transformer en JSON
    public Response getListUe(){

        return Response.status(200)
                .entity(helper.getListeUE())
                .build();
    }
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)//input
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUniteEnseignement(UniteEnseignement ue){
        if(helper.addUniteEnseignement(ue)){
            //201 Created
            return Response.status(201).entity("Scuccess").build();
        }
        //to be updated
        else {
            // 400 Bad request
            return Response.status(400).entity("Erreur").build();
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{code}")
public Response getUEByCode(@PathParam("code")int code)
{
    return Response.status(200).entity(helper.getUEByCode(code)).build();
}
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response getUEBySemestre(@QueryParam("semestre") int semestre)
    {
        return Response.status(200).entity(helper.getUEByCode(semestre)).build();
    }
    @DELETE
    @Path("/{code}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUE(@PathParam("code") int code) {

        if (helper.deleteUniteEnseignement(code)) {

            return Response.status(200)
                    .entity("UE supprimée avec succès")
                    .build();

        } else {
            return Response.status(404)
                    .entity("UE introuvable")
                    .build();
        }
    }
    @PUT
    @Path("/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUE(
            @PathParam("code") int code,
            UniteEnseignement ue) {
        if (helper.updateUniteEnseignement(code, ue)) {

            return Response.status(200)
                    .entity("UE modifiée avec succès")
                    .build();

        } else {

            return Response.status(404)
                    .entity("UE introuvable")
                    .build();
        }
    }
}


