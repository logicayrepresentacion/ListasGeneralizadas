/*
 * Copyright 2019 Carlos Alejandro Escobar Marulanda ealejandro101@gmail.com
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation 
 * files (the "Software"), to deal in the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or 
 * sell copies of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following 
 * conditions:
 * The above copyright notice and this permission notice shall 
 * be included in all copies or substantial portions of the 
 * Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR 
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package listasgeneralizadas.polinomio;

import java.util.ArrayList;

/**
 *
 * @author Alejandro
 */
public class Polinomio {

    NodoPolinomioLG nodoCabeza;
    char variables[] = new char[3];
    int cv = 0;

    /**
     * Cadena representa un polinomio escrito de la forma
     * 5x^3+6x^1y^2-10z^1+8+y^1+8x^4y^2z^1
     *
     * @param cadena
     * @return
     */
    public NodoPolinomioLG construir(String cadena) {

        /**
         * identificar los terminos de la sucesión
         */
        ArrayList<String> terminosCadena = Utileria.partirTerminosCadena(cadena);

        /**
         * Crear las partesTermino para cada termino del polinomio
         */
        ArrayList<PartesTermino> terminosPartesTermino = new ArrayList<>();
        for (String terminoCadena : terminosCadena) {
            PartesTermino terminoPartesTermino = new PartesTermino(terminoCadena);
            terminosPartesTermino.add(terminoPartesTermino);
        }

        /**
         * Insertar uno a uno el termino en el polinomioLG con partesTermino
         * 6x^1y^2
         */
        NodoPolinomioLG cabeza = null;
        boolean primeraVez = true;
        for (PartesTermino unPartesTermino : terminosPartesTermino) {
            if (primeraVez) {
                cabeza = insertarTermino(unPartesTermino, null, null);
                primeraVez = false;
            } else {
                insertarTermino(unPartesTermino, cabeza, null);
            }
        }

        return cabeza;

    }

    /**
     * Termino
     *
     * @param miPartesTermino
     * @param nodoCabezaLocal
     * @param referenciaVariable
     * @return
     */
    public NodoPolinomioLG insertarTermino(PartesTermino miPartesTermino,
            NodoPolinomioLG nodoCabezaLocal, Character referenciaVariable) {

        int cantidadVariables = miPartesTermino.cantidadVariables();
        //ToDo validar que pasa si el partesTermino es un escalar al principio
        //ToDo validar si el orden es xy o yx

        char primeraVariableTermino = miPartesTermino.getPrimeraVariable();

        // Crear o adicionar las variables en el arreglo de variables 
        // nodo cabeza con la primera variable 
        if (nodoCabezaLocal == null) {
            variables[cv] = primeraVariableTermino;
            nodoCabezaLocal = new NodoPolinomioLG();
            nodoCabezaLocal.setSw(1);
            nodoCabezaLocal.setCoeficiente(primeraVariableTermino);
            cv++;
        }

        // Ingresar el termino 
        char variableEvaluarTermino = (char) nodoCabezaLocal.getCoeficiente();
        double coeficiente = miPartesTermino.getCoeficiente();

        NodoPolinomioLG nodoRecorrido = nodoCabezaLocal;

        // Buscar el exponente de x en las partes 
        int exponente = miPartesTermino.buscarExponente(variableEvaluarTermino);
        // Busque el nodo según el exponente 
        NodoPolinomioLG nodoExponente = posicionar(exponente, nodoRecorrido);

        miPartesTermino.eliminarVariable(variableEvaluarTermino);
        if (miPartesTermino.cantidadVariables() <= 0) { // Todo
            nodoExponente.setCoeficiente(coeficiente);
            nodoExponente.setSw(0);
            return nodoCabezaLocal;
        } else {
            // OJO, si ya existia el nodoExponente
            nodoExponente.setSw(1);
            NodoPolinomioLG nuevaRaiz = insertarTermino(miPartesTermino, null, variableEvaluarTermino);
            nodoExponente.setCoeficiente(nuevaRaiz);
        }

        return nodoCabezaLocal;

    }

    public void mostrar() {

    }

    private NodoPolinomioLG posicionar(int exponente, NodoPolinomioLG nodoRecorrido) {
        NodoPolinomioLG ultimo = nodoRecorrido;
        nodoRecorrido = nodoRecorrido.getLiga();
        while (nodoRecorrido != null) {
            if (nodoRecorrido.getExponente() > exponente) {
                ultimo = nodoRecorrido;
                nodoRecorrido = nodoRecorrido.getLiga();
            } else if (nodoRecorrido.getExponente() == exponente) {
                return nodoRecorrido;
            } else {
                nodoRecorrido = null;
            }
        }

        if (nodoRecorrido == null) {
            nodoRecorrido = new NodoPolinomioLG();
            nodoRecorrido.setExponente(exponente);
            nodoRecorrido.setLiga(ultimo.getLiga());
            ultimo.setLiga(nodoRecorrido);
        }

        return nodoRecorrido;
    }

}
