import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Assign value to knn: ");
        int k = Integer.parseInt(sc.nextLine());

        System.out.print("(1) test your own vector. (2) test a set of vectors: ");
        String choice = sc.nextLine();

        if (choice.equals("1")) {
            System.out.print("Enter value to test your own vector: ");
            String[] parts = sc.nextLine().split(",");
            double[] testVector = new double[parts.length];
            for (int i = 0; i < parts.length; i++) {
                testVector[i] = Double.parseDouble(parts[i]);
            }


            System.out.print("Provide a training file: ");
            String trainFile = sc.nextLine();

            String result = calculateDistance(testVector, trainFile, k);
            System.out.println("Predicted: " + result);
        } else if (choice.equals("2")) {
            System.out.print("Provide a file to test: ");
            String testFile = sc.nextLine();

            System.out.print("Provide a training file: ");
            String trainFile = sc.nextLine();

            BufferedReader testReader = new BufferedReader(new FileReader(testFile));

            int correct = 0;
            int total = 0;

            String line;
            while ((line = testReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 2) {
                    continue;
                }

                double[] features = new double[parts.length - 1];
                for (int i = 0; i < parts.length - 1; i++) {
                    features[i] = Double.parseDouble(parts[i]);
                }

                String actualLabel = parts[parts.length - 1];
                String predicted = calculateDistance(features, trainFile, k);
                System.out.println("\nchecking vector: " + Arrays.toString(parts));
                System.out.println("Predicted: " + predicted);
                if (predicted.equals(actualLabel)) {
                    System.out.println("Occurency");
                    correct++;
                }
                total++;
            }
            testReader.close();
            System.out.println("Correct answer: " + correct);
            System.out.println("Accuracy: " + (double) correct / (double) total);
        }
        sc.close();
    }
    // Distance uses Euclidean distance tutorial 2. sqrt((X1 - Y1)^2 + (X2 - Y2)^2...)
    static String calculateDistance(double[] testVector, String trainFile, int k) throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader(trainFile));


        Map<String, String> smallest = new HashMap<>();

        int vectorId = 0;
        String line;

        while ((line = reader.readLine()) != null) {

            String[] parts = line.split(",");
            if (parts.length < 2) continue;

            double distance = 0.0;


            for (int i = 0; i < parts.length - 1; i++) {
                double val = Double.parseDouble(parts[i]);
                double diff = testVector[i] - val;
                distance += diff * diff;
            }

            String label = parts[parts.length - 1];

            String key = distance + "_" + vectorId;

            if (smallest.size() < k) {
                smallest.put(key, label);
            } else {

                // find largest stored distance
                String maxKey = null;
                double maxDist = -1;

                for (String kkey : smallest.keySet()) {
                    double d = Double.parseDouble(kkey.split("_")[0]);
                    if (d > maxDist) {
                        maxDist = d;
                        maxKey = kkey;
                    }
                }

                if (distance < maxDist) {
                    smallest.remove(maxKey);
                    smallest.put(key, label);
                }
            }

            vectorId++;
        }

        reader.close();

        // voting
        Map<String, Integer> count = new HashMap<>();

        for (String label : smallest.values()) {
            count.put(label, count.getOrDefault(label, 0) + 1);
        }

        // find winner
        String winner = null;
        int max = -1;

        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                winner = entry.getKey();
            }
        }

        return winner;
    }
}
