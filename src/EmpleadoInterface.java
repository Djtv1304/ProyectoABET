import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EmpleadoInterface extends JFrame{
    private JTabbedPane tabbedPane1;
    public JPanel panelPrincipal;
    private JTextField textFieldNombre;
    private JTextField textFieldSueldo;
    private JTextField textFieldCedula;
    private JTextField textFieldTelf;
    private JButton registrarNuevoEmpleadoButton;
    private JTabbedPane tabbedPane2;
    private JTextArea textAreaListaNombres;
    private JTextArea textAreaListaCompleta;
    private JButton imprimirLaListaDeButton;
    private JButton imprimirLaListaCompletaButton;
    private JTextField textFieldNewNombre;
    private JTextField textFieldNewSueldo;
    private JTextField textFieldNewCedula;
    private JTextField textFieldNewTelf;
    private JButton ModificarDatosEmpleado;
    private JTabbedPane tabbedPane3;
    private JTextArea textABuscarCedula;
    private JTextField textFieldCedulaBusqueda;
    private JButton buscarPorCedulaButton;
    private JTextArea textAbuscarNombre;
    private JTextField textFieldNombreBusqueda;
    private JButton buscarPorNombreButton;
    private JButton guardarLaInformacionActualButton;
    private EmpleadoController Controller;
    public EmpleadoInterface() {

        Controller = new EmpleadoController();

        registrarNuevoEmpleadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    boolean validacioTelf = validarTelfEcuatoriano(textFieldTelf);
                    boolean validacionCedula = validarCedulaEcuatoriana(textFieldCedula);

                    if(validacioTelf && validacionCedula){
                        Empleado NuevoEmpleado = new Empleado(textFieldNombre.getText(),Double.parseDouble(textFieldSueldo.getText()),textFieldCedula.getText(),textFieldTelf.getText());

                        boolean estado = Controller.AgregarEmpleados(NuevoEmpleado);

                        if (estado) {
                            JOptionPane.showMessageDialog(null,"Se registró exitosamente al empleado");
                            limpiarCampos();
                        } else {
                            JOptionPane.showMessageDialog(null,"Hubo un error al registrar el empleado, verifique la información ingresada.");
                        }
                    }
                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null,"Error en tiempo de ejecución controlado.\nVerifique la información ingresada.");
                }

            }
        });

        ModificarDatosEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean validacioTelf = validarTelfEcuatoriano(textFieldNewTelf);
                boolean validacionCedula = validarCedulaEcuatoriana(textFieldNewCedula);

                if(validacioTelf && validacionCedula) {
                    String cedulaParaBuscar = JOptionPane.showInputDialog("Ingrese la cédula del empleado a reemplazar: ");
                    try {
                        boolean estado = Controller.ModificarDatosEmpleado(cedulaParaBuscar, textFieldNewNombre.getText(), Double.parseDouble(textFieldNewSueldo.getText()), textFieldNewCedula.getText(), textFieldNewTelf.getText());
                        if (estado) {
                            JOptionPane.showMessageDialog(null, "El cambio se realizó exitosamente");
                            limpiarCampos();
                        } else {
                            JOptionPane.showMessageDialog(null, "El empleado que busca no existe o compruebe la cedula nueva");
                        }
                    } catch (Exception x) {
                        JOptionPane.showMessageDialog(null, "Error en tiempo de ejecución controlado.\nVerifique la información ingresada.");
                    }
                }
            }
        });

        imprimirLaListaDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    StringBuilder texto = Controller.ListadoEmpleados();
                    textAreaListaNombres.setText(String.valueOf(texto));
                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null,"Error en tiempo de ejecución controlado.\nVerifique la información ingresada.");
                }
            }
        });

        imprimirLaListaCompletaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    StringBuilder texto = Controller.ListadoEmpleadosCompleta();
                    textAreaListaCompleta.setText(String.valueOf(texto));
                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null,"Error en tiempo de ejecución controlado.\nVerifique la información ingresada.");
                }
            }
        });

        textFieldNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validacionLetras(e);
            }
        });
        textFieldSueldo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validacionNumeros(e);
            }
        });
        textFieldCedula.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validacionNumeros(e);
            }
        });
        textFieldTelf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validacionNumeros(e);
            }
        });
        textFieldNewNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validacionLetras(e);
            }
        });
        textFieldNewSueldo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validacionNumeros(e);
            }
        });
        textFieldNewCedula.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validacionNumeros(e);
            }
        });
        textFieldNewTelf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validacionNumeros(e);
            }
        });
        textFieldCedulaBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validacionNumeros(e);
            }
        });
        textFieldNombreBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                validacionLetras(e);
            }
        });

        buscarPorCedulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Empleado employee = Controller.buscarEmpleadoPorCedula(textFieldCedulaBusqueda.getText());
                    if (employee == null) {
                        JOptionPane.showMessageDialog(null,"No se ha encontrado al empleado, verifique la información");
                    } else {
                        textABuscarCedula.setText("-\tInformación del empleado a buscar:\n"+employee.toString());
                    }
                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null,"Error en tiempo de ejecución controlado.\nVerifique la información ingresada.");
                }

            }
        });


        buscarPorNombreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Empleado employee = Controller.buscarEmpleadoPorNombre(textFieldNombreBusqueda.getText());
                    if (employee == null) {
                        JOptionPane.showMessageDialog(null,"No se ha encontrado al empleado, verifique la información");
                    } else {
                        textAbuscarNombre.setText("-\tInformación del empleado a buscar:\n"+employee.toString());
                    }
                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null,"Error en tiempo de ejecución controlado.\nVerifique la información ingresada.");
                }
            }
        });

        guardarLaInformacionActualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String ruta = JOptionPane.showInputDialog("Ingrese la ruta en donde desea que se guarde el archivo.\nEjemplo de ruta:\nC:/Users/TuUsuario/Desktop/nombreArchivo.txt");
                    if (!ruta.equals("")) {
                        Controller.guardarEmpleadosEnArchivo(ruta);

                    }
                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null,"Error en tiempo de ejecución controlado.\nVerifique la información ingresada.");
                }

            }
        });
    }

    public void validacionLetras(java.awt.event.KeyEvent evt) {
        char validar = evt.getKeyChar();
        if (Character.isDigit(validar) ){
            getToolkit ().beep();
            evt.consume ();
            JOptionPane.showMessageDialog(rootPane, "Ingresar solo letras");
        }
    }
    public void validacionNumeros(java.awt.event.KeyEvent evt) {
        char validar=evt.getKeyChar();
        if (Character.isLetter(validar) || validar == '-'){
            getToolkit ().beep();
            evt.consume ();
            JOptionPane.showMessageDialog(rootPane, "Ingresar solo valores válidos");
        }
    }

    public boolean validarCedulaEcuatoriana(JTextField FieldParaValidar) {
        String cedulaComparar;
        String validarCedula = FieldParaValidar.getText(); //Cedula a verificar
        boolean decision = false;
        boolean estado = false;


        if ( validarCedula.length() == 10) { //R1
            //
            if ((Integer.parseInt(validarCedula.substring(0, 2)) <= 24) || (Integer.parseInt(validarCedula.substring(0, 2)) == 30)){
                int coeficientes [] = {2,1,2,1,2,1,2,1,2};
                int digitoVerificador = Integer.parseInt(validarCedula.substring(validarCedula.length()-1, validarCedula.length()));
                int suma = 0;
                int digitoXcoeficiente = 0;
                int modulo = 0;
                //R4
                for (int i = 0;i < validarCedula.length()-1;i++){
                    //P1
                    digitoXcoeficiente = Integer.parseInt(validarCedula.substring(i, i+1)) * coeficientes[i]; //Obtiene digito por digito
                    //P2
                    digitoXcoeficiente = (digitoXcoeficiente > 9) ? digitoXcoeficiente -= 9 : digitoXcoeficiente; //En caso de que la multiplicacion sea mayora 9
                    //P3
                    suma += digitoXcoeficiente; //Obtiene la suma de digitos
                }
                //P4
                modulo = suma%10;
                //P5
                if ((10-modulo) == digitoVerificador){
                    estado = true;
                }
                if ( modulo == 0 && modulo == digitoVerificador){
                    estado = true;
                }
            } else { //FIN R3
                estado = false;
                JOptionPane.showMessageDialog(null, "La cédula no pertenece a Ecuador.");
            }
        } else { //FIN R1
            estado = false;
            JOptionPane.showMessageDialog(null, "La cédula tiene menos o mas de 10 caracteres."); //R1
        }

        return estado;
    }

    public boolean validarTelfEcuatoriano(JTextField FieldParaValidarTelf) {

        String validarTelefono = new String(FieldParaValidarTelf.getText());
        boolean telfValido = validarTelefono.matches("[\\+](593 9)\\d{1}[\\s]\\d{3}[\\s]\\d{4}");
        if(!telfValido){
            JOptionPane.showMessageDialog(null, "Ingrese un número válido\n Ejem: +593 99 999 9999");
        }

        return telfValido;
    }

    public void limpiarCampos(){
        textFieldNombre.setText("");
        textFieldSueldo.setText("");
        textFieldTelf.setText("");
        textFieldCedula.setText("");

        textFieldNewNombre.setText("");
        textFieldNewSueldo.setText("");
        textFieldNewTelf.setText("");
        textFieldNewCedula.setText("");

    }
}
