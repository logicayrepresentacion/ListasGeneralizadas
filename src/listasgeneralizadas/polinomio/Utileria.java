/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listasgeneralizadas.polinomio;

import java.util.ArrayList;

/**
 *
 * @author ealej
 */
public class Utileria {

    /**
     * Se recibe una cadena de este tipo 5x^3+6x^1y^2-10z^1+8+1y^1+8x^4y^2z^1
     *
     * @param cadena
     * @return
     */
    public static ArrayList<String> partirTerminosCadena(String cadena) {
        ArrayList<String> terminosCadena = new ArrayList<>();
        StringBuilder temporal = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char variable = cadena.charAt(i);
            if (variable == '+' || variable == '-') {
                terminosCadena.add(temporal.toString());
                temporal = new StringBuilder();
                temporal.append(variable);
            } else {
                temporal.append(variable);
            }
        }
        terminosCadena.add(temporal.toString());
        System.out.println("ter" + terminosCadena.toString());
        return terminosCadena;
    }

}
