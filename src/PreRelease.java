import java.util.Scanner;

/**
 * *****************************
 * * Class created by @Krish ***
 * *****************************
 **/

public class PreRelease {

    public static void main(String[] args) {

        int totalCow;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the herd size");
        totalCow = scanner.nextInt();

        int identity[] = new int[totalCow];
        double yield[] = new double[totalCow];
        double totalYield = 0;
        int falseIdentity;

        int under[] = new int[totalCow];

        for (int cow = 0; cow < totalCow; cow++) {
            System.out.println("Input the cow identity for cow #" + (cow + 1));
            falseIdentity = scanner.nextInt();
            identity[cow] = falseIdentity;

            while ((identity[cow] < 0) || (identity[cow] > 999)) {
                System.out.println("This is not a valid identity. Input a new one");
                identity[cow] = scanner.nextInt();
            }

            for (int day = 0; day < 7; day++) {
                double dayYield = 0;
                for (int session = 0; session < 2; session++) {
                    System.out.println("What is the yield of cow #" + (cow + 1) + " on day #" + (day + 1) + " session #" + (session + 1));
                    dayYield += scanner.nextDouble();

                    while (dayYield < 0) {
                        System.out.println("The yield cannot be negative. Please re-input the yield");
                        dayYield += scanner.nextDouble();
                    }

                }

                yield[cow] += dayYield;
                totalYield += dayYield;

                if (dayYield < 12)
                    under[cow]++;
            }
        }


        System.out.println("The final yield of all the cows is " + round(totalYield) + " litres of milk");

        int hCow = 0;
        double hYield = 0;

        String underS = "";

        for (int cow = 0; cow < totalCow; cow++) {
            if (yield[cow] > hYield) {
                hYield = yield[cow];
                hCow = cow;
            }

            if (under[cow] >= 4)
                underS += identity[cow] + " ";
        }

        System.out.println("Cow " + identity[hCow] + " had the highest yield, " + round(hYield) + " litres of milk");

        for (int counter = 0; counter < totalCow; counter++)
            System.out.println("The average yield for each cow: identity " + identity[counter] + ": " + round(yield[counter] / 14.0) + " litres of milk");

        System.out.print("Cows with daily yields of less than 12 litres for 4 or more days:" + "Cow ID #" + underS);
    }

    private static int round(double num) {
        int rounded = (int) num;
        if (num % 1 >= 0.5) rounded++;
        return rounded;
    }

}

