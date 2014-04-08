import java.util.Scanner;
import java.util.Arrays;

public class CoinDenominations {
    public static void main(String...args) {
        Scanner scanner = new Scanner(System.in);
        int numDefinitions = scanner.nextInt();
        int value = scanner.nextInt();
        scanner.nextLine();

        for (int n = 0; n < numDefinitions; n++) {
            String[] definitions = scanner.nextLine().split(" ");
            int[] denominations = new int[definitions.length];            

            for (int i = 0; i < definitions.length; i++) {
                denominations[i] = Integer.parseInt(definitions[i]);
            }

            Arrays.sort(denominations);

            int[] coins = findMinCoins(denominations, value);
            printCoins(denominations, coins);
        }
    }

    private static void printCoins(int[] denominations, int[] coins) {
        for (int n = 0; n < denominations.length; n++) {
            if (coins[n] > 0) {
                System.out.print(denominations[n] + ":" + coins[n] + " ");
            }
        }
        System.out.println();
    }

    private static int[] findMinCoins(int[] denominations, int value) {
        int[] coins = new int[denominations.length];        

        int currValue = value;
        while (currValue > 0) {
            int index = findLargestDenom(denominations, currValue);
            int denomination = denominations[index];
            coins[index] = currValue / denomination;
            currValue = currValue % denomination;
        }

        return coins;
    }

    private static int findLargestDenom(int[] denominations, int value) {
        int largestIndex = 0;
        for (int n = 0; n < denominations.length; n++) {
            if (denominations[n] < value) {
                largestIndex = n;
            }
        }        

        return largestIndex;
    }

    private static int[] getNextCombination(int[] denominations, int[] coins) {
        int nextIndex = getNextDenomination(coins);
        if (nextIndex < 0) {
            return new int[0];
        }

        int value = denominations[nextIndex];
        coins[nextIndex]--;

        int[] newCoins = findMinCoins(denominations, value);
        for (int n = 0; n < newCoins.length; n++) {
            coins[n] += newCoins[n];
        }

        return coins;
    }

    private static int getNextDenomination(int[] coins) {
        for (int n = 1; n < coins.length; n++) {
            if (coins[n] > 0) {
                return n;
            }
        }

        return -1;
    }
}