import javax.swing.*;
import java.awt.*;

public class Gui {
    public JFrame frame;
    public static JPanel opravilnaVrstica;
    public static JTextField port;
    public JTextArea vnosnoPolje;
    public static JPanel sredina;
    public static JButton gumbPort;
    public static JPanel command;
    public static JTextArea ukazi;

    public Gui() {
        frame = new JFrame("Blockchain");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        opravilnaVrstica = new JPanel();
        JLabel labelPort = new JLabel("Port: ");
        port = new JTextField(15);
        gumbPort = new JButton("Povezi se");
        opravilnaVrstica.add(labelPort);
        opravilnaVrstica.add(port);
        opravilnaVrstica.add(gumbPort);

        sredina = new JPanel();
        sredina.setLayout(new BoxLayout(sredina, BoxLayout.Y_AXIS));
        vnosnoPolje = new JTextArea();
        sredina.add(vnosnoPolje);

        command = new JPanel();
        command.setPreferredSize(new Dimension(100, 300));
        command.setBackground(new Color(176, 224, 230));
        JLabel ukazVrstica = new JLabel("Ukazi:");
        ukazi = new JTextArea();
        ukazi.setBackground(new Color(176, 224, 230));
        command.setLayout(new BoxLayout(command, BoxLayout.Y_AXIS));
        command.add(ukazVrstica);
        command.add(ukazi);

        frame.getContentPane().add(BorderLayout.NORTH, opravilnaVrstica);
        frame.getContentPane().add(BorderLayout.CENTER, sredina);
        frame.getContentPane().add(BorderLayout.EAST, command);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Gui gui = new Gui();
    }
}

