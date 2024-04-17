import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        /*System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);*/
        try{
            // Setting URL
            String url_str = "https://v6.exchangerate-api.com/v6/bc078a0e2f4b47dd5ac69d2e/latest/USD";

            // Making Request
            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            // Convert to JSON
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();

            // Accessing object
            String req_result = jsonobj.get("result").getAsString();

            JOptionPane.showMessageDialog(null,"Mensaje: " + req_result);

            JsonObject conversion = jsonobj.getAsJsonObject("conversion_rates");

            JOptionPane.showMessageDialog(null,"Mensaje:PASO ARRAY ");

            //String salida = jsonobj.get("base_code").getAsString();

            //JOptionPane.showMessageDialog(null,"Mensaje: " + salida);

            String moneda = conversion.getAsJsonObject().get("AED").getAsString();

            JOptionPane.showMessageDialog(null,"Mensaje número: " + moneda);

            Scanner op = new Scanner(System.in);

            int opcion = 0;

            while(opcion!=4){
                System.out.println("1) Opción 1.");
                System.out.println("2) Opción 2.");
                System.out.println("3) Opción 3.");
                System.out.println("4) Salir.");

                System.out.println("4) Salir.");
                System.out.println("Ingrese opción:");
                opcion = op.nextInt();
                System.out.println("La opción elegida es: " + opcion + ". Asegurado.");

            }
            System.out.println("Gracias por utilizar el conversor de monedas.");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}