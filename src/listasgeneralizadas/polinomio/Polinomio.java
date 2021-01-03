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

/**
 *
 * @author Alejandro
 */
public class Polinomio {

    NodoPolinomioLG nodoCabeza;
    char variables[] = new char[3];
    int cv = 0;

    /**
     * Se ingresa el termino para el polinomio de la forma 5x^3y^2 o de la forma
     * 7y^1x^3 o de la forma 8 0 de la forma 8z^2
     *
     * @param termino
     * @return
     */
    public String[] parseCadenaTermino(String termino) {
        String[] partes = termino.split("\\^");
        // 5x^3 -    [ 5x , 3 ]
        // 5x^3y^2 -    [ 5x , 3y , 2]
        int posiblesVariables = partes.length - 1;

        String[] terminos = new String[(posiblesVariables * 2) + 1];
        int i = 0;
        while (posiblesVariables > 0) {
            String exponente = partes[posiblesVariables];
            String parteAnterior = partes[posiblesVariables - 1];
            char variable = parteAnterior.charAt(parteAnterior.length() - 1);
            // 5x^3y^2 -    [ 5x , 3y , 2]
            // y con exponente 2
            posiblesVariables--;

            System.out.print(" variable  " + variable);
            System.out.println(" -- exponente  " + exponente);
            int posibleT = partes[posiblesVariables].length() - 1;
            //[ 5x , 3 , 2]
            partes[posiblesVariables] = partes[posiblesVariables].substring(0, posibleT);

            terminos[i] = variable + "";
            terminos[i++] = exponente;
            //[y, 2, x, 3]

        }
        System.out.println(" coeficiente " + partes[0]);
        terminos[terminos.length - 1] = partes[0];
        return terminos;
        //[y, 2, x, 3, 5]
    }

    /**
     *
     * @param partes Algo parecido a [y, 2, x, 3, 5]
     * @param nodoCabezaLocal
     * @param referenciaVariable
     * @return
     */
    private NodoPolinomioLG insertarTermino(String[] partes, NodoPolinomioLG nodoCabezaLocal, int referenciaVariable) {

        int posiblesVariables = partes.length / 2;
        char primeraVariablePartes = partes[0].charAt(0);

        // Crear o adicionar las variables en el arreglo de variables
        //Crear el nodo cabeza con la primera variable
        if (nodoCabezaLocal == null) {
            variables[cv] = primeraVariablePartes;
            nodoCabezaLocal = new NodoPolinomioLG();
            nodoCabezaLocal.setSw(1);
            nodoCabezaLocal.setCoeficiente(cv);
            cv++;
        }

        // Ingresar el termino
        int indice = nodoCabezaLocal.getCoeficiente();
        char variableEvaluarTermino = variables[indice];
        double coeficiente = Double.parseDouble(partes[partes.length - 1]);
        NodoPolinomioLG nodoDesdeDonde = nodoCabezaLocal;
        

            // Buscar el exponente de x en las partes
            int exponente = buscarExponente(variable, partes);
            // Me posiciono o creo el nodo x según el exponente
            NodoPolinomioLG nodoExponente = posicionar(exponente, nodoDesdeDonde);

            if (posiblesVariables - 1 > 0) {
                nodoExponente.setCoeficiente(coeficiente);
                nodoExponente.setSw(0);
                return nodoExponente;
            } else {
                nodoExponente.setSw(1);
                eliminoVariable( partes,variable )
                nodoExponente.setCoeficiente(   insertarTermino ( partes, null,  variable   )    );
            }

            // Si hay más variables en el termino, ingreso a la siguiente.
        

    }

    public void mostrar() {

    }

    public static void main(String[] args) {
        Polinomio polinomio = new Polinomio();

        NodoPolinomioLG nodoCabezaGrande = polinomio.insertarTermino(polinomio.parseCadenaTermino("5x^3"), null, 0);

    }

}
