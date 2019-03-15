package chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        List<Transaction> tn2011 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        System.out.println(tn2011);

        // Query 2: What are all the unique cities where the traders work?
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());

        System.out.println(cities);

        // Query 3: Find all traders from Cambridge and sort them by name.
        List<Trader> traders = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());

        System.out.println(traders);

        // Query 4: Return a string of all traders’ names sorted alphabetically.
        String names = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (a, b) -> a + b);

        System.out.println(names);

        // Query 5: Are there any trader based in Milan?
        boolean milanBased = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));

        System.out.println(milanBased);

        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Milan"))
                .map(Transaction::getTrader)
                .forEach(trader -> trader.setCity("Cambridge"));

        System.out.println(transactions);

        // Query 7: What's the highest value in all the transactions?
        Optional<Integer> highestValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        System.out.println(highestValue);

        // Query 8: Find the transaction with the smallest value?
        Optional<Transaction> smallest = transactions.stream()
                .min(comparing(Transaction::getValue));

        System.out.println(smallest);

        // Query 9: Print all transaction' values from the trader living in Cambridge
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

    }

}
