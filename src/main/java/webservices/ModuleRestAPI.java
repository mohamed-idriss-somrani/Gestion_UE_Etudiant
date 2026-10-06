package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/module")
public class ModuleRestAPI {

    static ModuleBusiness helper = new ModuleBusiness();
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListModules() {

        return Response.status(200)
                .entity(helper.getAllModules())
                .build();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addModule(Module module) {

        if (helper.addModule(module)) {

            return Response.status(201)
                    .entity("Module ajouté avec succès")
                    .build();

        } else {

            return Response.status(400)
                    .entity("Erreur : UE introuvable ou module invalide")
                    .build();
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{matricule}")
    public Response getModuleByMatricule(
            @PathParam("matricule") String matricule) {

        Module module = helper.getModuleByMatricule(matricule);

        if (module != null) {

            return Response.status(200)
                    .entity(module)
                    .build();

        } else {

            return Response.status(404)
                    .entity("Module introuvable")
                    .build();
        }
    }
    @GET
    @Path("/type")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByType(
            @QueryParam("type") Module.TypeModule type) {

        if (type == null) {

            return Response.status(400)
                    .entity("Type du module obligatoire")
                    .build();
        }

        return Response.status(200)
                .entity(helper.getModulesByType(type))
                .build();
    }
    @GET
    @Path("/ue")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByUE(
            @QueryParam("code") int code) {

        UniteEnseignement ue =
                helper.getUniteEnseignementBusiness()
                        .getUEByCode(code);

        if (ue == null) {

            return Response.status(404)
                    .entity("UE introuvable")
                    .build();
        }

        return Response.status(200)
                .entity(helper.getModulesByUE(ue))
                .build();
    }
    @PUT
    @Path("/{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateModule(
            @PathParam("matricule") String matricule,
            Module module) {

        if (helper.updateModule(matricule, module)) {

            return Response.status(200)
                    .entity("Module modifié avec succès")
                    .build();

        } else {

            return Response.status(404)
                    .entity("Module introuvable")
                    .build();
        }
    }
    @DELETE
    @Path("/{matricule}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteModule(
            @PathParam("matricule") String matricule) {

        if (helper.deleteModule(matricule)) {

            return Response.status(200)
                    .entity("Module supprimé avec succès")
                    .build();

        } else {

            return Response.status(404)
                    .entity("Module introuvable")
                    .build();
        }
    }
}