package edu.upc.dsa.MyBikeManagerTest;

import edu.upc.dsa.MyBikeManager;
import edu.upc.dsa.MyBikeManagerImpl;
import edu.upc.dsa.models.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public class MyBikeManagerTestScenario1 {
    private MyBikeManagerImpl bm;

    @Before
    public void initialize(){
        bm = MyBikeManagerImpl.getInstance();
    }

    @After
    public void tearDown(){
        bm = null;
    }

    @Test
    public void addUserTest() throws ExistantUserException {
        bm.addUser("carlo","Carlo");
        Assert.assertEquals(1,bm.userSize());
    }

    @Test
    public void addStationTest() throws ExistantStationException {
        bm.addStation("castelldefelsStation","Castelldefels");
        Assert.assertEquals(1,bm.stationSize());
    }

    @Test
    public void getBikeTest() throws StationNotFoundException, EmptyStationException, UserNotFoundException, BikeNotFoundException{
        bm.getBike("orbea","carlo","castelldefelsStation");
        Assert.assertEquals(2,bm.getStation("castelldefelsStation").getBikeList().size());
        Assert.assertEquals(1,bm.getUser("carlo").getBikeList().size());
    }

    @Test
    public void addBikeTest() throws StationNotFoundException, UserNotFoundException, BikeNotFoundException, WrongStationException{
        bm.addBike("orbea","castelldefelsStation","carlo",20);
        Assert.assertEquals(3,bm.getStation("castelldefelsStation").getBikeList().size());
    }

    @Test
    public void getBikesByUser() throws EmptyUserException, UserNotFoundException, StationNotFoundException, EmptyStationException, BikeNotFoundException{
        bm.getBike("bh","carlo","castelldefelsStation");
        bm.getBike("orbea","carlo","castelldefelsStation");
        Assert.assertEquals(2,bm.bikesByUser("carlo").size());
    }
    @Test
    public void getBikesByStationOrderedByKms() throws StationNotFoundException, EmptyStationException, UserNotFoundException, BikeNotFoundException, WrongStationException{
        bm.addBike("orbea","castelldefelsStation","carlo",5);
        bm.addBike("bh","castelldefelsStation","carlo",50);
        bm.getBike("scott","carlo","castelldefelsStation");
        bm.addBike("scott","castelldefelsStation","carlo",30);
        List<Bike> bikeList = bm.bikesByStationOrderByKms("castelldefelsStation");
        Assert.assertEquals("bh",bikeList.get(0).getId());
        Assert.assertEquals("scott",bikeList.get(1).getId());
        Assert.assertEquals("orbea",bikeList.get(2).getId());
    }

}
