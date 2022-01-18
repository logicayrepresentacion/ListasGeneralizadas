/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listasgeneralizadas.polinomio;

import java.util.HashMap;

/**
 *
 * @author ealej
 */
public class PartesTermino {

    String coeficiente;
    /**
     * Caracter es la variable String es el exponente
     */
    HashMap<Character, String> variables;

    /**
     * Se ingresa el termino para el polinomio de la forma 5x^3y^2 o de la forma
     * 7y^1x^3 o de la forma 8 0 de la forma 8z^2
     *
     * @param terminoCadena
     */
    public PartesTermino(String terminoCadena) {
        String[] partes = terminoCadena.split("\\^");
        // 5x^3 -    [ 5x , 3 ]
        // 5x^3y^2 -    [ 5x , 3y , 2]
        int posiblesVariables = partes.length - 1;

        variables = new HashMap<Character, String>();

        while (posiblesVariables > 0) {
            String exponente = partes[posiblesVariables];
            String parteAnterior = partes[posiblesVariables - 1];
            char variable = parteAnterior.charAt(parteAnterior.length() - 1);
            variables.put(variable, exponente);

            partes[posiblesVariables - 1] = parteAnterior.substring(0, parteAnterior.length() - 1);
            // 5x^3 -    [ 5 , 3 ]
            // 5x^3y^2 -    [ 5x , 3 , 2]
            posiblesVariables--;
        }

        System.out.println(" coeficiente " + partes[0]);
        coeficiente = partes[0];
    }

    /**
     * Retorna la cantidad de variables ACTIVAS que están en el termino La
     * cantidad de variables puede cambiar en el tiempo por la manipulación del
     * objeto.
     *
     * @return
     */
    int cantidadVariables() {
        return variables.size();
    }

    char getPrimeraVariable() {
        // Todo - OJO si no hay más variables
        return variables.keySet().iterator().next();
    }

    double getCoeficiente() {
        return Double.parseDouble(coeficiente);
    }

    int buscarExponente(char variableEvaluarTermino) {
        String exponente = variables.get(variableEvaluarTermino);
        if (exponente == null) {
            return 0;
        }
        return Integer.parseInt(exponente);
    }

    void eliminarVariable(char variableEvaluarTermino) {
        variables.remove(variableEvaluarTermino);
    }

}
