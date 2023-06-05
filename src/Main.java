import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        EmpleadoInterface Interface = new EmpleadoInterface();
        Interface.setBounds(100,100,600,400);
        Interface.setContentPane(Interface.panelPrincipal);
        Interface.setVisible(true);
        Interface.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}