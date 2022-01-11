import java.util.*;

public class Veriga {
    public static ArrayList<Blok> blockchain = new ArrayList<Blok>();
    public static int tezavnost = 6;
    public static int zeton = 0;
    
    public static void main(String[] args) {
        blockchain.add(new Blok("Prvi blok", "0", 0, tezavnost));
        Blok.rudari(blockchain.get(0), tezavnost);

        blockchain.add(new Blok("Drugi blok", blockchain.get(blockchain.size() - 1).hash, 1, tezavnost));
        Blok.rudari(blockchain.get(1), tezavnost);

        blockchain.add(new Blok("Tretji blok", blockchain.get(blockchain.size() - 1).hash, 2, tezavnost));
        Blok.rudari(blockchain.get(2), tezavnost);

        for(Blok blok: blockchain) {
            Date date = new Date(blok.datum);
            System.out.println("Timestamp: " + date);
        }

        Date d1 = new Date(blockchain.get(0).datum);
        Date d2 = new Date(blockchain.get(1).datum); 
        Date d3 = new Date(blockchain.get(2).datum);

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

    public static int izracunajTezavnost(Blok prejsnjiBlok, Blok zdajsnjiBlok) {


        return 0;
    }
}
