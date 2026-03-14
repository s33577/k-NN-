import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        double[] test = new double[] {1, 3, 4, 5};
        double[] test2 = new double[] {9, 2, 6, 1};
        System.out.println(distance(test, test2));
        System.out.println(readData("/Users/and/Desktop/k-NN-/K-NN-JavaFile/src/testData/iris.data"));
    }

    static double distance(double[] a, double[] b) {
        double sum = 0;

        for (int i = 0; i < a.length; i++) {
            double difference = a[i] - b[i];
            sum += difference * difference;
        }
        return Math.sqrt(sum);
    }

    static List<Data> readData(String filename) {
        List<Data> data = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while( (line = br.readLine()) != null) {
                String[] parts = line.split(",");
                double[] features = new double[parts.length - 1];
                for (int i = 0; i < parts.length - 1; i++) {
                    features[i] = Double.parseDouble(parts[i]);
                }
                String label = parts[parts.length - 1];
                data.add(new Data(features, label));

            }
            br.close();
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
