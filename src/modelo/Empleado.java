/**
 * Resuelve el Examen del módulo 6 "Profundización Desarrollo de Software",
 * Programa CORFO "Mil Programadores".
 *
 * @autor Daniel Zúñiga Correa, y Felipe García 2017-12-15 (yyyy-mm-dd)
 */
package modelo;

/**
 * Contienen la clase Empleado
 *
 * @author daniel Zúñiga Correa, 2017-12-15 (yyyy-mm-dd)
 */
public class Empleado {

//    atributos de clase
    private int codigo;
    private String rut;
    private String nombre;
    private String apellido;
    private int celular;
    private String email;
    private int sueldoBruto;
    private String estadoCivil;
    private String nombreDepartamento;

//    constructor sin parámetros
    public Empleado() {
    }

//    constructor con parámetros
    public Empleado(int codigo, String rut, String nombre, String apellido, int celular, String email, int sueldoBruto, String estadoCivil, String nombreDepartamento) {
        setCodigo(codigo);
        setRut(rut);
        setNombre(nombre);
        setApellido(apellido);
        setCelular(celular);
        setEmail(email);
        setSueldoBruto(sueldoBruto);
        setEstadoCivil(estadoCivil);
        setNombreDepartamento(nombreDepartamento);
    }

//    Mutadores y accesadores que contienen las reglas de negocio
    public int getCodigo() {
        return codigo;
    }

    public boolean setCodigo(int codigo) {
        if (codigo > 0 && codigo <= 100) {
            this.codigo = codigo;
            return true;
        } else {
            return false;
        }
    }

    public String getRut() {
        return rut;
    }

    public boolean setRut(String rut) {
        if (!rut.equalsIgnoreCase(" ") && rut.length() > 0) {
            this.rut = rut;
            return true;
        } else {
            return false;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public boolean setNombre(String nombre) {
        if (!nombre.equalsIgnoreCase(" ") && nombre.length() > 0) {
            this.nombre = nombre;
            return true;
        } else {
            return false;
        }
    }

    public String getApellido() {
        return apellido;
    }

    public boolean setApellido(String apellido) {
        if (!apellido.equalsIgnoreCase(" ") && apellido.length() > 0) {
            this.apellido = apellido;
            return true;
        } else {
            return false;
        }
    }

    public int getCelular() {
        return celular;
    }

    public boolean setCelular(int celular) {
        if (String.valueOf(celular).length() == 9) {
            this.celular = celular;
            return true;
        } else {
            return false;
        }
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        this.email = email;
        return true;
    }

    public int getSueldoBruto() {
        return sueldoBruto;
    }

    public boolean setSueldoBruto(int sueldoBruto) {
        if (sueldoBruto >= 120000) {
            this.sueldoBruto = sueldoBruto;
            return true;
        } else {
            return false;
        }
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public boolean setEstadoCivil(String estadoCivil) {
        if (estadoCivil.equalsIgnoreCase("C")
                || estadoCivil.equalsIgnoreCase("S")
                || estadoCivil.equalsIgnoreCase("V")) {
            this.estadoCivil = estadoCivil;
            return true;
        } else {
            return false;
        }
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public boolean setNombreDepartamento(String nombreDepartamento) {
        if (nombreDepartamento.equalsIgnoreCase("Informática")
                || nombreDepartamento.equalsIgnoreCase("Redes")
                || nombreDepartamento.equalsIgnoreCase("Administración")
                || nombreDepartamento.equalsIgnoreCase("Finanzas")
                || nombreDepartamento.equalsIgnoreCase("Bienestar")) {
            this.nombreDepartamento = nombreDepartamento;
            return true;
        } else {
            return false;
        }
    }

}
