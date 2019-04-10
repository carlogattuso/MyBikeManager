package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import javax.jws.soap.SOAPBinding;
import java.util.*;

public class MyBikeManagerImpl implements MyBikeManager{
    private static MyBikeManagerImpl instance = new MyBikeManagerImpl();

    final static Logger logger = Logger.getLogger(MyBikeManagerImpl.class);

    public static MyBikeManagerImpl getInstance() {
        return instance;
    }

    private List<Station> stations;
    private HashMap<String, User> userHashMap;

    private MyBikeManagerImpl() {
        this.stations = new ArrayList<>();
        this.userHashMap = new HashMap<>();
    }

    public int userSize(){
        return this.userHashMap.size();
    }

    public int stationSize(){
        return this.stations.size();
    }

    public User addUser(String id, String name) throws ExistantUserException{
        try{
            this.getUser(id);
        }
        catch(UserNotFoundException e){
            User u = new User(id,name);
            this.userHashMap.put(id,u);
            logger.info("New user: "+u.toString());
            logger.info("Users: "+userSize());
            return u;
        }
        throw new ExistantUserException("Existant user");
    }

    public Station addStation(String id, String place) throws ExistantStationException {
        try{
            this.getStation(id);
        }
        catch (StationNotFoundException e){
            Station s = new Station(id,place);
            this.stations.add(s);
            List<Bike> bikeList = new LinkedList<>();
            Bike b1 = new Bike("orbea","Orbea",0);
            b1.setStationId(s.getId());
            Bike b2 = new Bike("bh","BH",0);
            b2.setStationId(s.getId());
            Bike b3 = new Bike("scott","Scott",0);
            b3.setStationId(s.getId());
            s.addBike(b1);
            s.addBike(b2);
            s.addBike(b3);
            logger.info("New Station: "+s.toString());
            logger.info("Stations: "+stationSize());
            return s;
        }
        throw new ExistantStationException("Existant station");
    }

    public Bike addBike(String bikeId, String stationId, String userId, double kms) throws StationNotFoundException, UserNotFoundException, BikeNotFoundException, WrongStationException{
        User u = this.getUser(userId);
        Station s = this.getStation(stationId);
        Bike b = findBikeByUser(bikeId,userId);
        if(!b.getStationId().equals(stationId)){
            throw new WrongStationException("Wrong station");
        }
        u.removeBike(b);
        s.addBike(b);
        b.setKms(b.getKms()+kms);
        logger.info("New bike: "+b.toString());
        logger.info("Station: "+s.toString());
        logger.info("Bikes: "+s.getBikeList().size());
        return b;
    }

    public Station getStation(String stationId) throws StationNotFoundException {
        for (Station s : this.stations){
            if(s.getId().equals(stationId)){
                return s;
            }
        }
        throw new StationNotFoundException("Station not found");
    }

    public User getUser(String userId) throws UserNotFoundException {
        User u = this.userHashMap.get(userId);
        if(u==null) throw new UserNotFoundException("User not found");
        return u;
    }

    public Bike getBike(String bikeId, String userId, String stationId) throws StationNotFoundException, EmptyStationException, UserNotFoundException, BikeNotFoundException {
        Station s = this.getStation(stationId);
        if(s.getBikeList().size()==0){
            throw new EmptyStationException("No bikes available");
        }
        User u = this.getUser(userId);
        for(Bike b : s.getBikeList()){
            if(b.getId().equals(bikeId)){
                s.getBikeList().remove(b);
                u.getBikeList().add(b);
                logger.info("Get bike");
                logger.info("User: "+u.toString());
                logger.info("User bikes: "+u.getBikeList().size());
                logger.info("Station: "+s.toString());
                logger.info("Remaining bikes: "+s.getBikeList().size());
                return b;
            }
        }
        throw new BikeNotFoundException("Bike not found");
    }

    public List<Bike> bikesByUser(String userId) throws UserNotFoundException, EmptyUserException {
        List<Bike> bikes = this.getUser(userId).getBikeList();
        if(bikes.size()==0) throw new EmptyUserException("Empty bike list");
        return bikes;
    }

    public Bike findBikeByUser(String bikeId, String userId) throws UserNotFoundException, BikeNotFoundException{
        for(Bike b : this.getUser(userId).getBikeList()){
            if(b.getId().equals(bikeId)){
                return b;
            }
        }
        throw new BikeNotFoundException("Bike not found");
    }

    public List<Bike> bikesByStationOrderByKms(String stationId) throws StationNotFoundException, EmptyStationException{
        List<Bike> bikes = this.getStation(stationId).getBikeList();
        if(bikes.size()==0) throw new EmptyStationException("Empty bike list");
        Collections.sort(bikes, new Comparator<Bike>() {
            @Override
            public int compare(Bike one, Bike other) {
                return Double.compare(other.getKms(), one.getKms());
            }
        });
        return bikes;
    }
}
