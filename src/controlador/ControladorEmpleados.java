/**
 * Resuelve el Examen del módulo 6 "Profundización Desarrollo de Software",
 * Programa CORFO "Mil Programadores".
 *
 * @autor Daniel Zúñiga Correa, y Felipe García 2017-12-15 (yyyy-mm-dd)
 */
package controlador;

//importación de las librerías correspondientes
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;

/**
 * Clase Controlador que contiene las clases correspondientes al "Controlador"
 * del patrón MVC requerido en el documento del Taller. Implementa los Listener
 * respectivos
 *
 * @author Daniel Zúñiga Correa, y Felipe García 2017-12-15 (yyyy-mm-dd)
 */
public class ControladorEmpleados implements ActionListener, MouseListener {

//    declara e inicializa las vistas
    public MenuPrincipal vistaPrincipal = new MenuPrincipal();
    public MenuBuscar vistaBuscar = new MenuBuscar();
    public MenuDatos vistaDatos = new MenuDatos();
    public MenuMostrar vistaMostrar = new MenuMostrar();
    public MenuBotonAgregar vistaBotonAgregar = new MenuBotonAgregar();
    public MenuBotonBuscarEliminar vistaBotonBuscarEliminar = new MenuBotonBuscarEliminar();
    public MenuBotonBuscarModificar vistaBotonBuscarModificar = new MenuBotonBuscarModificar();
    public MenuBotonEliminar vistaBotonEliminar = new MenuBotonEliminar();
    public MenuBotonMostrar vistaBotonMostrar = new MenuBotonMostrar();
    public MenuBotonModificar vistaBotonModificar = new MenuBotonModificar();
    public MenuTituloAgregar vistaTituloAgregar = new MenuTituloAgregar();
    public MenuTituloEliminar vistaTituloEliminar = new MenuTituloEliminar();
    public MenuTituloMostrar vistaTituloMostrar = new MenuTituloMostrar();
    public MenuTituloModificar vistaTituloModificar = new MenuTituloModificar();

//    declara e inicializa el objeto que contiene los métodos para leer y
//    grabar información en la base de datos
    public Registro registro = new Registro();

//    Constructor sin parámetros
    public ControladorEmpleados() {
    }

    /**
     * Método para inicializar las vistas e implementar los Listeners
     */
    public void iniciar() {
        /*inicializa la vista del menú principal*/
        this.vistaPrincipal.setLocationRelativeTo(null);
        this.vistaPrincipal.setVisible(true);
        vistaPrincipal.panelDatos.setVisible(true);
        vistaPrincipal.panelTitulo.setVisible(true);
        vistaPrincipal.panelBoton.setVisible(true);

//        controla la opción del menú que termina el programa
        this.vistaPrincipal.mnuArchivoSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });
//        controla la opcion del menú que desplega la vista para agregar empleados
        this.vistaPrincipal.mnuOpcionesAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ocultarVistas();
                limpiarDatos();//limpia los campos de la vista
                habilitarDatos();//habilita la edición de datos

                vistaPrincipal.setSize(580, 480);

                vistaPrincipal.panelTitulo.setPreferredSize(new Dimension(540, 40));
                vistaPrincipal.panelTitulo.revalidate();
                vistaPrincipal.panelTitulo.repaint();
                vistaPrincipal.panelTitulo.add(vistaTituloAgregar, BorderLayout.CENTER);

                vistaPrincipal.panelDatos.setPreferredSize(new Dimension(430, 270));
                vistaPrincipal.panelDatos.revalidate();
                vistaPrincipal.panelDatos.repaint();
                vistaPrincipal.panelDatos.add(vistaDatos, BorderLayout.CENTER);

                vistaPrincipal.panelBoton.setPreferredSize(new Dimension(110, 270));
                vistaPrincipal.panelBoton.revalidate();
                vistaPrincipal.panelBoton.repaint();
                vistaPrincipal.panelBoton.add(vistaBotonAgregar, BorderLayout.CENTER);

                vistaTituloAgregar.setSize(530, 30);
                vistaDatos.setSize(430, 270);
                vistaBotonAgregar.setSize(110, 270);

                vistaTituloAgregar.setVisible(true);
                vistaDatos.setVisible(true);
                vistaBotonAgregar.setVisible(true);
            }
        });
