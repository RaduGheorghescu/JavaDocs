package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;

import javax.management.Query;
import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Radu.Gheorghescu on 7/13/2017.
 */
public class EntityManagerImp implements EntityManager {
    public <T> T findById(Class<T> entityClass, Long id) {
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columnInfoList = EntityUtils.getColumns(entityClass);
        List<Field> columns = EntityUtils.getFieldsByAnnotations(entityClass, Column.class);
        Field idColumn = EntityUtils.getFieldsByAnnotations(entityClass, Id.class).get(0);
        Condition condition = new Condition();
        Id idColumnAnnotation = idColumn.getAnnotation(Id.class);
        condition.setColumnName(idColumnAnnotation.name());
        condition.setValue(id);
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.addCondition(condition);
        queryBuilder.setQueryType(QueryType.SELECT);
        queryBuilder.addQueryColumns(columnInfoList);
        String query = queryBuilder.createQuery();
        ResultSet resultSet = null;

        try {
            if (connection != null) {
                resultSet = connection.prepareStatement(query).executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        T object = null;
        try {
            object = entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    for (ColumnInfo columnInfo : columnInfoList) {
                        Field field = entityClass.getDeclaredField(columnInfo.getColumnName());
                        field.setAccessible(true);
                        field.set(object, EntityUtils.castFromSqlType(EntityUtils.getSqlValue(resultSet.getObject(columnInfo.getDbColumnName())), columnInfo.getColumnType()));
                    }
                }
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return object;
    }

    public Long getNextIdVal(String tableName, String columnIdName) {
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Long returnId = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX (" + columnIdName + ") FROM " + tableName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                returnId = resultSet.getLong(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnId;
    }

    public <T> Object insert(T entity) {
        Long id = 1L;
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());

        for (ColumnInfo column : columns) {
            if (column.isId()) {
                column.setValue(getNextIdVal(tableName, column.getDbColumnName()));
                id = getNextIdVal(tableName, column.getDbColumnName());
            } else {
                Field field = null;
                try {
                    field = entity.getClass().getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                try {
                    if (field != null) {
                        column.setValue(field.get(entity));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setQueryType(QueryType.INSERT);
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columns);
        String query = queryBuilder.createQuery();
        try {
            connection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return findById(entity.getClass(), id);
    }

    public <T> void insert(List<T> list){
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Object entity : list) {
            String tableName = EntityUtils.getTableName(entity.getClass());
            List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());

            for (ColumnInfo column : columns) {
                if (column.isId()) {
                    column.setValue(getNextIdVal(tableName, column.getDbColumnName())+list.indexOf(entity));
                } else {
                    Field field = null;
                    try {
                        field = entity.getClass().getDeclaredField(column.getColumnName());
                        field.setAccessible(true);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (field != null) {
                            column.setValue(field.get(entity));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.setQueryType(QueryType.INSERT);
            queryBuilder.setTableName(tableName);
            queryBuilder.addQueryColumns(columns);
            String query = queryBuilder.createQuery();
            try {
                connection.createStatement().executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T> List<T> findAll(Class<T> entityClass) {
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.setQueryType(QueryType.SELECT);
        queryBuilder.addQueryColumns(columns);
        String query = queryBuilder.createQuery();

        ResultSet resultSet = null;
        try {
            if (connection != null) {
                resultSet = connection.prepareStatement(query).executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<T> list = new ArrayList<T>();

        try {
            while (resultSet.next()) {
                Object instance = null;
                try {
                    instance = entityClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                for (ColumnInfo column : columns) {
                    Field field = entityClass.getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    field.set(instance, EntityUtils.castFromSqlType(EntityUtils.getSqlValue(resultSet.getObject(column.getDbColumnName())), column.getColumnType()));
                }
                list.add((T) instance);
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public <T> T update(T entity) {
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());
        Long id = null;
        String columnName = null;
        for (ColumnInfo column : columns) {
            try {
                Field field = entity.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                column.setValue(field.get(entity));
                if (column.isId()) {
                    id = (Long) column.getValue();
                    columnName = column.getDbColumnName();
                }

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Condition condition = new Condition();
        condition.setValue(id);
        condition.setColumnName(columnName);

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.setQueryType(QueryType.UPDATE);
        queryBuilder.addCondition(condition);
        queryBuilder.addQueryColumns(columns);

        String query = queryBuilder.createQuery();
        try {
            connection.prepareStatement(query).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void delete(Object entity) {
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());
        Long id = null;
        String columnName = null;
        for (ColumnInfo column : columns) {
            try {
                Field field = entity.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                column.setValue(field.get(entity));
                if (column.isId()) {
                    id = (Long) column.getValue();
                    columnName = column.getDbColumnName();

                }

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Condition condition = new Condition();
        condition.setValue(id);
        condition.setColumnName(columnName);

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.setQueryType(QueryType.DELETE);
        queryBuilder.addCondition(condition);
        queryBuilder.addQueryColumns(columns);
        String query = queryBuilder.createQuery();

        try {
            connection.prepareStatement(query).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) {
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
        for (ColumnInfo column : columns) {
            try {
                Field field = entityClass.getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                column.setValue(field.get(entityClass.newInstance()));

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        Condition condition = new Condition();

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setQueryType(QueryType.SELECT);
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columns);

        for (Map.Entry<String, Object> param : params.entrySet()) {
            condition.setValue(param.getValue());
            condition.setColumnName(param.getKey());
            queryBuilder.addCondition(condition);
        }
        String query = queryBuilder.createQuery();

        ResultSet resultSet = null;
        try {
            if (connection != null) {
                resultSet = connection.prepareStatement(query).executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<T> list = new ArrayList<T>();

        try {
            while (resultSet.next()) {
                Object instance = null;
                try {
                    instance = entityClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                for (ColumnInfo column : columns) {
                    Field field = entityClass.getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    field.set(instance, EntityUtils.castFromSqlType(EntityUtils.getSqlValue(resultSet.getObject(column.getDbColumnName())), column.getColumnType()));
                }
                list.add((T) instance);
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }
}
