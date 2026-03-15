import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println("Usage: java Main <k> <train file> <test file>");
        }
        int k = Integer.parseInt(args[0]);
        String trainFile = args[1];
        String testFile = args[2];

        List<Point> train = readPoint(trainFile);
        List<Point> test = readPoint(testFile);


        double accuracy = testAcc(train, test, k);
        System.out.println("Accuracy: " + accuracy);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter vectors (comma separated): ");

        while (true) {

            String input = sc.nextLine();

            if (input.equalsIgnoreCase("exit"))
                break;

            String[] parts = input.split(",");
            double[] features = new double[parts.length];

            for (int i = 0; i < parts.length; i++) {
                features[i] = Double.parseDouble(parts[i]);
            }

            Point newPoint = new Point(features, "");
            String prediction = classifier(newPoint, train, k);

            System.out.println("Predicted class: " + prediction);
        }

        sc.close();
    }

    static double distance(double[] a, double[] b) {
        double sum = 0;

        for (int i = 0; i < a.length; i++) {
            double difference = a[i] - b[i];
            sum += difference * difference;
        }
        return Math.sqrt(sum);
    }

    static List<Point> readPoint(String filename) {
        List<Point> Point = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while( (line = br.readLine()) != null) {
                String[] parts = line.split(",");
                double[] features = new double[parts.length - 1];
                for (int i = 0; i < parts.length - 1; i++) {
                    features[i] = Double.parseDouble(parts[i]);
                }
                String label = parts[parts.length - 1];
                Point.add(new Point(features, label));

            }
            br.close();
            return Point;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static String classifier(Point test, List<Point> trainSet, int k) {
        List<Neigbor> neighbors = new ArrayList<>();

        for (Point d : trainSet) {
            double distance = distance(test.features, d.features);
            neighbors.add(new Neigbor(distance, d.labels));

        }
        neighbors.sort(Comparator.comparingDouble(n -> n.distance));



        Map<String, Integer> labels = new HashMap<>();
        for (int i = 0; i < k; i++) {
            String label = neighbors.get(i).labels;
             labels.put(label, labels.getOrDefault(label, 0) + 1);
        }

        return Collections.max(labels.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    static double testAcc(List<Point> train, List<Point> test, int k) {
        int correct = 0;
        for (Point d : test) {
            String predicted = classifier(d, train, k);
            if (predicted.equals(d.labels)) {
                correct++;
            }
        }
        return (double) correct / test.size();
    }
}
