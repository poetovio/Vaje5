import java.util.*;

public class Veriga {
    public static ArrayList<Blok> blockchain = new ArrayList<Blok>();
    public static int tezavnost = 4;
    
    public static void main(String[] args) {
        blockchain.add(new Blok("Prvi blok", "0", 0));
        blockchain.get(0).rudari(tezavnost);

        blockchain.add(new Blok("Drugi blok", blockchain.get(blockchain.size() - 1).hash, 1));
        blockchain.get(1).rudari(tezavnost);

        blockchain.add(new Blok("Tretji blok", blockchain.get(blockchain.size() - 1).hash, 2));
        blockchain.get(2).rudari(tezavnost);

        System.out.println("Veriga je veljavna: " + preveritev());
    }

    public static Boolean preveritev() {
        Blok prejsnji = null;
        Blok zdajsnji = null;

        for(int i = 1; i < blockchain.size(); i++) {
            zdajsnji = blockchain.get(i);
            prejsnji = blockchain.get(i - 1);

            if(!((zdajsnji.indeks - 1) == prejsnji.indeks) || !((zdajsnji.prevHash).equals(prejsnji.hash)) || !((zdajsnji.hash).equals(Blok.vrniHash(zdajsnji.indeks, zdajsnji.datum, zdajsnji.data, zdajsnji.prevHash)))) {
                return false;
            }
        }

        return true;
    }
}
