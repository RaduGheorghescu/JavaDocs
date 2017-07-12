package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.domain.Job;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Radu.Gheorghescu on 7/12/2017.
 */
public class EntityUnitsTest {
    @Test
    public void testGetTableNameMethod() throws NoSuchFieldException {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "departments", tableName);
    }

    @Test
    public void testGetTableNameMethod2() throws NoSuchFieldException {
        String tableName = EntityUtils.getTableName(Employee.class);
        assertEquals("Table name should be employees!", "employees", tableName);
    }

}
