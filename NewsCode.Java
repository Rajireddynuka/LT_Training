import java.util.ArrayList;
import java.util.*;

public class SubscriptionCalculator {

    private static class Newspaper {
        String name;
        double price[];

        public Newspaper(String name, double[] price) {
            this.name = name;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        // Initialize newspapers
        Newspaper toi = new Newspaper("TOI", new double[] {3, 3, 3, 3, 3, 5, 6});
        Newspaper hindu = new Newspaper("Hindu", new double[] {2.5, 2.5, 2.5, 2.5, 2.5, 4, 4});
        Newspaper et = new Newspaper("ET", new double[] {4, 4, 4, 4, 4, 4, 10});
        Newspaper bm = new Newspaper("BM", new double[] {1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5});
        Newspaper ht = new Newspaper("HT", new double[] {2, 2, 2, 2, 2, 4, 4});

        // Initialize newspapers list
        List<Newspaper> newspapers = new ArrayList<>();
        newspapers.add(toi);
        newspapers.add(hindu);
        newspapers.add(et);
        newspapers.add(bm);
        newspapers.add(ht);

        // User input
        //double budget = 40;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Budget :");
        double budget = input.nextDouble();

        // Calculate all possible combinations of newspapers within budget
        List<List<String>> subscriptions = calculateSubscriptions(newspapers, budget);
        


        // Output all possible subscriptions
        for (List<String> subscription : subscriptions) {
            Set<String> set = new HashSet<>(subscription);
                List<String> l1 = new ArrayList<>();
                l1.addAll(set);
            System.out.println(l1);
        }
    }

    private static List<List<String>> calculateSubscriptions(List<Newspaper> newspapers, double budget) {
        List<List<String>> subscriptions = new ArrayList<>();

        // Generate all possible combinations of newspapers
        int n = newspapers.size();
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = 0;
        }

        while (true) {
            // Calculate cost of current combination
            double cost = 0;
            for (int i = 0; i < n; i++) {
                cost += newspapers.get(i).price[indices[i]];
            }

            // Add current combination to subscriptions list if within budget
            if (cost <= budget) {
                List<String> subscription = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    if (indices[i] > 0) {
                        subscription.add(newspapers.get(i).name);
                    }
                }
                Set<String> set = new HashSet<>(subscription);
                List<String> l1 = new ArrayList<>();
                l1.addAll(set);
                subscriptions.add(l1);
            }

            // Generate next combination
            int i = n - 1;
            while (i >= 0 && indices[i] == newspapers.get(i).price.length - 1) {
                indices[i] = 0;
                i--;
            }
            if (i < 0) {
                break;
            }
            indices[i]++;
        }

        return subscriptions;
    }
}
