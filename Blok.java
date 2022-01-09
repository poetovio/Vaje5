import java.util.Date;
import java.security.MessageDigest;

public class Blok {
    public String hash;
    private String prevHash;
    private String data;
    private int indeks;
    private long datum;

    public Blok(String data, String prevHash, int indeks) {
        this.data = data;
        this.hash = izracunajHash();
        this.prevHash = prevHash;
        this.indeks = indeks;
        this.datum = new Date().getTime();
    }

    public String izracunajHash() {
        String koda = hash(String.valueOf(indeks) + Long.toString(datum) + data + prevHash);
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
}