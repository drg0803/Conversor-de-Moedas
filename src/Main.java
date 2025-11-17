import java.util.Scanner;

public class Main {
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== Conversor de Moedas ===");
    System.out.println("Digite a moeda de origem: ");
    String fromCurrency = scanner.nextLine().toUpperCase();

    System.out.println("Digite a moeda de destino: ");
    String toCurrency = scanner.nextLine().toUpperCase();

    System.out.println("Digite o valor a ser convertido: ");
    double amount = scanner.nextDouble();

    double converted = CurrencyConverterMenu.convertCurrency(fromCurrency, toCurrency, amount);

    if (converted != -1) {
        System.out.printf("%.2f %s = %.2f %s%n", amount, fromCurrency, converted, toCurrency);
    }

    scanner.close();
}
}