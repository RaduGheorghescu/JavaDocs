package ro.teamnet.zth.api.database;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;
import ro.teamnet.zth.dao.LocationDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Radu.Gheorghescu on 7/14/2017.
 */
public class LocationDaoTest {
    LocationDao locationDao = new LocationDao();

    @Test
    public void testFindByIdTest(){
        System.out.println(locationDao.findById((long) 2700).getCity());
    }

    @Test
    public void getNextIdValTest(){
        System.out.println(locationDao.getNextIdVal());
    }

    @Test
    public void insertTest(){
        Location location = new Location();
        location.setCity("Japolanda");
        location.setPostalCode("31231");
        location.setStateProvince("Japoopaia");
        location.setStreetAddress("JDSA DSA  DA DA DA DA DWEWQ EQ");
        locationDao.insert(location);
    }

    @Test
    public void findAllTest() {
        List<Location> locations = locationDao.findAll();
        System.out.println((long) locations.size());
        System.out.println(locations.stream().toString());
    }

    @Test
    public void testUpdate() {
        Location location = locationDao.findById((long) 1700);
        location.setCity("Laurentiana");
        locationDao.update(location);
    }

    @Test
    public void testDelete() {
        Location location = locationDao.findById((long) 2100);
        locationDao.delete(location);
    }

    @Test
    public void findByParamTest() {
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("city", "Laurentiana");
        List<Location> locations = locationDao.findByParams(stringObjectMap);
        System.out.println(locations.size());
    }


}
