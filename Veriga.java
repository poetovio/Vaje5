import java.util.*;

public class Veriga {
    public static ArrayList<Blok> blockchain = new ArrayList<Blok>();
    public static int tezavnost = 5;
    public static int zeton = 0;
    
    public static void main(String[] args) {
        blockchain.add(new Blok("Prvi blok", "0", 0, tezavnost));
        Blok.rudari(blockchain.get(0), tezavnost);

        blockchain.add(new Blok("Drugi blok", blockchain.get(blockchain.size() - 1).hash, 1, tezavnost));
        Blok.rudari(blockchain.get(1), tezavnost);

        blockchain.add(new Blok("Tretji blok", blockchain.get(blockchain.size() - 1).hash, 2, tezavnost));
        Blok.rudari(blockchain.get(2), tezavnost);

        System.out.println("Veriga je veljavna: " + preveritev());
    }

    public static Boolean preveritev() {
        Blok prejsnji = null;
        Blok zdajsnji = null;

        for(int i = 1; i < blockchain.size(); i++) {
            zdajsnji = blockchain.get(i);
            prejsnji = blockchain.get(i - 1);

            if(!((zdajsnji.indeks - 1) == prejsnji.indeks) || !((zdajsnji.prevHash).equals(prejsnji.hash)) /* || !((zdajsnji.hash).equals(Blok.vrniHash(zdajsnji.indeks, zdajsnji.datum, 0 , zdajsnji.data, zdajsnji.prevHash, zdajsnji.tezavnost))) */) {
                return false;
            }
        }

        return true;
    }
}
