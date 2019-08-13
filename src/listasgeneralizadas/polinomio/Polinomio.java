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
    public String[] insertarTerminoCadena(String termino) {
        String[] partes = termino.split("\\^");
        int posiblesVariables = partes.length - 1;

        String[] terminos = new String[(posiblesVariables * 2) + 1];
        int i = 0;
        while (posiblesVariables > 0) {
            String exponente = partes[posiblesVariables];
            String parteAnterior = partes[posiblesVariables - 1];
            char variable = parteAnterior.charAt(parteAnterior.length() - 1);

            posiblesVariables--;

            System.out.print(" variable  " + variable);
            System.out.println(" -- exponente  " + exponente);
            int posibleT = partes[posiblesVariables].length() - 1;
            partes[posiblesVariables] = partes[posiblesVariables].substring(0, posibleT);

            terminos[i] = variable + "";
            terminos[i++] = exponente;

        }
        System.out.println(" coeficiente " + partes[0]);
        terminos[terminos.length - 1] = partes[0];
        return terminos;
    }

    private NodoPolinomioLG insertarTermino(String[] partes, NodoPolinomioLG nodoCabezaLocal, int referenciaVariable) {

        int posiblesVariables = partes.length / 2;
        char primeraVariablePartes = partes[0].charAt(0);
        
        /**
         * Crear el nodo cabeza con la primera variable
         */
        if (nodoCabezaLocal == null) {
            variables[cv] = primeraVariablePartes;
            nodoCabezaLocal = new NodoPolinomioLG();
            nodoCabezaLocal.setSw(1);
            nodoCabezaLocal.setCoeficiente( cv );
            cv++;
        }
        
        int indice = nodoCabezaLocal.getCoeficiente();
        char variableEvaluar = variables[indice];
        
        if (  variableEvaluar != primeraVariablePartes  )    ) {
            String[] completarPartes = new String[2];
            completarPartes[0] =  nodoCabezaLocal.getCoeficiente() + "";
            completarPartes[1] = "0";
            String[] partesCompletas = new String[partes.length + 2];
            System.arraycopy(completarPartes, 0, partesCompletas, 0, 2);
            System.arraycopy(partes, 0, partesCompletas, 2, partes.length);
            return insertarTermino(partesCompletas, nodoCabezaLocal);
        } else {
            int eNuevo = Integer.parseInt(partes[1]);

            // Es el nodo a encontrar o a crear
            NodoPolinomioLG nodoCabezaSublista = null;
            NodoPolinomioLG ultimoTermino = nodoCabezaLocal;
            NodoPolinomioLG terminoRecorrido = nodoCabezaLocal.getLiga();
            while (terminoRecorrido != null) {
                int e = terminoRecorrido.getExponente();
                if (e > eNuevo) {
                    ultimoTermino = terminoRecorrido;
                    terminoRecorrido = terminoRecorrido.getLiga();
                } else if (e == eNuevo) {
                    nodoCabezaSublista = (NodoPolinomioLG) terminoRecorrido.getCoeficiente();
                } else {
                    break;
                }
            }

            /**
             * Si tengo el nodoCabezaSublista es por que el exponente ya tenia
             * termino, de lo contrario es un nuevo termino.
             */
            if (nodoCabezaSublista == null) {
                nodoCabezaSublista = new NodoPolinomioLG();
                nodoCabezaSublista.setExponente(eNuevo);
                nodoCabezaSublista.setLiga(ultimoTermino.getLiga());
                ultimoTermino.setLiga(nodoCabezaSublista);
            } else {
                            //ToDo
            }


            if (posiblesVariables > 1) {
                nodoCabezaSublista.setSw(1);
                int nuevaLong = partes.length - 2;
                String[] nuevasPartes = new String[nuevaLong];
                System.arraycopy(partes, 2, nuevasPartes, 0, nuevaLong);
                nodoCabezaSublista.setCoeficiente(insertarTermino(nuevasPartes, null, referenciaVariable++));
            } else if (posiblesVariables == 1) {
                nodoCabezaSublista.setSw(0);
                nodoCabezaSublista.setCoeficiente(partes[2]);
            }

            return nodoCabezaLocal;
        }
    }

    public void mostrar() {

    }

    public static void main(String[] args) {
        Polinomio polinomio = new Polinomio();

        NodoPolinomioLG nodoCabezaGrande = polinomio.insertarTermino(polinomio.insertarTerminoCadena("5x^3"), null, 0);

    }

}
