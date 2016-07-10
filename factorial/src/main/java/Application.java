import java.math.BigInteger;

public class Application {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Expected one argument. Exiting");
            System.exit(1);
        }

        String input = args[0];

        try {
            int i = Integer.parseInt(input);

            runIterative(i);

            //avoid StackOverflowExceptions
            if (i < 9000) {
                runRecursive(i);
            }

            runStirlings(i);
        } catch (NumberFormatException e) {
            System.out.println("Expected integer input. Exiting");
            System.exit(1);
        }
    }

    private static void runIterative(int i) {
        long start = System.currentTimeMillis();
        BigInteger factorial = new Factorial.Iterative().factorial(i);
        long end = System.currentTimeMillis();

        System.out.println("Iterative: " + factorial + " (" + (end - start) + " ms)");
    }

    private static void runRecursive(int i) {
        long start = System.currentTimeMillis();
        BigInteger factorial = new Factorial.Recursive().factorial(i);
        long end = System.currentTimeMillis();

        System.out.println("Recursive: " + factorial + " (" + (end - start) + " ms)");
    }

    private static void runStirlings(int i) {
        long start = System.currentTimeMillis();
        double factorial = new Factorial.Stirling().factorial(i);
        long end = System.currentTimeMillis();

        System.out.println("Stirling's Approximation: " + factorial + " (" + (end - start) + " ms)");
    }
}
