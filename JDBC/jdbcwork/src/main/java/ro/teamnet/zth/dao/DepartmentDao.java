package ro.teamnet.zth.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImp;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;
import java.util.Map;

/**
 * Created by Radu.Gheorghescu on 7/14/2017.
 */
public class DepartmentDao {
    EntityManager entityManager = new EntityManagerImp();
    public Department findById(Long id){
        return entityManager.findById(Department.class,id);
    }
    public Long getNextIdVal(){
        return entityManager.getNextIdVal("Departments","department_id");
    }
    public  Department insert(Department department){
        return (Department) entityManager.insert(department);
    }

    public List<Department> findAll(){
        return entityManager.findAll(Department.class);
    }

    public Department update(Department department){
        return entityManager.update(department);
    }

    public void delete(Department department){
        entityManager.delete(department);
    }

    public List<Department> findByParams(Map<String, Object> params){
        return entityManager.findByParams(Department.class,params);
    }
}
