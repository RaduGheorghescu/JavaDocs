package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radu.Gheorghescu on 7/12/2017.
 */
public class EntityUtils {
    public EntityUtils() throws UnsupportedOperationException{
    }

    public static String getTableName(Class entity) throws NoSuchFieldException {
        if(entity.isAnnotationPresent(Table.class)){
            Table annotation = (Table) entity.getAnnotation(Table.class);
            return annotation.name();
        }else
            return  entity.getName();
    }

    public static List<ColumnInfo> getColumns(Class entity){
        Field[] fields= entity.getFields();
        List<ColumnInfo> columnInfoList = new ArrayList<>();
        for(Field field:fields){
            if(field.getAnnotation(Column.class) != null || field.getAnnotation(Id.class) != null){
                ColumnInfo columnInfo = new ColumnInfo();
                columnInfo.setColumnName(field.getName());
                columnInfo.setColumnType(field.getType());
                Table annotation = field.getAnnotation(Table.class);
                columnInfo.setDbColumnName(annotation.name());
                if (field.isAnnotationPresent(Id.class)){
                    columnInfo.setId(true);
                }else {
                    columnInfo.setId(false);
                }
                columnInfoList.add(columnInfo);
            }
        }
        return columnInfoList;
    }

    public static Object castFromSqlType(Object value, Class wantedType){
        if(value instanceof BigDecimal && wantedType.getClass().isInstance(Integer.class)){
            return ((BigDecimal) value).intValue();
        }else if (value instanceof BigDecimal && wantedType.getClass().isInstance(Long.class)){
            return ((BigDecimal) value).longValue();
        }else if (value instanceof BigDecimal && wantedType.getClass().isInstance(Float.class)){
            return ((BigDecimal) value).floatValue();
        }else if (value instanceof BigDecimal && wantedType.getClass().isInstance(Double.class)){
            return ((BigDecimal) value).doubleValue();
        }else {
            return value;
        }
    }

    public static List<Field> getFieldsAnnotations(Class clazz, Class annotation){
        Field[] fields = clazz.getFields();
        List<Field> annotationList = new ArrayList<>();
        for(Field field : fields){
            if(field.isAnnotationPresent(annotation)){
                annotationList.add(field);
            }
        }

        return annotationList;
    }

    public static Object getSqlValue(Object object){
        if(object.getClass().isAnnotationPresent(Table.class)){
            Field[] fields = object.getClass().getFields();
            for(Field field : fields){
                if (field.isAnnotationPresent(Id.class)){
                    field.setAccessible(true);
                    return object;
                }
            }
        }else return object;
        return object;
    }
}
