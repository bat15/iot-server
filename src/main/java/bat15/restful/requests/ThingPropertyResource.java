/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bat15.restful.requests;

import bat15.restful.process.ThingPropertyProcessor;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Павел
 */
@Path("models")
public class ThingPropertyResource {
    
    @Context
    private UriInfo context;

//    @EJB (beanName="Result")
//    Result proc;

    @EJB (beanName="ThingPropertyProcessor")
    ThingPropertyProcessor proc;    
    
    /**
     * Creates a new instance of RestResource
     */
    public ThingPropertyResource() {
    }

    /**
     * Retrieves representation of an instance of ru.beeline.hadoop.webtechccboonline.ws.RestResource
     * @param ctn
     * @param fromtime
     * @param totime
     * @return an instance of ru.beeline.hadoop.webtechccboonline.ws.WsObject
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTestRequest(@QueryParam("json") String jsonType) {
//        return new WsObject("examp.ru","/usrl/fhsj", (new Date()).getTime());
        

        System.out.println("json: "+jsonType);
        
        System.out.println("test");
        
        int type = 0;
        
        if(jsonType!=null) 
            if(jsonType.equals("short")) type = 1;
            else if(jsonType.equals("full")) type = 0;
        
        return proc.getPropertyValue(type);
    }
    
    
    @GET
    @Path("{path:.*}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getModelRequest(@PathParam("path") String modelQuery) {
    
        return proc.getModel(modelQuery);
    }
  
  
//    
//    @GET
//    @Path("model_name/{uuid}")
//    public String retrieveSomething(@PathParam("uuid") String uuid) {
//        if(uuid == null || uuid.trim().length() == 0) {
//            //return Response.serverError().entity("UUID cannot be blank").build();
//            return "no such model or property";
//        }
//        
//        return uuid;
//        //return Response.ok(uuid, MediaType.APPLICATION_JSON).build();
//    }    
    
    

    /**
     * PUT method for updating or creating an instance of RestResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String data) {
        proc.putPropertyValue(data);
    }      
    
    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDataInJSON(String data) { 

        String result = "Data post: "+data;

        return Response.status(201).entity(result).build(); 
    }    
}

