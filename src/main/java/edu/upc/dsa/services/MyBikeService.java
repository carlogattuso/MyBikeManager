package edu.upc.dsa.services;

import edu.upc.dsa.MyBikeManager;
import edu.upc.dsa.MyBikeManagerImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/bikes", description = "Endpoint to Orders Service")
@Path("/bikes")
public class MyBikeService {
    private MyBikeManager bm;

    public MyBikeService() throws ExistantUserException, ExistantStationException {
        this.bm = MyBikeManagerImpl.getInstance();
        if (bm.stationSize() == 0) {
            bm.addUser("carlo", "Carlo");
            bm.addStation("castelldefelsStation", "Castelldefels");
        }
    }

    @POST
    @ApiOperation(value = "create a new Station", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=StationTO.class),
            @ApiResponse(code = 500, message = "Existant Exception"),
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/stations/")
    public Response newStation(StationTO stationTO) {
        try {
            Station s = this.bm.addStation(stationTO.getId(),stationTO.getPlace());
            return Response.status(201).entity(stationTO).build();
        } catch (ExistantStationException e) {
            return Response.status(500).build();
        }
    }

    @POST
    @ApiOperation(value = "create a new User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=UserTO.class),
            @ApiResponse(code = 500, message = "Existant User"),
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/users/")
    public Response newUser(UserTO userTO) {
        try {
            User u = this.bm.addUser(userTO.getId(),userTO.getName());
            return Response.status(201).entity(userTO).build();
        } catch (ExistantUserException e) {
            return Response.status(500).build();
        }
    }

    @POST
    @ApiOperation(value = "return a bike", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=AddBikeTO.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 405, message = "Station not found"),
            @ApiResponse(code = 406, message = "Bike not found"),
            @ApiResponse(code = 500, message = "Wrong station")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/return/")
    public Response addBike(AddBikeTO addBikeTO) {
        try {
            Bike b = this.bm.addBike(addBikeTO.getBikeId(),addBikeTO.getStationId(),addBikeTO.getUserId(),addBikeTO.getKms());
            return Response.status(201).entity(addBikeTO).build();
        } catch (UserNotFoundException e1) {
            return Response.status(404).build();
        } catch (StationNotFoundException e2) {
            return Response.status(405).build();
        } catch (BikeNotFoundException e3) {
            return Response.status(406).build();
        } catch (WrongStationException e3){
            return Response.status(500).build();
        }
    }

    @POST
    @ApiOperation(value = "take a Bike", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class),
            @ApiResponse(code = 404, message = "Station not found"),
            @ApiResponse(code = 405, message = "User not found"),
            @ApiResponse(code = 406, message = "Bike not found"),
            @ApiResponse(code = 204, message = "No bikes available")
    })
    @Path("/take/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getBike(GetBikeTO request) {
        try {
            Bike b = this.bm.getBike(request.getBikeId(),request.getUserId(),request.getStationId());
            return Response.status(201).entity(b).build();
        }
        catch(StationNotFoundException e1) {
            return Response.status(404).build();
        }
        catch (EmptyStationException e2) {
            return Response.status(204).build();
        }
        catch(UserNotFoundException e3) {
            return Response.status(405).build();
        }
        catch(BikeNotFoundException e4) {
            return Response.status(406).build();
        }
    }

    @GET
    @ApiOperation(value = "get bikes by station ordered by Kms", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class),
            @ApiResponse(code = 204, message = "No bikes"),
            @ApiResponse(code = 404, message = "Station not found")
    })
    @Path("/kms")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBikesByStationOrderByKms(@PathParam("id") String id) {
        List<Bike> bikeList;
        try {
            bikeList = this.bm.bikesByStationOrderByKms(id);
        }
        catch (StationNotFoundException e1){
            return Response.status(404).build();
        }
        catch (EmptyStationException e2){
            return Response.status(204).build();
        }
        GenericEntity<List<Bike>> entity = new GenericEntity<List<Bike>>(bikeList) {
        };
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get bikes by User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class),
            @ApiResponse(code = 204, message = "No bikes"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBikesByUser(@PathParam("id") String id) {
        List<Bike> bikeList;
        try {
            bikeList = this.bm.bikesByUser(id);
        }
        catch (UserNotFoundException e1){
            return Response.status(404).build();
        }
        catch (EmptyUserException e2){
            return Response.status(204).build();
        }
        GenericEntity<List<Bike>> entity = new GenericEntity<List<Bike>>(bikeList) {
        };
        return Response.status(201).entity(entity).build();
    }
}
