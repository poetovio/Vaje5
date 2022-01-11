import java.util.*;

public class Veriga {
    public static ArrayList<Blok> blockchain = new ArrayList<Blok>();
    public static int tezavnost = 5;
    public static int zeton = 0;
    public static long generiranjeBlokov = 5000;
    public static long pricakovaniCas = generiranjeBlokov * tezavnost;

    public static void main(String[] args) {
        Gui gui = new Gui();

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
}
