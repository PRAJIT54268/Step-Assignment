public final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0)
            throw new IllegalArgumentException();

        if (delayMinutes == 0)
            return 0.0;

        double fee = Math.min(delayMinutes, 5) * orderValue * 0.005;

        if (delayMinutes > 5)
            fee += Math.min(delayMinutes - 5, 10) * orderValue * 0.01;

        if (delayMinutes > 15)
            fee += (delayMinutes - 15) * orderValue * 0.02;

        fee = Math.max(fee, orderValue * minimumSurgePercent / 100);

        return Math.round(fee * 100) / 100.0;
    }

    public static void main(String[] args) {
        SurgeFeeCalculator s = new SurgeFeeCalculator(1);

        System.out.println(s.calculateSurgeFee(500, 0));
        System.out.println(s.calculateSurgeFee(500, 1));
        System.out.println(s.calculateSurgeFee(500, 16));
    }
}