//      controla la vista para comenzar la eliminación de empleados
        this.vistaPrincipal.mnuOpcionesEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ocultarVistas();
                vistaBuscar.txtCodigo.setText("0");

                vistaPrincipal.setSize(580, 300);

                vistaPrincipal.panelTitulo.setPreferredSize(new Dimension(540, 40));
                vistaPrincipal.panelTitulo.revalidate();
                vistaPrincipal.panelTitulo.repaint();
                vistaPrincipal.panelTitulo.add(vistaTituloEliminar, BorderLayout.CENTER);

                vistaPrincipal.panelDatos.setPreferredSize(new Dimension(430, 90));
                vistaPrincipal.panelDatos.revalidate();
                vistaPrincipal.panelDatos.repaint();
                vistaPrincipal.panelDatos.add(vistaBuscar, BorderLayout.CENTER);

                vistaPrincipal.panelBoton.setPreferredSize(new Dimension(110, 90));
                vistaPrincipal.panelBoton.revalidate();
                vistaPrincipal.panelBoton.repaint();
                vistaPrincipal.panelBoton.add(vistaBotonBuscarEliminar, BorderLayout.CENTER);

                vistaTituloEliminar.setSize(530, 30);
                vistaBuscar.setSize(430, 80);
                vistaBotonBuscarEliminar.setSize(110, 80);

                vistaTituloEliminar.setVisible(true);
                vistaBuscar.setVisible(true);
                vistaBotonBuscarEliminar.setVisible(true);
            }
        });
//        controla la vista para comenzar la modificacion de empleados
        this.vistaPrincipal.mnuOpcionesModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ocultarVistas();
                vistaBuscar.txtCodigo.setText("0");

                vistaPrincipal.setSize(580, 300);

                vistaPrincipal.panelTitulo.setPreferredSize(new Dimension(540, 40));
                vistaPrincipal.panelTitulo.revalidate();
                vistaPrincipal.panelTitulo.repaint();
                vistaPrincipal.panelTitulo.add(vistaTituloModificar, BorderLayout.CENTER);

                vistaPrincipal.panelDatos.setPreferredSize(new Dimension(430, 90));
                vistaPrincipal.panelDatos.revalidate();
                vistaPrincipal.panelDatos.repaint();
                vistaPrincipal.panelDatos.add(vistaBuscar, BorderLayout.CENTER);

                vistaPrincipal.panelBoton.setPreferredSize(new Dimension(110, 90));
                vistaPrincipal.panelBoton.revalidate();
                vistaPrincipal.panelBoton.repaint();
                vistaPrincipal.panelBoton.add(vistaBotonBuscarModificar, BorderLayout.CENTER);

                vistaTituloModificar.setSize(530, 30);
                vistaBuscar.setSize(430, 80);
                vistaBotonBuscarModificar.setSize(110, 80);

                vistaTituloModificar.setVisible(true);
                vistaBuscar.setVisible(true);
                vistaBotonBuscarModificar.setVisible(true);
            }
        });
//        controla la vista para mostrar listados de empleados
        this.vistaPrincipal.mnuOpcionesMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (registro.cantidadEmpleados() > 0) {
                    //despliega vista listar
                    ocultarVistas();
                    limpiarMostrar();

                    vistaPrincipal.setSize(580, 600);

                    vistaPrincipal.panelTitulo.setPreferredSize(new Dimension(540, 40));
                    vistaPrincipal.panelTitulo.revalidate();
                    vistaPrincipal.panelTitulo.repaint();
                    vistaPrincipal.panelTitulo.add(vistaTituloMostrar, BorderLayout.CENTER);

                    vistaPrincipal.panelDatos.setPreferredSize(new Dimension(430, 465));
                    vistaPrincipal.panelDatos.revalidate();
                    vistaPrincipal.panelDatos.repaint();
                    vistaPrincipal.panelDatos.add(vistaMostrar, BorderLayout.CENTER);

                    vistaPrincipal.panelBoton.setPreferredSize(new Dimension(110, 465));
                    vistaPrincipal.panelBoton.revalidate();
                    vistaPrincipal.panelBoton.repaint();
                    vistaPrincipal.panelBoton.add(vistaBotonMostrar, BorderLayout.CENTER);

                    vistaTituloMostrar.setSize(530, 30);
                    vistaMostrar.setSize(440, 455);
                    vistaBotonMostrar.setSize(110, 455);
                    vistaMostrar.tbMostrar.setModel(registro.mostrarEmpleados());
                    agregarMostrar();

                    vistaTituloMostrar.setVisible(true);
                    vistaMostrar.setVisible(true);
                    vistaBotonMostrar.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "No se han ingresado"
                            + " empleados al registro");
                }
            }
        });
