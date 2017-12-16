/**
 * Resuelve el Examen del módulo 6 "Profundización Desarrollo de Software",
 * Programa CORFO "Mil Programadores".
 *
 * @autor Daniel Zúñiga Correa, y Felipe García 2017-12-15 (yyyy-mm-dd)
 */
package modelo;

//importación de las librerías correspondientes
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase que contiene el método necesario para conectar el programa a la base de
 * datos respectiva.
 *
 * @author daniel Zúñiga Correa, 2017-12-15 (yyyy-mm-dd)
 */
public class Conexion {

    final static String url = "localhost";
    final static String usuario = "modulo6examendanielzuniga";
    final static String contrasena = "modulo6examendanielzuniga";

    public static Connection getConexion() {
        Connection connection = null;
        try {
            String driverClassName = "oracle.jdbc.driver.OracleDriver";
            String driverUrl = "jdbc:oracle:thin:@" + url + ":" + "1521" + ":XE";
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(driverUrl, usuario, contrasena);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
