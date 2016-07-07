package factorial;

import java.math.BigInteger;

class Factorial {
    /**
     * Basic iterative implementation. Slows down quite a bit when operand gets larger.
     */
    static class Iterative {
        BigInteger factorial(int operand) {
            if (operand < 0) {
                throw new IllegalArgumentException("Cannot compute factorial on negative number.");
            } else if (operand == 0 || operand == 1) {
                return BigInteger.valueOf(1);
            }

            BigInteger result = BigInteger.valueOf(1);
            for (int i = 1; i < operand; i++) {
                BigInteger next = BigInteger.valueOf(i + 1);
                result = result.multiply(next);
            }

            return result;
        }
    }

    /**
     * Recursive implementation. Slower than iterative and may run into StackOverflowExceptions!
     */
    static class Recursive {
        BigInteger factorial(int operand) {
            if (operand < 0) {
                throw new IllegalArgumentException("Cannot compute factorial on negative number.");
            } else if (operand == 0 || operand == 1) {
                return BigInteger.valueOf(1);
            } else {
                return BigInteger.valueOf(operand).multiply(factorial(operand - 1));
            }
        }
    }

    /**
     * Stirling's approximation :) Much, much faster if you don't need an exact answer!
     * However, quite inaccurate at lower values of operand
     */
    static class Stirling {
        double factorial(int operand) {
            return Math.pow(operand / Math.E, operand) * Math.sqrt(2 * Math.PI * operand);
        }
    }
}