//        se implementa esta opción del menú para cumplir con la Consulta número
//        1 del exámen 
        this.vistaPrincipal.mnuConsultasConsulta1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ocultarVistas();
                limpiarDatos();//limpia los campos de la vista
                habilitarDatos();//habilita la edición de datos

                vistaPrincipal.setSize(580, 480);

                vistaPrincipal.panelTitulo.setPreferredSize(new Dimension(540, 40));
                vistaPrincipal.panelTitulo.revalidate();
                vistaPrincipal.panelTitulo.repaint();
                vistaPrincipal.panelTitulo.add(vistaTituloAgregar, BorderLayout.CENTER);

                vistaPrincipal.panelDatos.setPreferredSize(new Dimension(430, 270));
                vistaPrincipal.panelDatos.revalidate();
                vistaPrincipal.panelDatos.repaint();
                vistaPrincipal.panelDatos.add(vistaDatos, BorderLayout.CENTER);

                vistaPrincipal.panelBoton.setPreferredSize(new Dimension(110, 270));
                vistaPrincipal.panelBoton.revalidate();
                vistaPrincipal.panelBoton.repaint();
                vistaPrincipal.panelBoton.add(vistaBotonAgregar, BorderLayout.CENTER);

                vistaTituloAgregar.setSize(530, 30);
                vistaDatos.setSize(430, 270);
                vistaBotonAgregar.setSize(110, 270);

                vistaTituloAgregar.setVisible(true);
                vistaDatos.setVisible(true);
                vistaBotonAgregar.setVisible(true);

                vistaDatos.txtCodigo.grabFocus();
            }
        });
//        se implementa esta opción del menú para cumplir con la consulta número
//        2 del exámen 
        this.vistaPrincipal.mnuConsultasConsulta2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JOptionPane.showMessageDialog(null, registro.empleadosRedes());
            }
        });
//        se implementa esta opción del menú para cumplir con la consulta número
//        3 del exámen 
        this.vistaPrincipal.mnuConsultasConsulta3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String texto = registro.eliminaEmpleadosPorSueldo(120000);
                if (texto.equalsIgnoreCase("LISTA DE EMPLEADOS ELIMINADOS:\n")) {
                    JOptionPane.showMessageDialog(null, "No se encontraron empleados con Sueldo Bruto de $120.000");
                } else {
                    JOptionPane.showMessageDialog(null, texto);
                }
            }
        });
//        se implementa esta opción del menú para cumplir con la consulta número
//        4 del exámen 
        this.vistaPrincipal.mnuConsultasConsulta4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JOptionPane.showMessageDialog(null, registro.modificaSueldos(0.1));
            }
        });

//        implementa Listener para botones en vista agregar
        this.vistaBotonAgregar.btnAgregar.setActionCommand("btnAgregar");
        this.vistaBotonAgregar.btnAgregar.addActionListener(this);

//        se ingresa nuevo botón "Limpiar" como requisito de la consulta
//        número 1 del exámen
        this.vistaBotonAgregar.btnLimpiarAgregar.setActionCommand("btnLimpiarAgregar");
        this.vistaBotonAgregar.btnLimpiarAgregar.addActionListener(this);

//        implementa Listener para botón en vista buscar eliminar
        this.vistaBotonBuscarEliminar.btnBuscar.setActionCommand("btnBuscarEliminar");
        this.vistaBotonBuscarEliminar.btnBuscar.addActionListener(this);

//        implementa Listener para botón en vista buscar modificar
        this.vistaBotonBuscarModificar.btnBuscarModificar.setActionCommand("btnBuscarModificar");
        this.vistaBotonBuscarModificar.btnBuscarModificar.addActionListener(this);

//        implementa Listener para botón en vista eliminar
        this.vistaBotonEliminar.btnEliminar.setActionCommand("btnEliminar");
        this.vistaBotonEliminar.btnEliminar.addActionListener(this);

//        implementa Listener para botón en vista mostrar
        this.vistaBotonMostrar.btnRefrescarMostrar.setActionCommand("btnRefrescarMostrar");
        this.vistaBotonMostrar.btnRefrescarMostrar.addActionListener(this);
        this.vistaBotonMostrar.btnModificarMostrar.setActionCommand("btnModificarMostrar");
        this.vistaBotonMostrar.btnModificarMostrar.addActionListener(this);
//        implementa Listener para mouse click en vista listar
        this.vistaMostrar.tbMostrar.addMouseListener(this);
        //se ingresa nuevo botón "Limpiar" como requisito de la consulta
        //número 1 del exámen
        this.vistaBotonMostrar.btnLimpiarMostrar.setActionCommand("btnLimpiarMostrar");
        this.vistaBotonMostrar.btnLimpiarMostrar.addActionListener(this);

