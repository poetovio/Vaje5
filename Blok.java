import java.util.Date;
import java.security.MessageDigest;

public class Blok {
    public String hash;
    public String prevHash;
    public String data;
    public int indeks;
    public long datum;
    public int zeton;
    public int tezavnost;

    public Blok(String data, String prevHash, int indeks, int tezavnost, int zeton) {
        this.data = data;
        this.hash = izracunajHash(zeton);
        this.prevHash = prevHash;
        this.indeks = indeks;
        this.tezavnost = tezavnost;
        this.zeton = zeton;
        this.datum = new Date().getTime();
    }

    public String izracunajHash(int token) {
        String koda = hash(String.valueOf(indeks) + Long.toString(datum) + token + data + prevHash + tezavnost);
        return koda;
    }

    public static String vrniHash(int indeks, long datum, int zeton, String data, String prevHash, int tezavnost) {
        String koda = hash(String.valueOf(indeks) + Long.toString(datum) + Integer.toString(zeton) + data + prevHash + tezavnost);
        return koda;
    }

    public static String hash(String besedilo) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");

            byte[] koda = sha.digest(besedilo.getBytes("UTF-8"));
            StringBuffer hex = new StringBuffer();

            for(int i = 0; i < koda.length; i++) {
                String del = Integer.toHexString(0xff & koda[i]);

                if(del.length() == 1) {
                    hex.append('0');
                }
                hex.append(del);
            }
            
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void rudari(int tezavnost) {
        String vrednost = new String(new char[tezavnost]).replace('\0', '0');

        /*
        while(!hash.substring(0, tezavnost).equals(vrednost)) {
            zeton++;
            hash = izracunajHash();
        }
        */

        int zeton = 0;

        while(true) {
            if(hash.substring(0, tezavnost).equals(vrednost)) {
                System.out.println("Blok najden! ~ " + hash);
                break;
            } else {
                zeton++;
                hash = izracunajHash(zeton);
            }
        }

        System.out.println("Success! ~ " + hash);
    }
}