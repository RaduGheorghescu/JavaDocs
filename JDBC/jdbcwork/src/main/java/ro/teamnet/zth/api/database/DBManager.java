package ro.teamnet.zth.api.database;

import java.sql.*;

/**
 * Created by Radu.Gheorghescu on 7/13/2017.
 */
public class DBManager {
    private static String CONNECTION_STRING= "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT+":xe";
    private static Boolean isDriverRegisterd = false;
    private DBManager() throws UnsupportedOperationException{

    }
    private static void registerDriver() throws ClassNotFoundException {
        isDriverRegisterd = true;
        Class.forName(DBProperties.DRIVER_CLASS);
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            if (!isDriverRegisterd) {
                registerDriver();
            }
            connection = DriverManager.getConnection(CONNECTION_STRING, DBProperties.USER, DBProperties.PASS);
        }catch (SQLException e){
            e.getStackTrace();
        }
        return connection;
    }

    public static ResultSet checkConnection(Connection connection) throws SQLException {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1 FROM DUAL");
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException e){
            e.getStackTrace();
        }
        return resultSet;
    }
}