//        implementa Listener para botón en vista modificar
        this.vistaBotonModificar.btnModificarModificar.setActionCommand("btnModificarModificar");
        this.vistaBotonModificar.btnModificarModificar.addActionListener(this);
        //se ingresa nuevo botón "Limpiar" como requisito de la consulta
        //número 1 del exámen
        this.vistaBotonModificar.btnLimpiarModificar.setActionCommand("btnLimpiarModificar");
        this.vistaBotonModificar.btnLimpiarModificar.addActionListener(this);

    }

    /**
     * Método para controlar las ocurrencia de seleccionar un botón en diversas
     * vistas
     *
     * @param e corresponde al objeto relacionado con el evento ocurrido
     */
    @Override
    public void actionPerformed(ActionEvent e) {
//      implementa el código que se ejecuta con motivo de la presión de un botón 
        switch (e.getActionCommand()) {
            case "btnAgregar":
                if (validaNumerosDatos()) {
//                    verifica la regla de negocio correspondiente a que el 
//                    código del producto a ingresar no exista previamente
                    if (!registro.empleadoExiste(
                            Integer.parseInt(vistaDatos.txtCodigo.getText()))) {
//                        verifica que los datos a ingresar cumplan con las 
//                        reglas de negocio de cada uno de ellos, y de ser así
//                        crea un nuevo registro en la base de datos
                        if (registro.agregarEmpleado(
                                Integer.parseInt(vistaDatos.txtCodigo.getText()),
                                vistaDatos.txtRut.getText(),
                                vistaDatos.txtNombre.getText(),
                                vistaDatos.txtApellido.getText(),
                                Integer.parseInt(vistaDatos.txtCelular.getText()),
                                vistaDatos.txtEmail.getText(),
                                Integer.parseInt(vistaDatos.txtSueldoBruto.getText()),
                                obtieneEstadoCivil(vistaDatos.cboEstadoCivil.getSelectedIndex()),
                                vistaDatos.cboNombreDepartamento.getSelectedItem().toString())) {
                            JOptionPane.showMessageDialog(null, "Empleado agregado correctamente.\n");
                            limpiarDatos();
                        } else {
//                            informa que el ingreso de datos vulnera la o las reglas de negocio
                            mensajeReglasNegocio();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya existe un Empleado con el código "
                                + vistaDatos.txtCodigo.getText() + "\n"
                                + "y en consecuencia no se agregó al registro.\n");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error en el ingreso "
                            + "de números\n Debe ingresar solo números en los"
                            + " campos correspondientes");
                }
                break;
//                se implementa esta opción como requisito de la consulta número
//                1 del exámen
            case "btnLimpiarAgregar":
                limpiarDatos();
                vistaDatos.txtCodigo.grabFocus();
                break;
            case "btnBuscarEliminar":
//                se verifica que el código que se desea buscar sea convertible 
//                a número
                if (esEntero(vistaBuscar.txtCodigo.getText())) {
//                    verifica que el producto a eliminar exista en la base de 
//                    datos
                    if (registro.empleadoExiste(
                            Integer.parseInt(vistaBuscar.txtCodigo.getText()))) {
                        ocultarVistas();
                        limpiarDatos();
                        vistaPrincipal.setSize(580, 480);
                        vistaPrincipal.panelTitulo.setPreferredSize(new Dimension(520, 40));
                        vistaPrincipal.panelTitulo.revalidate();
                        vistaPrincipal.panelTitulo.repaint();
                        vistaPrincipal.panelTitulo.add(vistaTituloEliminar, BorderLayout.CENTER);
                        vistaPrincipal.panelDatos.setPreferredSize(new Dimension(400, 270));
                        vistaPrincipal.panelDatos.revalidate();
                        vistaPrincipal.panelDatos.repaint();
                        vistaPrincipal.panelDatos.add(vistaDatos, BorderLayout.CENTER);

                        vistaPrincipal.panelBoton.setPreferredSize(new Dimension(100, 270));
                        vistaPrincipal.panelBoton.revalidate();
                        vistaPrincipal.panelBoton.repaint();
                        vistaPrincipal.panelBoton.add(vistaBotonEliminar, BorderLayout.CENTER);

                        vistaTituloEliminar.setSize(510, 30);
                        vistaDatos.setSize(400, 270);
                        vistaBotonEliminar.setSize(100, 270);

//                        inhabilita la edición de datos
                        inhabilitarDatos();
//                        llena los campos correspondientes de la vista 
//                        eliminar, con los datos correspondientes al producto
//                        a modificar
                        agregarDatos(Integer.parseInt(vistaBuscar.txtCodigo.getText()));

                        vistaTituloEliminar.setVisible(true);
                        vistaDatos.setVisible(true);
                        vistaBotonEliminar.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "El Empleado código "
                                + vistaBuscar.txtCodigo.getText() + " no existe en el registro.\n");
                    }
                }
                break;
            case "btnBuscarModificar":
//                verifica que el código que se desea buscar sea convertible 
//                a número
                if (esEntero(vistaBuscar.txtCodigo.getText())) {
//                    verifica que el producto a eliminar exista en la base de 
//                    datos
                    if (registro.empleadoExiste(
                            Integer.parseInt(vistaBuscar.txtCodigo.getText()))) {
                        ocultarVistas();
                        limpiarDatos();

                        vistaPrincipal.setSize(580, 480);

                        vistaPrincipal.panelTitulo.setPreferredSize(new Dimension(520, 40));
                        vistaPrincipal.panelTitulo.revalidate();
                        vistaPrincipal.panelTitulo.repaint();
                        vistaPrincipal.panelTitulo.add(vistaTituloModificar, BorderLayout.CENTER);

                        vistaPrincipal.panelDatos.setPreferredSize(new Dimension(400, 270));
                        vistaPrincipal.panelDatos.revalidate();
                        vistaPrincipal.panelDatos.repaint();
                        vistaPrincipal.panelDatos.add(vistaDatos, BorderLayout.CENTER);

                        vistaPrincipal.panelBoton.setPreferredSize(new Dimension(100, 270));
                        vistaPrincipal.panelBoton.revalidate();
                        vistaPrincipal.panelBoton.repaint();
                        vistaPrincipal.panelBoton.add(vistaBotonModificar, BorderLayout.CENTER);

                        vistaTituloModificar.setSize(510, 30);
                        vistaDatos.setSize(400, 270);
                        vistaBotonModificar.setSize(100, 270);

//                        habilita la edición de datos
                        habilitarDatos();
                        vistaDatos.txtCodigo.setEditable(false);
//                        llena los campos correspondientes de la vista 
//                        eliminar, con los datos correspondientes al producto
//                        a modificar
                        agregarDatos(Integer.parseInt(vistaBuscar.txtCodigo.getText()));

                        vistaTituloModificar.setVisible(true);
                        vistaDatos.setVisible(true);
                        vistaBotonModificar.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "El Empleado código "
                                + vistaBuscar.txtCodigo.getText() + " no existe en el registro.\n");
                    }
                }
                break;
