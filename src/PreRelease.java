/**
 * *********************************
 * @author class created by Krish  *
 * *********************************
 */

import java.util.Scanner;

public class PreRelease {

    public static void main(String[] args) {

        int totalCow;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the herd size.");
        totalCow = scanner.nextInt();

        int identity[] = new int[totalCow];
        double yield[] = new double[totalCow];
        double totalYield = 0;
        double highestYield = 0;
        double dailyYield[] = new double[totalCow];
        int low[] = new int[totalCow];
        int cowID;
        boolean validated;
        boolean defined;

        for (int count = 0; count < totalCow; count++) {

            // check this ID isn't already used
            do {
                System.out.println("Input the cow identity for cow #" + (count + 1) + ".");
                cowID = scanner.nextInt();

                validated = true;

                // range check
                if ((cowID < 0) || (cowID > 999)) {
                    System.out.println("This identity does not fall under the range of possible identities.");
                    validated = false;
                    continue;
                }

                // iterate through the other identities to check it's available
                for (int i = 0; i < totalCow; i++) {
                    if (identity[i] == cowID) {
                        // clash detected, invalid input
                        System.out.println("This identity has already been used by another cow.");
                        validated = false;
                        break;
                    }
                }
            } while (!validated);

            identity[count] = cowID;
        }

        for (int day = 0; day < 7; day++) {
            for (int session = 0; session < 2; session++) {
                for (int i = 0; i < totalCow; i++) {
                    do {
                        System.out.println("Enter the identity of the cow to be milked on day #" + (day + 1) + " session #" + (session + 1) + ".");
                        cowID = scanner.nextInt();

                        defined = false;

                        // iterate through the other identities to check it's valid
                        for (int count = 0; count < totalCow; count++) {
                            if (identity[count] == cowID) {
                                // found it in our list
                                defined = true;
                                break;
                            }
                        }

                        // send error message before next input message if necessary
                        if (!defined) {
                            System.out.println("This identity has not been pre-defined or already has a defined value.");
                        }
                    } while (!defined);

                    System.out.println("Enter the yield of cow #" + cowID + ".");
                    double yieldL = scanner.nextDouble();

                    for (int counter = 0; counter < totalCow; counter++) {
                        if (identity[counter] == cowID) {

                            yield[counter] += yieldL;
                            totalYield += yieldL;
                            dailyYield[counter] += yieldL;

//                            if (yield[counter] % 1 < 0.5) {
//                                yield[counter] -= (yield[counter] % 1);
//                            } else {
//                                yield[counter] += (1 - (yield[counter] % 1));
//                            }

                            if (session == 1) {
                                if (dailyYield[counter] < 12) {
                                    low[counter]++;
                                }
                            }
                            dailyYield[counter] = 0;
                        }
                    }
                }
            }
        }

        System.out.println("The final yield of all the cows is " + totalYield + " litres of milk.");

        for (int counter = 0; counter < totalCow; counter++) {
            double average = (totalYield / totalCow);

//            if (average % 1 < 0.5) {
//                average -= (average % 1);
//            } else {
//                average += (1 - (average % 1));
//            }

            System.out.println("The average yield for each cow in a day for identity " + identity[counter] + ": " + round(average) + " litres of milk.");
            if (yield[counter] > highestYield) {
                highestYield = yield[counter];
            }
        }

        for (int counter = 0; counter < totalCow; counter++) {
            if (yield[counter] == highestYield) {
                System.out.println("The cow identity: " + identity[counter] + " has the highest production of " + round(highestYield) + ".");
            }
            if (low[counter] > 3) {
                System.out.println("identity:" + identity[counter] + " has produced less than 12 litres of milk for at least 4 days.");
            }
        }
    }

    private static int round(double num) {
        int rounded = (int) num;
        if (num % 1 >= 0.5) rounded++;
        return rounded;
    }
}
