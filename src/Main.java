import com.google.gson.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try{
            // Setting URL
            String url_str = "https://v6.exchangerate-api.com/v6/bc078a0e2f4b47dd5ac69d2e/latest/USD";

            /*----------------------------------------------*/
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url_str))
                    .build();

            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

            String respuestaHttp= response.body();

            JsonObject jsonObjectMon = new Gson().fromJson(respuestaHttp, JsonObject.class);

            JsonObject conversion = jsonObjectMon.getAsJsonObject("conversion_rates");

            /*----------------------------------------------*/

            Scanner op = new Scanner(System.in);

            int opcion = 0;

            while(opcion!=6){
                System.out.println("\n----------------------------------------------------------------");
                System.out.println("1) Convertir de Pesos Argentinos a Dólares Estadounidenses.");
                System.out.println("2) Convertir de Dólares Estadounidenses a Pesos Argentinos.");
                System.out.println("3) Convertir de Pesos Brasileros a Dólares Estadounidenses.");
                System.out.println("4) Convertir de Dólares Estadounidenses a Pesos Brasileros.");
                System.out.println("5) Convertir de Euros a Pesos Brasileros.");

                System.out.println("6) Salir.");
                System.out.println("Ingrese opción:");
                opcion = op.nextInt();

                double suma;
                String cotMoneda;
                DecimalFormat df = new DecimalFormat("#.##");

                switch (opcion){


                    case 1:
                        System.out.println("Ingrese pesos argentinos a convertir:");
                        suma = op.nextDouble();
                        cotMoneda = conversion.getAsJsonObject().get("ARS").getAsString();
                        PesosArgentinos dol_usd = new PesosArgentinos(Double.parseDouble(cotMoneda));
                        System.out.println("Un dolar equivale a " + df.format(Double.parseDouble(cotMoneda)) + " pesos argentinos.");
                        System.out.println("La conversión actual de " + df.format(suma) + " pesos argentinos es de " + df.format(dol_usd.cambiarADolares(suma)) + " dólares.");

                        break;

                    case 2:
                        System.out.println("Ingrese dólares a convertir:");
                        suma = op.nextDouble();
                        cotMoneda = conversion.getAsJsonObject().get("ARS").getAsString();
                        PesosArgentinos ars_usd = new PesosArgentinos(Double.parseDouble(cotMoneda));
                        System.out.println("Un dolar equivale a " + df.format(Double.parseDouble(cotMoneda)) + " pesos argentinos.");
                        System.out.println("La conversión actual de " + df.format(suma) + " dólares es de " + df.format(ars_usd.convertir(suma)) + " pesos argentinos.");

                        break;

                    case 3:
                        System.out.println("Ingrese pesos brasileros a convertir:");
                        suma = op.nextDouble();
                        cotMoneda = conversion.getAsJsonObject().get("BRL").getAsString();
                        PesosBrasileros brl_usd = new PesosBrasileros(Double.parseDouble(cotMoneda));
                        System.out.println("Un dólar equivale a " + df.format(Double.parseDouble(cotMoneda)) + " pesos brasileros.");
                        System.out.println("La conversión actual de " + df.format(suma) + " pesos brasileros es de " + df.format(brl_usd.cambiarADolares(suma)) + " dólares.");

                        break;

                    case 4:
                        System.out.println("Ingrese dólares a convertir:");
                        suma = op.nextDouble();
                        cotMoneda = conversion.getAsJsonObject().get("BRL").getAsString();
                        PesosBrasileros usd_brl = new PesosBrasileros(Double.parseDouble(cotMoneda));
                        System.out.println("Un dólar equivale a " + df.format(Double.parseDouble(cotMoneda)) + " pesos brasileros.");
                        System.out.println("La conversión actual de " + df.format(suma) + " dólares es de " + df.format(usd_brl.convertir(suma)) + " pesos brasileros.");

                        break;
                    case 5:
                        System.out.println("Ingrese euros a convertir:");
                        suma = op.nextDouble();
                        cotMoneda = conversion.getAsJsonObject().get("EUR").getAsString();
                        Euros eur_brl = new Euros(Double.parseDouble(cotMoneda));
                        double dolares = eur_brl.cambiarADolares(suma);
                        cotMoneda = conversion.getAsJsonObject().get("BRL").getAsString();
                        PesosBrasileros brl_eur = new PesosBrasileros(Double.parseDouble(cotMoneda));
                        System.out.println(suma + " euros, equivalen a " + df.format(dolares) + " dolares.");
                        System.out.println("La conversión actual de " + df.format(suma) + " euros es de " + df.format(brl_eur.convertir(dolares)) + " pesos brasileros.");

                        break;
                }


            }
            System.out.println("Gracias por utilizar el conversor de monedas.");



        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }


    }

}