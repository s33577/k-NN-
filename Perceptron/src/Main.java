public class Main {
    public static void main(String[] args) {
        double[] w = {2,-1,4,1};

        Perceptron perceptron = new Perceptron(w, 3);

        double[] input = {7,-2,-5,4};
        System.out.println(perceptron.predict(input));

    }
}

class Perceptron {
    double[] weights;
    double bias;

    Perceptron(double[] weights, double bias) {
        this.weights = weights;
        this.bias = bias;
    }
    int predict(double[] input) {
        for (int i = 0; i < weights.length; i++) {
            double net = (weights[i]*input[i])+bias;
            if (net >= bias) {
                return 1;
            }

        }
        return 0;
    }
}