//                se implementa esta opción como requisito de la consulta número
//                1 del exámen
            case "btnLimpiarModificar":
                limpiarModificar();
                vistaDatos.txtRut.grabFocus();
                break;
            case "btnEliminar":
//                dada la importancia de eliminar datos, se solicita verificación
//                previa
                if (pideConfirmacion("ESTA ACCION ELIMINARA"
                        + " PERMANENTEMENTE AL EMPLEADO DEL REGISTRO") == JOptionPane.YES_OPTION) {
//                    elimina y verifica que el producto sea eliminado de la base de datos
                    if (registro.eliminarEmpleado(
                            Integer.parseInt(vistaDatos.txtCodigo.getText()))) {
                        JOptionPane.showMessageDialog(null, "El Empleado "
                                + "código " + vistaDatos.txtCodigo.getText()
                                + " fué eliminado correctamente.\n");
                        ocultarVistas();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo "
                                + "eliminar al Empleado de Registro\n");
                    }
                }
                break;
//            botón contenido en la vista modificar
            case "btnModificarModificar":
//                verifica que no se ingresen datos no numericos en los 
//                    campos respectivos
                if (validaNumerosDatos()) {
//                    verifica que los datos a ingresar cumplan con las 
//                        reglas de negocio de cada uno de ellos, y de ser así los
//                        ingresa a la base de datos
                    if (registro.modificarEmpleado(
                            Integer.parseInt(vistaDatos.txtCodigo.getText()),
                            vistaDatos.txtRut.getText(),
                            vistaDatos.txtNombre.getText(),
                            vistaDatos.txtApellido.getText(),
                            Integer.parseInt(vistaDatos.txtCelular.getText()),
                            vistaDatos.txtEmail.getText(),
                            Integer.parseInt(vistaDatos.txtSueldoBruto.getText()),
                            obtieneEstadoCivil(vistaDatos.cboEstadoCivil.getSelectedIndex()),
                            vistaDatos.cboNombreDepartamento.getSelectedItem().toString())) {
                        JOptionPane.showMessageDialog(null, "Empleado modificado correctamente.\n");
                    } else {
                        mensajeReglasNegocio();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Error en el ingreso "
                            + "de números\n Debe ingresar solo números en los"
                            + " campos correspondientes");
                }
                break;
//            refresca los datos de la JTable contenida en la vista mostrar,
//                pensando en un acceso multiusuario a la base de datos
            case "btnRefrescarMostrar":
//                verifica que existan productos en la base de datos
                if (registro.cantidadEmpleados() > 0) {
                    this.vistaMostrar.tbMostrar.setModel(registro.mostrarEmpleados());
//                    refresca los campos de la vista mostrar
                    limpiarMostrar();
                    agregarMostrar();
                } else {
                    JOptionPane.showMessageDialog(null, "No se han ingresado"
                            + " empleados al registro");
                }
                break;
