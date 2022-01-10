import java.util.Date;
import java.security.MessageDigest;

public class Blok {
    public String hash;
    public String prevHash;
    public String data;
    public int indeks;
    public long datum;
    public int tezavnost;
    public int glavniZeton;

    public Blok(String data, String prevHash, int indeks, int tezavnost) {
        this.data = data;
        this.hash = izracunajHash();
        this.prevHash = prevHash;
        this.indeks = indeks;
        this.tezavnost = tezavnost;
        this.glavniZeton = 0;
        this.datum = new Date().getTime();
    }

    public String izracunajHash() {
        String koda = hash(String.valueOf(indeks) + Long.toString(datum) + data + prevHash + tezavnost);
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

    public static void rudari(Blok blok, int tezavnost) {
        String vrednost = new String(new char[tezavnost]).replace('\0', '0');

        int zeton = 0;

        while(true) {
            if(blok.hash.substring(0, tezavnost).equals(vrednost)) {
                System.out.println("Blok najden! ~ " + blok.hash);
                blok.glavniZeton = zeton;
                break;
            } else {
                zeton++;
                blok.hash = vrniHash(blok.indeks, blok.datum, zeton, blok.data, blok.prevHash, blok.tezavnost);
            }
        }
    }

    public int vrniZeton() {
        return glavniZeton;
    }
}