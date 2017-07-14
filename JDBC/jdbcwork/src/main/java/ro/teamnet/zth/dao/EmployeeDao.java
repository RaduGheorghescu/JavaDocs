package ro.teamnet.zth.dao;

import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.api.em.ColumnInfo;
import ro.teamnet.zth.api.em.EntityUtils;
import ro.teamnet.zth.api.em.QueryBuilder;
import ro.teamnet.zth.api.em.QueryType;
import ro.teamnet.zth.appl.domain.Employee;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radu.Gheorghescu on 7/14/2017.
 */
public class EmployeeDao {
    public List<Employee> findByDepartment(String searchValue){
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        String tableName = EntityUtils.getTableName(Employee.class);
        List<ColumnInfo> columns = EntityUtils.getColumns(Employee.class);

        String query = "SELECT EMPLOYEES.* FROM EMPLOYEES JOIN DEPARTMENTS ON EMPLOYEES.DEPARTMENT_ID = DEPARTMENTS.DEPARTMENT_ID WHERE UPPER(DEPARTMENT_NAME) LIKE '%'||UPPER('"+searchValue+"')||'%'";

        ResultSet resultSet = null;
        try {
            if (connection != null) {
                resultSet = connection.prepareStatement(query).executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Employee> list = new ArrayList<Employee>();

        try {
            while (resultSet.next()) {
                Object instance = null;
                try {
                    instance = Employee.class.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                for (ColumnInfo column : columns) {
                    Field field = Employee.class.getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    field.set(instance, EntityUtils.castFromSqlType(EntityUtils.getSqlValue(resultSet.getObject(column.getDbColumnName())), column.getColumnType()));
                }
                list.add((Employee) instance);
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }
}
