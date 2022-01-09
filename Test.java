public class Test {

    public static void main(String[] args) {

        Blok blok1 = new Blok("Prvi blok", "0", 0);
        Blok blok2 = new Blok("Drugi blok", blok1.hash, 1);
        Blok blok3 = new Blok("Tretji blok", blok2.hash, 2);

        System.out.println("Prvi blok hash: " + blok1.hash);
        System.out.println("Drugi blok hash: " + blok2.hash);
        System.out.println("Tretji blok hash: " + blok3.hash);
    }
}