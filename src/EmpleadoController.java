import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Stack;

public class EmpleadoController {
    private Stack<Empleado> pilaEmpleados;

    public EmpleadoController() {
        pilaEmpleados = new Stack<Empleado>();
    }
    Stack<String> prueba = new Stack();

    public boolean AgregarEmpleados(Empleado EmpleadoNuevo) {
        Empleado x = buscarEmpleadoPorCedula(EmpleadoNuevo.getCedula());
        if ( x == null) {
            pilaEmpleados.add(EmpleadoNuevo);
            return true;
        }

        return false;
    }

    public Empleado buscarEmpleadoPorNombre(String nombreBuscado) {
        int izquierda = 0;
        int derecha = pilaEmpleados.size() - 1;

        while (izquierda <= derecha) {
            int medio = (izquierda + derecha) / 2;
            pilaEmpleados.sort(Comparator.comparing(Empleado::getNombres)); //Ordeno la pila
            int comparacion = nombreBuscado.compareTo(pilaEmpleados.elementAt(medio).getNombres());

            if (comparacion == 0) {
                return pilaEmpleados.elementAt(medio);

            } else if (comparacion < 0) {
                derecha = medio - 1;
            } else {
                // La cédula buscada está después que el elemento medio
                izquierda = medio + 1;
            }
        }
        return null;
    }

    public Empleado buscarEmpleadoPorCedula(String cedulaBuscada) {
        int izquierda = 0;
        int derecha = pilaEmpleados.size() - 1;

        while (izquierda <= derecha) {
            int medio = (izquierda + derecha) / 2;
            //(s1, s2) -> s1.getCedula().compareTo(s2.getCedula())
            pilaEmpleados.sort(Comparator.comparing(Empleado::getCedula));
            int comparacion = cedulaBuscada.compareTo(pilaEmpleados.elementAt(medio).getCedula());

            if (comparacion == 0) {
                return pilaEmpleados.elementAt(medio);

            } else if (comparacion < 0) {
                derecha = medio - 1;
            } else {
                // La cédula buscada está después que el elemento medio
                izquierda = medio + 1;
            }
        }
        return null;
    }

    public boolean ModificarDatosEmpleado(String CedulaBuscada, String newNombre, double newSueldo, String newCedula, String newTelefono) {
        Empleado x = buscarEmpleadoPorCedula(newCedula); //Tu cedula
        if ( x == null) {
            for (Empleado e : pilaEmpleados) {
                if (e.getCedula().equals(CedulaBuscada)) {
                    e.setCedula(newCedula);
                    e.setSueldoMensual(newSueldo);
                    e.setNombres(newNombre);
                    e.setTelefono(newTelefono);
                    return true;
                }
            }
        }
        return false;
    }

    public StringBuilder ListadoEmpleados () {

        StringBuilder sb = new StringBuilder();
        sb.append("-\tLista de nombres los empleados\n");

        for (Empleado e : pilaEmpleados) {
            sb.append("Nombre: ").append(e.getNombres()).append("\n");
        }

        return sb;
    }

    public StringBuilder ListadoEmpleadosCompleta () {

        StringBuilder sb = new StringBuilder();
        sb.append("-\tLista completa de los empleados\n");

        for (Empleado e : pilaEmpleados) {
            sb.append(e.toString());
        }

        return sb;
    }

    public void guardarEmpleadosEnArchivo(String nombreArchivo) {
        Stack<Empleado> CloneStack = pilaEmpleados;
        try {
            FileWriter writer = new FileWriter(nombreArchivo);
            while (!CloneStack.isEmpty()) {
                Empleado empleado = CloneStack.pop();
                writer.write(empleado.toString());
            }
            writer.close();
            JOptionPane.showMessageDialog(null,"Se ha creado el archivo correctamente");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error al guardar los empleados en el archivo: " + e.getMessage());
        }
    }


}
