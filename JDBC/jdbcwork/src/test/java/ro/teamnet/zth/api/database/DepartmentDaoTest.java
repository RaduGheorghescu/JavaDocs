package ro.teamnet.zth.api.database;

import org.junit.Test;
import ro.teamnet.zth.api.em.EntityManagerImp;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.autowired.MyAutowired;
import ro.teamnet.zth.dao.DepartmentDao;

import javax.xml.bind.annotation.XmlType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Radu.Gheorghescu on 7/14/2017.
 */
public class DepartmentDaoTest {
    DepartmentDao departmentDao = new DepartmentDao();

    @Test
    public void testFindByIdTest(){
        System.out.println(departmentDao.findById((long) 90).getDepartmentName());
    }

    @Test
    public void getNextIdValTest(){
        System.out.println(departmentDao.getNextIdVal());
    }

    @Test
    public void insertTest(){
        Department department = new Department();
        department.setDepartmentName("Test");
        department.setLocation((long) 1700);
        departmentDao.insert(department);
    }

    @Test
    public void findAllTest() {
        List<Department> departments = departmentDao.findAll();
        System.out.println((long) departments.size());
        System.out.println(departments.stream().toString());
    }

    @Test
    public void testUpdate() {
        Department department = departmentDao.findById((long) 271);
        department.setDepartmentName("Test2");
        departmentDao.update(department);
    }

    @Test
    public void testDelete() {
        Department department = departmentDao.findById((long) 293);
        departmentDao.delete(department);
    }

    @Test
    public void findByParamTest() {
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("department_name", "Test");
        List<Department> departments = departmentDao.findByParams(stringObjectMap);
        System.out.println(departments.size());
    }
}
