import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Conversor {

    public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            try {
            boolean exit = false;
            while (!exit) {
                System.out.println("**************************************************************");
                System.out.println("Bienvenido/a al menú de Conversión de Moneda :)");
                System.out.println("1. Dólar a Peso colombiano");
                System.out.println("2. Peso colombiano a Dólar");
                System.out.println("3. Dólar a Peso chileno");
                System.out.println("4. Peso chileno a Dólar");
                System.out.println("5. Dólar a Real brasileño");
                System.out.println("6. Real brasileño a Dólar");
                System.out.println("7. Dólar a Peso argentino");
                System.out.println("8. Peso argentino a Dólar");
                System.out.println("9. Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        convertir("USD", "COP");
                        break;
                    case 2:
                        convertir("COP", "USD");
                        break;
                    case 3:
                        convertir("USD", "CLP");
                        break;
                    case 4:
                        convertir("CLP", "USD");
                        break;
                    case 5:
                        convertir("USD","BRL");
                        break;
                    case 6:
                        convertir("BRL", "USD");
                        break;
                    case 7:
                        convertir("USD","ARS");
                        break;
                    case 8:
                        convertir("ARS", "USD");
                        break;
                    case 9:
                        exit = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        break;
                }
            }

        } catch (InputMismatchException e){
            System.out.println("Opción inválida. Por favor, ingrese un número válido.");
            scanner.nextLine();
        }

        catch (IOException | InterruptedException e) {
            System.err.println("Error al procesar la conversión: " + e.getMessage());
        }
        scanner.close();
    }

    private static void convertir(String monedaOrigen, String monedaAConvertir) throws IOException, InterruptedException {
        double tasa = ConsultaMoneda.buscaMoneda(monedaOrigen, monedaAConvertir);

        Scanner lectura = new Scanner(System.in);
        System.out.println("Ingresa la cantidad a convertir: ");
        BigDecimal monto = lectura.nextBigDecimal();
        lectura.nextLine();

        BigDecimal total = monto.multiply(BigDecimal.valueOf(tasa));
        System.out.printf("Tasa de cambio de %s a %s: %.2f\n", monedaOrigen, monedaAConvertir, tasa);
        System.out.printf("El valor %.2f [%s] corresponde al valor final de =>>> %.2f [%s]\n", monto, monedaOrigen, total, monedaAConvertir);
    }

}

