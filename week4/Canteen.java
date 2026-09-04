public class Canteen {
    private final String canteenCode, canteenName;
    private final int trustScore;

    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    public int compareTo(Canteen other) {
        if (trustScore != other.trustScore)
            return Integer.compare(other.trustScore, trustScore);

        int x = canteenCode.compareToIgnoreCase(other.canteenCode);

        if (x != 0)
            return x;

        return Integer.compare(canteenName.length(), other.canteenName.length());
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {
        for (int i = 0; i < canteens.length - 1; i++)
            for (int j = 0; j < canteens.length - i - 1; j++)
                if (canteens[j].compareTo(canteens[j + 1]) > 0) {
                    Canteen t = canteens[j];
                    canteens[j] = canteens[j + 1];
                    canteens[j + 1] = t;
                }

        return canteens;
    }

    public static void main(String[] args) {
        Canteen[] a = {
            new Canteen("HB3-C", "Spice Junction", 3),
            new Canteen("hb1-c", "Grand Mess", 5),
            new Canteen("HB2-C", "Southern Treats")
        };

        rankCanteens(a);

        for (Canteen c : a)
            System.out.println(c.canteenCode);
    }
}