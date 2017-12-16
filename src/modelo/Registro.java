/**
 * Resuelve el Examen del módulo 6 "Profundización Desarrollo de Software",
 * Programa CORFO "Mil Programadores".
 *
 * @autor Daniel Zúñiga Correa, y Felipe García 2017-12-15 (yyyy-mm-dd)
 */
package modelo;

/*importación de las librerías correspondientes*/
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que contiene los métodos propios necesarios para leer y poblar la base
 * de datos
 *
 * @author daniel Zúñiga Correa, 2017-12-07 (yyyy-mm-dd)
 */
public class Registro extends Conexion {

    /*Declara las constantes correspondientes a los nombres de las tablas y 
    base de datos*/
    public final String dataBase = "Empleado";
    public final String Empleado = "modulo6examendanielzuniga.empleados";

    /*devuelve el color según el char de que se trate*/
    public String TransformaEstadoCivil(String estadoCivil) {
        String estadoTmp = "";
        switch (estadoCivil) {
            case "C":
                estadoTmp = "Casado";
                break;
            case "S":
                estadoTmp = "Soltero";
                break;
            case "V":
                estadoTmp = "Viudo";
                break;
        }
        return estadoTmp;
    }

    /**
     * Método para obtener la cantidad de registros que contiene la tabla
     * películas, y que fué necesario programar por separado del método
     * obtenerPeliculas para evitar un error por existir demasiadas conexiones a
     * la base de datos
     *
     * @return retorna el total de registros existentes en la tabla empleados
     */
    public int cantidadEmpleados() {
        int cantidadRegistros = 0;
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(*) as total FROM Empleados");
            ResultSet res = pstm.executeQuery();
            res.next();
            cantidadRegistros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            if (cantidadRegistros > 0) {
                System.err.println(e.getMessage());
            }
        }
        return cantidadRegistros;
    }

    /**
     * Método para almacenar el contenido de la tabla empleados en un array de
     * objetos Empleado, para su posterior uso
     *
     * @return retorna un array de objetos Empleado
     */
    public Empleado[] obtenerEmpleados() {
        Empleado[] todosEmpleados;
        int cantidadRegistros = cantidadEmpleados();
        todosEmpleados = new Empleado[cantidadRegistros];
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM Empleados");
            ResultSet res = pstm.executeQuery();
            int cont = 0;
            while (res.next()) {
                todosEmpleados[cont] = new Empleado();
                todosEmpleados[cont].setCodigo(Integer.parseInt(res.getString("codigo")));
                todosEmpleados[cont].setRut(res.getString("rut"));
                todosEmpleados[cont].setNombre(res.getString("nombre"));
                todosEmpleados[cont].setApellido(res.getString("apellido"));
                todosEmpleados[cont].setCelular(Integer.parseInt(res.getString("celular")));
                todosEmpleados[cont].setEmail(res.getString("email"));
                todosEmpleados[cont].setSueldoBruto(Integer.parseInt(res.getString("sueldo_bruto")));
                todosEmpleados[cont].setEstadoCivil(res.getString("est_civil"));
                todosEmpleados[cont].setNombreDepartamento(res.getString("nom_depto"));
                cont++;
            }
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return todosEmpleados;
    }

    /**
     * Método para crear la matriz de empleados a ingresar en el JTable de la
     * vista listar
     *
     * @return retorna un objeto DefaultTableModel que contiene una matriz de
     * empleados
     */
    public DefaultTableModel mostrarEmpleados() {
        String texto = "";
        DefaultTableModel tablemodel = new DefaultTableModel();
        String[] nombreColumnas = {"Codigo", "RUT", "Nombre", "Apellido",
            "Celular", "eMail", "Sueldo Bruto", "Estado Civil", "Departamento"};
        Empleado[] empleadosTemp = obtenerEmpleados();
        Object[][] dato = new String[empleadosTemp.length][10];
        int cont = 0;
        while (cont < empleadosTemp.length) {
            dato[cont][0] = String.valueOf(empleadosTemp[cont].getCodigo());
            dato[cont][1] = empleadosTemp[cont].getRut();
            dato[cont][2] = empleadosTemp[cont].getNombre();
            dato[cont][3] = empleadosTemp[cont].getApellido();
            dato[cont][4] = String.valueOf(empleadosTemp[cont].getCelular());
            dato[cont][5] = empleadosTemp[cont].getEmail();
            dato[cont][6] = String.valueOf(empleadosTemp[cont].getSueldoBruto());
            dato[cont][7] = TransformaEstadoCivil(empleadosTemp[cont].getEstadoCivil());
            dato[cont][8] = empleadosTemp[cont].getNombreDepartamento();
            cont++;
        }
        tablemodel.setDataVector(dato, nombreColumnas);
        return tablemodel;
    }

    /**
     * Método para insertar un objeto Empleado a la tabla empleados
     *
     * @param codigo corresponde al campo respectivo de la tabla
     * @param nombre corresponde al campo respectivo de la tabla
     * @param descripcion corresponde al campo respectivo de la tabla
     * @param genero corresponde al campo respectivo de la tabla
     * @param precioVenta corresponde al campo respectivo de la tabla
     * @param numero corresponde al campo respectivo de la tabla
     * @param color corresponde al campo respectivo de la tabla
     * @param tipo corresponde al campo respectivo de la tabla
     * @param stock corresponde al campo respectivo de la tabla
     * @param cantidad corresponde al campo respectivo de la tabla
     * @return retorna true si la inserción fué existosa
     */
    public boolean agregarEmpleado(int codigo, String rut, String nombre,
            String apellido, int celular, String email, int sueldoBruto,
            String estadoCivil, String nombreDepartamento) {
        /*objetos temporales necesarios para la ejecución del método*/
        Empleado empleadosTemp = new Empleado();
        /*verifica que no pre exista una tupla con el mismo código, para no
        vulnerar la primera forma normal*/
        if (!empleadoExiste(codigo)) {
            if (empleadosTemp.setCodigo(codigo)
                    && empleadosTemp.setRut(rut)
                    && empleadosTemp.setNombre(nombre)
                    && empleadosTemp.setApellido(apellido)
                    && empleadosTemp.setCelular(celular)
                    && empleadosTemp.setEmail(email)
                    && empleadosTemp.setSueldoBruto(sueldoBruto)
                    && empleadosTemp.setEstadoCivil(estadoCivil)
                    && empleadosTemp.setNombreDepartamento(nombreDepartamento)) {
                String query = " INSERT INTO Empleados (codigo,rut,"
                        + "nombre, apellido, celular, email, sueldo_bruto, est_civil,"
                        + "nom_depto) "
                        + "VALUES ( '" + codigo + "','" + rut + "', '" + nombre
                        + "','" + apellido + "', '" + celular + "', '" + email
                        + "','" + sueldoBruto + "', '" + estadoCivil
                        + "', '" + nombreDepartamento + "' ) ";
                try {
                    PreparedStatement pstm = this.getConexion().prepareStatement(query);
                    pstm.execute();
                    pstm.close();
                    return true;
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Método para modificar una tupla contenida en la tabla empleados
     *
     * @param codigo corresponde al campo respectivo de la tabla
     * @param nombre corresponde al campo respectivo de la tabla
     * @param descripcion corresponde al campo respectivo de la tabla
     * @param genero corresponde al campo respectivo de la tabla
     * @param precioVenta corresponde al campo respectivo de la tabla
     * @param numero corresponde al campo respectivo de la tabla
     * @param color corresponde al campo respectivo de la tabla
     * @param tipo corresponde al campo respectivo de la tabla
     * @param stock corresponde al campo respectivo de la tabla
     * @param cantidad corresponde al campo respectivo de la tabla
     * @return retorna true si la modificación fué exitosa
     */
    public boolean modificarEmpleado(int codigo, String rut, String nombre,
            String apellido, int celular, String email, int sueldoBruto,
            String estadoCivil, String nombreDepartamento) {
        Empleado empleadosTemp = new Empleado();
        if (empleadosTemp.setCodigo(codigo)
                && empleadosTemp.setRut(rut)
                && empleadosTemp.setNombre(nombre)
                && empleadosTemp.setApellido(apellido)
                && empleadosTemp.setCelular(celular)
                && empleadosTemp.setEmail(email)
                && empleadosTemp.setSueldoBruto(sueldoBruto)
                && empleadosTemp.setEstadoCivil(estadoCivil)
                && empleadosTemp.setNombreDepartamento(nombreDepartamento)) {
            String query = "UPDATE Empleados SET rut='" + rut
                    + "', nombre='" + nombre + "', apellido='" + apellido
                    + "' , celular='" + celular + "', email='" + email
                    + "' , sueldo_bruto='" + sueldoBruto + "', est_civil='" + estadoCivil
                    + "' , nom_depto='" + nombreDepartamento
                    + "' WHERE codigo='" + codigo + "'";
            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(query);
                pstm.execute();
                pstm.close();
                return true;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Método para verificar la existencia del producto en la tabla respectiva
     * de la base de datos
     *
     * @param codigo corresponde al campo respectivo de la tabla
     * @return retorna true si existe a lo menos un registro con el código
     * buscado
     */
    public boolean empleadoExiste(int codigo) {
        boolean encontrado = false;
        Empleado empleadoTemp = new Empleado();
        Empleado[] empleadosTemp;
        empleadosTemp = obtenerEmpleados();
        /*verifica que codigo cumpla con la regla de negocio respectiva*/
        if (empleadoTemp.setCodigo(codigo)) {
            for (int cont = 0; cont <= empleadosTemp.length - 1; cont++) {
                if (empleadosTemp[cont].getCodigo() == codigo) {
                    encontrado = true;
                }
            }
        }
        return encontrado;
    }

    /**
     * Método para buscar la existencia de una tupla que contenga determinado
     * código en la tabla empleados
     *
     * @param codigo corresponde al campo respectivo de la tabla
     * @return retorna true sila busqueda fué exitosa
     */
    public Empleado buscarEmpleado(int codigo) {
        Empleado empleadoTmp = new Empleado();
        Empleado[] empleadosTemp;
        empleadosTemp = obtenerEmpleados();
        /*verifica si existe un registro con el código ingresado*/
        if (empleadoExiste(codigo)) {
            for (int cont = 0; cont <= empleadosTemp.length - 1; cont++) {
                if (empleadosTemp[cont].getCodigo() == codigo) {
                    empleadoTmp = empleadosTemp[cont];
                }
            }
        }
        return empleadoTmp;
    }

    /**
     * Método para eliminar registros en la tabla empleados
     *
     * @param codigo corresponde al campo respectivo de la tabla
     * @return retorna true si la eliminación fué exitosa
     */
    public boolean eliminarEmpleado(int codigo) {
        boolean resultado = false;
        /*verifica si existe un registro con el código ingresado*/
        if (empleadoExiste(codigo)) {
            String query = " DELETE FROM Empleados WHERE codigo=" + codigo + " ";
            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(query);
                pstm.execute();
                pstm.close();
                resultado = true;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

        }
        return resultado;
    }

    /**
     * Método que resuelve la consulta número 2 del exámen
     *
     * @return retorna el total de registros existentes de los empleados del
     * departamento de "Redes"
     *
     */
    public String empleadosRedes() {
        String texto = "LISTA DE EMPLEADOS DEL DEPARTAMENTO DE REDES:\n";
        String query = "SELECT CODIGO , RUT, NOMBRE, APELLIDO, CELULAR, EMAIL, CONCAT('$',SUELDO_BRUTO) AS \"SUELDO_BRUTO\", \n"
                + "CASE WHEN EST_CIVIL='C' THEN REPLACE (EST_CIVIL,'C', 'CASADO') \n"
                + "WHEN EST_CIVIL='S' THEN REPLACE (EST_CIVIL,'S', 'SOLTERO') \n"
                + "WHEN EST_CIVIL='V' THEN REPLACE (EST_CIVIL,'V', 'VIUDO') \n"
                + "END AS \"ESTADO_CIVIL\"\n"
                + "FROM EMPLEADOS\n"
                + "WHERE NOM_DEPTO='Redes'";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            ResultSet res = pstm.executeQuery();
            int cont = 1;
            while (res.next()) {
                texto += "Empleado número " + cont + ".--- ";
                texto += "Código: " + res.getString("codigo") + " -- ";
                texto += "RUT: " + res.getString("rut") + " - ";
                texto += "Nombre: " + res.getString("nombre") + " -- ";
                texto += "Apellido: " + res.getString("apellido") + " -- ";
                texto += "Celular: " + res.getString("celular") + " -- ";
                texto += "eMail: " + res.getString("email") + " -- ";
                texto += "Sueldo Bruto: " + res.getString("SUELDO_BRUTO") + " -- ";
                texto += "Estado Civil: " + res.getString("ESTADO_CIVIL") + "\n";
                cont++;
            }
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return texto;
    }

    /**
     * Método que resuelve la consulta número 3 del exámen
     *
     * @return retorna el detalle de los empleados eliminados
     *
     */
    public String eliminaEmpleadosPorSueldo(int sueldoBruto) {
        String texto = "LISTA DE EMPLEADOS ELIMINADOS:\n";
        String query = "SELECT CODIGO , RUT, NOMBRE, APELLIDO, CELULAR, EMAIL, CONCAT('$',SUELDO_BRUTO) AS \"SUELDO_BRUTO\", \n"
                + "EST_CIVIL AS \"ESTADO_CIVIL\"\n"
                + "FROM EMPLEADOS\n"
                + "WHERE SUELDO_BRUTO=" + sueldoBruto + " ";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            ResultSet res = pstm.executeQuery();
            int cont = 1;
            while (res.next()) {
                texto += "Empleado número " + cont + ".--- ";
                texto += "Código: " + res.getString("codigo") + " -- ";
                texto += "RUT: " + res.getString("rut") + " - ";
                texto += "Nombre: " + res.getString("nombre") + " -- ";
                texto += "Apellido: " + res.getString("apellido") + " -- ";
                texto += "Celular: " + res.getString("celular") + " -- ";
                texto += "eMail: " + res.getString("email") + " -- ";
                texto += "Sueldo Bruto: " + res.getString("SUELDO_BRUTO") + " -- ";
                texto += "Estado Civil: " + res.getString("ESTADO_CIVIL") + "\n";
                cont++;
            }
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        query = "DELETE EMPLEADOS WHERE SUELDO_BRUTO=" + sueldoBruto;
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            ResultSet res = pstm.executeQuery();
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return texto;
    }
}
