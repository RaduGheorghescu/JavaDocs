package ro.teamnet.zth.api.database;

import org.junit.Test;
import ro.teamnet.zth.dao.EmployeeDao;

/**
 * Created by Radu.Gheorghescu on 7/14/2017.
 */
public class EmployeeDaoTest {

    @Test
    public void findByDepartmentTest(){
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.findByDepartment("ion").forEach((employee -> System.out.println(employee.getFirstName())));
    }
}
