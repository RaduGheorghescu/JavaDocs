package ro.teamnet.zth.api.database;

import org.junit.Assert;
import org.junit.Test;
import ro.teamnet.zth.api.em.EntityManagerImp;
import ro.teamnet.zth.appl.domain.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Radu.Gheorghescu on 7/13/2017.
 */
public class DBManagerTest {
    @Test
    public void testGetConnection() throws SQLException, ClassNotFoundException {
        Connection connection = DBManager.getConnection();
        connection.close();
    }

    @Test
    public void testCheckConnection() throws SQLException, ClassNotFoundException {
        Connection connection = DBManager.getConnection();
        ResultSet resultSet = DBManager.checkConnection(connection);
        Assert.assertNotNull(resultSet);
    }

    @Test
    public void findByIdTest() {
        EntityManagerImp entityManagerImp = new EntityManagerImp();
        Department department = entityManagerImp.findById(Department.class, (long) 90);
        System.out.println(department.getDepartmentName());
    }

    @Test
    public void getNextIdValTest() {
        EntityManagerImp entityManagerImp = new EntityManagerImp();
        System.out.println(entityManagerImp.getNextIdVal("Departments", "department_id"));
    }

    @Test
    public void insertTest() {
        Department department = new Department();
        department.setDepartmentName("Test");
        department.setLocation((long) 1700);
        EntityManagerImp entityManagerImp = new EntityManagerImp();
        System.out.println(entityManagerImp.insert(department));
    }

    @Test
    public void findAllTest() {
        EntityManagerImp entityManagerImp = new EntityManagerImp();
        List<Department> departments = entityManagerImp.findAll(Department.class);
        System.out.println((long) departments.size());
        System.out.println(departments.stream().toArray().toString());
    }

    @Test
    public void testUpdate() {
        EntityManagerImp entityManagerImp = new EntityManagerImp();
        Department department = entityManagerImp.findById(Department.class, (long) 271);
        department.setDepartmentName("Test2");
        entityManagerImp.update(department);
    }

    @Test
    public void testDelete() {
        EntityManagerImp entityManagerImp = new EntityManagerImp();
        Department department = entityManagerImp.findById(Department.class, (long) 278);
        entityManagerImp.delete(department);
    }

    @Test
    public void findByParamTest() {
        EntityManagerImp entityManagerImp = new EntityManagerImp();
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("department_name", "Test");
        List<Department> departments = entityManagerImp.findByParams(Department.class, stringObjectMap);
        System.out.println(departments.size());
    }

    @Test
    public void insertListTest(){
        EntityManagerImp entityManagerImp = new EntityManagerImp();
        Department department = new Department();
        department.setDepartmentName("Testsadsadsa");
        department.setLocation((long) 1700);
        Department department2 = new Department();
        department2.setDepartmentName("dsadsadsadsadsadsad");
        department2.setLocation((long) 1700);

        List<Department> departments = new ArrayList<>();
        departments.add(department);
        departments.add(department2);
        entityManagerImp.insert(departments);
    }
}