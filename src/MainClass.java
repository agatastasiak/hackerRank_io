public class MainClass {

    public static void main(String[] args) {
        System.out.println("App start!");
        int[] arr = {-5, 4, -2, 3, 1, -1, -6, -1, 0, 5};
        System.out.println("MinX: " + minX(arr, 2));
        int costPerCut =1;
        int salePrice = 10;
        int lengths[] = {26,103,59};
        System.out.println("MaxProfit: " + maxProfit(costPerCut,salePrice,3,lengths));

    }

    private static int minX(int[] arrayOfInt, int x) {
        x = 1;
        int runningSum = x;
        for (int i : arrayOfInt) {
            while (runningSum + i < 1) {
                x += 1;
                runningSum = x;
            }
            runningSum += i;
        }
        return x;
    }

    private static int maxProfit(int costPerCut, int salePrice, int lengths_count, int[] lengths) {
        int totalUniformRods = 0;
        int totalCuts = 0;
        int maxLength = Integer.MIN_VALUE;
        // Finding the maximum length among all the rods
        for (int i = 0; i < lengths_count; i++) {
            if (lengths[i] > maxLength) {
                maxLength = lengths[i];
            }
        }
        // Calculating the profit for each possible saleLength
        for (int saleLength = 1; saleLength <= maxLength; saleLength++) {
            int currentTotalUniformRods = 0;
            int currentTotalCuts = 0;
            // Counting the number of sellable rods and cuts required for each saleLength
            for (int i = 0; i < lengths_count; i++) {
                if (lengths[i] >= saleLength) {
                    currentTotalUniformRods += lengths[i] / saleLength;
                    currentTotalCuts += lengths[i] % saleLength;
                }
            }
            // Calculating the number of cuts required for the current saleLength
            currentTotalCuts = (currentTotalCuts + saleLength - 1) / saleLength;
            currentTotalCuts *= costPerCut;
            // If the current profit is greater than the previous profit, update the totalUniformRods and totalCuts
            int currentProfit = currentTotalUniformRods * saleLength * salePrice - currentTotalCuts;
            if (currentProfit > totalUniformRods * saleLength * salePrice - totalCuts) {
                totalUniformRods = currentTotalUniformRods;
                totalCuts = currentTotalCuts;
            }
        }
        // Return the maximum profit
        return totalUniformRods * maxLength * salePrice - totalCuts;
    }
}

