import javax.swing.*;
import java.awt.*;

public class Gui {
    public JFrame frame;
    public static JPanel opravilnaVrstica;
    public JTextField port;
    public JTextArea vnosnoPolje;
    public static JPanel sredina;
    public JButton gumbPort;
    public static JPanel command;
    public JTextArea ukazi;
    public JButton gumbKreiranjePorta;
    public JButton mine;
    public JScrollPane scroll;

    public Gui() {
        frame = new JFrame("Blockchain");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        opravilnaVrstica = new JPanel();
        JLabel labelPort = new JLabel("Port: ");
        port = new JTextField(15);
        gumbPort = new JButton("Connect");
        gumbKreiranjePorta = new JButton("Port");
        mine = new JButton("Mine");
        opravilnaVrstica.add(labelPort);
        opravilnaVrstica.add(port);
        opravilnaVrstica.add(gumbPort);
        opravilnaVrstica.add(gumbKreiranjePorta);
        opravilnaVrstica.add(mine);

        sredina = new JPanel();
        sredina.setLayout(new BoxLayout(sredina, BoxLayout.Y_AXIS));
        vnosnoPolje = new JTextArea();
        scroll = new JScrollPane(vnosnoPolje, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sredina.add(scroll);

        command = new JPanel();
        command.setPreferredSize(new Dimension(250, 300));
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

    public void izpisi(String text) {
        vnosnoPolje.append(text);
        frame.validate();
        frame.repaint();
    }
}

