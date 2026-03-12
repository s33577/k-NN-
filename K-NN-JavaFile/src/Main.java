public class Main {

    public static void main(String[] args) {
        double[] test = new double[] {1, 3, 4, 5};
        double[] test2 = new double[] {9, 2, 6, 1};
        System.out.println(distance(test, test2));



    }

    static double distance(double[] a, double[] b) {
        double sum = 0;

        for (int i = 0; i < a.length; i++) {
            double difference = a[i] - b[i];
            sum += difference * difference;
        }
        return Math.sqrt(sum);
    }


}