//                se implementa esta opción como requisito de la consulta número
//                1 del exámen
            case "btnLimpiarMostrar":
                limpiarMostrar();
                vistaMostrar.txtRut.grabFocus();
                break;
//           botón para modificar el contenido de la vista mostrar
            case "btnModificarMostrar":
//                verifica que no se ingresen datos no numéricos en los 
//                    campos respectivos
                if (validaNumerosMostrar()) {
//                    verifica que los datos a ingresar cumplan con las 
//                        reglas de negocio de cada uno de ellos, y de ser así los
//                        ingresa a la base de datos
                    if (registro.modificarEmpleado(
                            Integer.parseInt(vistaMostrar.txtCodigo.getText()),
                            vistaMostrar.txtRut.getText(),
                            vistaMostrar.txtNombre.getText(),
                            vistaMostrar.txtApellido.getText(),
                            Integer.parseInt(vistaMostrar.txtCelular.getText()),
                            vistaMostrar.txtEmail.getText(),
                            Integer.parseInt(vistaMostrar.txtSueldoBruto.getText()),
                            obtieneEstadoCivil(vistaMostrar.cboEstadoCivil.getSelectedIndex()),
                            vistaMostrar.cboNombreDepartamento.getSelectedItem().toString())) {
                        JOptionPane.showMessageDialog(null, "Empleado modificado correctamente.\n");
                        this.vistaMostrar.tbMostrar.setModel(registro.mostrarEmpleados());
                    } else {
                        mensajeReglasNegocio();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Error en el ingreso "
                            + "de números\n Debe ingresar solo números en los"
                            + " campos correspondientes");
                }
                break;
        }
    }

    /**
     * Sobreescritura del método que permite asignar acciones a los "clicks" del
     * mouse, utilizado en conjunto con la JTable de la vista listar.
     *
     * @param e corresponde al objeto relacionado con el evento ocurrido
     */
    @Override
    public void mouseClicked(MouseEvent e
    ) {
        if (e.getButton() == 1)//boton izquierdo
        {
            //Muestra datos de producto a modificar
            int fila = vistaMostrar.tbMostrar.rowAtPoint(e.getPoint());
            if (fila > -1) {
                vistaMostrar.txtCodigo.setText(String.valueOf(vistaMostrar.tbMostrar
                        .getValueAt(fila, 0)));
                vistaMostrar.txtRut.setText(String.valueOf(vistaMostrar.tbMostrar
                        .getValueAt(fila, 1)));
                vistaMostrar.txtNombre.setText(String.valueOf(vistaMostrar.tbMostrar
                        .getValueAt(fila, 2)));
                vistaMostrar.txtApellido.setText(String.valueOf(vistaMostrar.tbMostrar
                        .getValueAt(fila, 3)));
                vistaMostrar.txtCelular.setText(String.valueOf(vistaMostrar.tbMostrar
                        .getValueAt(fila, 4)));
                vistaMostrar.txtEmail.setText(String.valueOf(vistaMostrar.tbMostrar
                        .getValueAt(fila, 5)));
                vistaMostrar.txtSueldoBruto.setText(String.valueOf(vistaMostrar.tbMostrar
                        .getValueAt(fila, 6)));
                vistaMostrar.cboEstadoCivil.setSelectedIndex(fijaEstadoCivil(
                        String.valueOf(vistaMostrar.tbMostrar.getValueAt(fila, 7))));
                vistaMostrar.cboNombreDepartamento.setSelectedItem(vistaMostrar.tbMostrar
                        .getValueAt(fila, 8));
            }
        }
    }

    /**
     * Sobreescritura obligatoria de método presente en la interfase
     * MouseListener, que no es usado en este proyecto
     *
     * @param e corresponde al objeto relacionado con el evento ocurrido
     */
    @Override
    public void mousePressed(MouseEvent e
    ) {

    }

    /**
     * Sobreescritura obligatoria de método presente en la interfase
     * MouseListener, que no es usado en este proyecto
     *
     * @param e corresponde al objeto relacionado con el evento ocurrido
     */
    @Override
    public void mouseReleased(MouseEvent e
    ) {

    }

    /**
     * Sobreescritura obligatoria de método presente en la interfase
     * MouseListener, que no es usado en este proyecto
     *
     * @param e corresponde al objeto relacionado con el evento ocurrido
     */
    @Override
    public void mouseEntered(MouseEvent e
    ) {

    }

    /**
     * Sobreescritura obligatoria de método presente en la interfase
     * MouseListener, que no es usado en este proyecto
     *
     * @param e corresponde al objeto relacionado con el evento ocurrido
     */
    @Override
    public void mouseExited(MouseEvent e
    ) {

    }

    /**
     * Método para limpiar la vista datos
     */
    private void limpiarDatos() {
        vistaDatos.txtCodigo.setText("0");
        vistaDatos.txtRut.setText("");
        vistaDatos.txtNombre.setText("");
        vistaDatos.txtApellido.setText("");
        vistaDatos.txtCelular.setText("0");
        vistaDatos.txtEmail.setText("");
        vistaDatos.txtSueldoBruto.setText("0");
        vistaDatos.cboEstadoCivil.setSelectedIndex(0);
        vistaDatos.cboNombreDepartamento.setSelectedIndex(0);
    }

    /**
     * Método para limpiar la vista mostrar
     */
    private void limpiarMostrar() {
        vistaMostrar.txtRut.setText("");
        vistaMostrar.txtNombre.setText("");
        vistaMostrar.txtApellido.setText("");
        vistaMostrar.txtCelular.setText("0");
        vistaMostrar.txtEmail.setText("");
        vistaMostrar.txtSueldoBruto.setText("0");
        vistaMostrar.cboEstadoCivil.setSelectedIndex(0);
        vistaMostrar.cboNombreDepartamento.setSelectedIndex(0);
    }

    /**
     * Método para limpiar la vista modificar
     */
    private void limpiarModificar() {
        vistaDatos.txtRut.setText("");
        vistaDatos.txtNombre.setText("");
        vistaDatos.txtApellido.setText("");
        vistaDatos.txtCelular.setText("0");
        vistaDatos.txtEmail.setText("");
        vistaDatos.txtSueldoBruto.setText("0");
        vistaDatos.cboEstadoCivil.setSelectedIndex(0);
        vistaDatos.cboNombreDepartamento.setSelectedIndex(0);
    }

    /**
     * Método para reemplazar los valores en los campos de la vista datos, por
     * los correspondientes al registro del empleado que se desea modificar
     *
     * @param codigo corresponde al código del empleado
     */
    private void agregarDatos(int codigo) {
        Empleado empleadoTmp = registro.buscarEmpleado(codigo);
        vistaDatos.txtCodigo.setText(String.valueOf(empleadoTmp.getCodigo()));
        vistaDatos.txtRut.setText(empleadoTmp.getRut());
        vistaDatos.txtNombre.setText(empleadoTmp.getNombre());
        vistaDatos.txtApellido.setText(empleadoTmp.getApellido());
        vistaDatos.txtCelular.setText(String.valueOf(empleadoTmp.getCelular()));
        vistaDatos.txtEmail.setText(empleadoTmp.getEmail());
        vistaDatos.txtSueldoBruto.setText(String.valueOf(empleadoTmp.getSueldoBruto()));
        vistaDatos.cboEstadoCivil.setSelectedIndex(fijaEstadoCivil(registro.TransformaEstadoCivil(empleadoTmp.getEstadoCivil())));
        vistaDatos.cboNombreDepartamento.setSelectedItem(empleadoTmp.getNombreDepartamento());
    }

    /**
     * Método para visulizar los datos de la vista mostrar con la información
     * correspondiente a la primera línea de la jTable
     */
    private void agregarMostrar() {
        vistaMostrar.txtCodigo.setText(String.valueOf(vistaMostrar.tbMostrar
                .getValueAt(0, 0)));
        vistaMostrar.txtRut.setText(String.valueOf(vistaMostrar.tbMostrar
                .getValueAt(0, 1)));
        vistaMostrar.txtNombre.setText(String.valueOf(vistaMostrar.tbMostrar
                .getValueAt(0, 2)));
        vistaMostrar.txtApellido.setText(String.valueOf(vistaMostrar.tbMostrar
                .getValueAt(0, 3)));
        vistaMostrar.txtCelular.setText(String.valueOf(vistaMostrar.tbMostrar
                .getValueAt(0, 4)));
        vistaMostrar.txtEmail.setText(String.valueOf(vistaMostrar.tbMostrar
                .getValueAt(0, 5)));
        vistaMostrar.txtSueldoBruto.setText(String.valueOf(vistaMostrar.tbMostrar
                .getValueAt(0, 6)));
        vistaMostrar.cboEstadoCivil.setSelectedIndex(fijaEstadoCivil(
                String.valueOf(vistaMostrar.tbMostrar.getValueAt(0, 7))));
        vistaMostrar.cboNombreDepartamento.setSelectedItem(vistaMostrar.tbMostrar
                .getValueAt(0, 8));
    }

    /**
     * Método para inhabilitar los campos de la vista eliminar
     *
     */
    private void inhabilitarDatos() {
        vistaDatos.txtCodigo.setEditable(false);
        vistaDatos.txtRut.setEditable(false);
        vistaDatos.txtNombre.setEditable(false);
        vistaDatos.txtApellido.setEditable(false);
        vistaDatos.txtCelular.setEditable(false);
        vistaDatos.txtEmail.setEditable(false);
        vistaDatos.txtSueldoBruto.setEditable(false);
        vistaDatos.cboEstadoCivil.setEnabled(false);
        vistaDatos.cboNombreDepartamento.setEnabled(false);
    }

    /**
     * Método para habilitar los campos de la vista eliminar
     *
     */
    private void habilitarDatos() {
        vistaDatos.txtCodigo.setEditable(true);
        vistaDatos.txtRut.setEditable(true);
        vistaDatos.txtNombre.setEditable(true);
        vistaDatos.txtApellido.setEditable(true);
        vistaDatos.txtCelular.setEditable(true);
        vistaDatos.txtEmail.setEditable(true);
        vistaDatos.txtSueldoBruto.setEditable(true);
        vistaDatos.cboEstadoCivil.setEnabled(true);
        vistaDatos.cboNombreDepartamento.setEnabled(true);
    }

    /**
     * Método para ocultar todas las vistas
     *
     */
    private void ocultarVistas() {
        vistaBuscar.setVisible(false);
        vistaDatos.setVisible(false);
        vistaMostrar.setVisible(false);

        vistaBotonAgregar.setVisible(false);
        vistaBotonBuscarEliminar.setVisible(false);
        vistaBotonBuscarModificar.setVisible(false);
        vistaBotonEliminar.setVisible(false);
        vistaBotonMostrar.setVisible(false);
        vistaBotonModificar.setVisible(false);

        vistaTituloAgregar.setVisible(false);
        vistaTituloEliminar.setVisible(false);
        vistaTituloMostrar.setVisible(false);
        vistaTituloModificar.setVisible(false);
    }

    /**
     * Método para solicitar confirmación ante un evento de modificación
     * permanente de la base de datos
     *
     * @param mensaje texto a desplegar
     * @return retorna el valor correspondiente a la opcion elegida
     */
    private int pideConfirmacion(String mensaje) {
        return JOptionPane.showConfirmDialog(null, mensaje + "\n",
                "SE REQUIERE CONFIRMACION", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método para validar que un string sea transformable a entero
     *
     * @param palabra corresponde al dato a validar
     * @return devuelve true si el número es entero, false si no lo es
     */
    private boolean esEntero(String palabra) {
        try {
            Integer.parseInt(palabra);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Método para validar la entrada de numeros enteros en las vistas agregar y
     * modificar
     *
     * @return devuelve true si todos los campos contienen strings
     * transformables a enteros
     */
    private boolean validaNumerosDatos() {
        if (esEntero(vistaDatos.txtCodigo.getText())
                && esEntero(vistaDatos.txtSueldoBruto.getText())
                && esEntero(vistaDatos.txtCelular.getText())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para validar la entrada de numeros enteros en la vista mostrar
     *
     * @return devuelve true si todos los campos contienen strings
     * transformables a enteros
     */
    private boolean validaNumerosMostrar() {
        if (esEntero(vistaMostrar.txtCodigo.getText())
                && esEntero(vistaMostrar.txtSueldoBruto.getText())
                && esEntero(vistaMostrar.txtCelular.getText())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para obtener el valor String del tipo seleccionado
     *
     * @return devuelve el char correspondiente
     */
    private String obtieneEstadoCivil(int index) {
        String tipo = "";
        switch (index) {
            case 0:
                tipo = "C";
                break;
            case 1:
                tipo = "S";
                break;
            case 2:
                tipo = "V";
                break;
        }
        return tipo;
    }

    /**
     * Método para obtener el valor char del color seleccionado
     *
     * @return devuelve el char correspondiente
     */
    private int fijaEstadoCivil(String estadoCivil) {
        int index = -1;
        switch (estadoCivil) {
            case "Casado":
                index = 0;
                break;
            case "Soltero":
                index = 1;
                break;
            case "Viudo":
                index = 2;
                break;
        }
        return index;
    }

    /**
     * Método para informar al usuario sobre el incumplimiento de las reglas de
     * negocio
     */
    private void mensajeReglasNegocio() {
        String texto = "Error en Reglas de Negocio.\n"
                + "Los datos ingresados con cumplen con una o mas de las siguientes Reglas de Negocio:\n"
                + "1.- El código del Empleado desde ser mayor a 0 y menor o igual a 100.\n"
                + "2.- El RUT del Empleado no debe estar en blanco.\n"
                + "3.- El nombre del Empleado no debe estar en blanco.\n"
                + "4.- El apellido del Empleado no debe estar en blanco.\n"
                + "5.- El número del celular debe contener 9 dígitos consecutivos.\n"
                + "6.- El sueldo bruto debe ser mayor o igual a $120.000 (sin signos ni puntuaciones).\n";
        JOptionPane.showMessageDialog(null, texto);
    }
}
