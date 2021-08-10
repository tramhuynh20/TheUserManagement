
package utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtils {
    public static Connection getConnection() throws SQLException, NamingException{
        Connection conn= null;
        Context context= new InitialContext();
        Context envCtx= (Context) context.lookup("java:comp/env");
        DataSource ds= (DataSource) envCtx.lookup("DBConn");
        conn= ds.getConnection();
        return conn;
    }
}
