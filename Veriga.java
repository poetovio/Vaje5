import java.util.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class Veriga {
    public static ArrayList<Blok> blockchain = new ArrayList<Blok>();
    public static int tezavnost = 5;
    public static int zeton = 0;
    public static long generiranjeBlokov = 5000;
    public static long pricakovaniCas = generiranjeBlokov * tezavnost;
    public static ServerSocket serverSocket;
    public static Socket socket;
    public static ArrayList<Socket> povezave = new ArrayList<Socket>();
    public static int port = randomPort();
    public static boolean bulean = true;
    public static DataInputStream input;

    public static void main(String[] args) {
        Gui gui = new Gui();

        gui.gumbPort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int novPort = Integer.parseInt(gui.port.getText());
                    Socket novSocket = new Socket("localhost", novPort);
                    gui.ukazi.append("Povezan na port: " + novPort + '\n');
                    povezave.add(novSocket);
                } catch (Exception error) {
                    error.printStackTrace(System.err);
                    System.err.println(error);
                }
            }
        });

        gui.gumbKreiranjePorta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    serverSocket = new ServerSocket(port);
                    gui.ukazi.append("Socket kreiran na portu: " + port + '\n');
                } catch(Exception error) {
                    error.printStackTrace(System.err);
                    System.err.println(error);
                }
            }
        });

        gui.mine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blockchain.add(new Blok("Blok1", "0", 0, tezavnost));
                Blok.rudari(blockchain.get(0), tezavnost, gui);
                bulean = false;
            }
        });

        while(bulean) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace(System.err);
                System.err.println(e);
            }
        }

        rudarjenje(gui, povezave);
        /*

        blockchain.add(new Blok("Prvi blok", "0", 0, tezavnost));
        Blok.rudari(blockchain.get(0), tezavnost, gui);        

        blockchain.add(new Blok("Drugi blok", blockchain.get(blockchain.size() - 1).hash, 1, tezavnost));
        Blok.rudari(blockchain.get(1), tezavnost, gui);

        Date d1 = new Date(blockchain.get(0).datum);
        Date d2 = new Date(blockchain.get(1).datum);
        tezavnost += izracunajTezavnost(d2.getTime() - d1.getTime());
        System.out.println("Tezavnost: " + tezavnost);

        blockchain.add(new Blok("Tretji blok", blockchain.get(blockchain.size() - 1).hash, 2, tezavnost));
        Blok.rudari(blockchain.get(2), tezavnost, gui);

        for(Blok blok: blockchain) {
            Date date = new Date(blok.datum);
            System.out.println("Timestamp: " + date);
        }

 
        Date d3 = new Date(blockchain.get(2).datum);
        tezavnost += izracunajTezavnost(d3.getTime() - d2.getTime());
        System.out.println("Tezavnost: " + tezavnost);

        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);

        System.out.println((d2.getTime() - d1.getTime()) / 1000.0);
        System.out.println((d3.getTime() - d2.getTime()) / 1000.0);

        System.out.println("Veriga je veljavna: " + preveritev());
        */
    }

    public static Boolean preveritev() {
        Blok prejsnji = null;
        Blok zdajsnji = null;

        for(int i = 1; i < blockchain.size(); i++) {
            zdajsnji = blockchain.get(i);
            prejsnji = blockchain.get(i - 1);

            if(!((zdajsnji.indeks - 1) == prejsnji.indeks) || !((zdajsnji.prevHash).equals(prejsnji.hash)) || !((zdajsnji.hash).equals(Blok.vrniHash(zdajsnji.indeks, zdajsnji.datum, zdajsnji.glavniZeton , zdajsnji.data, zdajsnji.prevHash, zdajsnji.tezavnost)))) {
                return false;
            }
        }

        return true;
    }

    public static int izracunajTezavnost(long cas) {   
        if(cas < (pricakovaniCas / 2)) {
            return 1;
        } else if(cas > (pricakovaniCas * 2)) {
            return -1;
        }
        return 0;
    }

    public static int randomPort() {
        Random random = new Random(System.currentTimeMillis());
        return ((random.nextInt(2) + 1) * 10000 + random.nextInt(10000));
    }

    public static void rudarjenje(Gui gui, ArrayList<Socket> sockets) {
        int stevec = 1;
        while(true) {
            blockchain.add(new Blok("Blok" + Integer.valueOf(stevec + 1), blockchain.get(blockchain.size() - 1).hash, stevec, tezavnost));
            Blok.rudari(blockchain.get(blockchain.size() - 1), tezavnost, gui);

            if(preveritev()) {
                Date d1 = new Date(blockchain.get(blockchain.size() - 2).datum);
                Date d2 = new Date(blockchain.get(blockchain.size() - 1).datum);
                tezavnost += izracunajTezavnost(d2.getTime() - d1.getTime());
                stevec++;
                gui.ukazi.append("Kumulativna tezavnost: " + kumulativnaTezavnost() + '\n');
            } else {
                blockchain.remove(blockchain.size() - 1);
            }
        }
    }

    public static int kumulativnaTezavnost() {
        int sestevek = 0;
        for(Blok block: blockchain) {
            sestevek += Math.pow(2, block.tezavnost);
        }
        return sestevek;
    }
}
