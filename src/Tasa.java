//Clase que mapea directamente los campos de la respuesta JSON

import java.util.Map;

public class Tasa {
    private String base; // Representa la moneda base de las tasas de conversión
    private String date; // Representa la fecha de la tasa de cambio
    private Map<String, Double> conversion_rates; // Mapa que almacena las tasas de conversión para diferentes monedas


    //Getters y setters
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }

    public void setConversion_rates(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }
}
