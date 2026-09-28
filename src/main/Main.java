import view.VentanaSpeedFast;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            VentanaSpeedFast ventana =
                    new VentanaSpeedFast();

            ventana.setVisible(true);
        });
    }
}