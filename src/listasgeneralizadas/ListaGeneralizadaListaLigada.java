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
package listasgeneralizadas;

import java.util.Stack;

/**
 *
 * @author alejandroescobar
 */
public class ListaGeneralizadaListaLigada {

    private NodoLG raizLista;

    /**
     * Permite construir a partir de la representación en Hilera La estrategia
     * es leer cada caracter de la hilera (cadena) Según el caracter tomar una
     * acción
     *
     * @param hilera
     */
    public void construir(String hilera) {
        /**
         * Plantea un pila para seguir las migas de pan cada vez que inicia una
         * nueva sublista
         *
         */
        Stack<NodoLG> pila = new Stack();
        NodoLG x = new NodoLG();
        raizLista = x;
        NodoLG u = x;
        /**
         * La evaluación de cada caracter inicia desde el caracter 1 Finaliza en
         * el último caracter
         */
        for (int i = 1; i < hilera.length() - 1; i++) {
            char c = hilera.charAt(i);
            switch (c) {
                case '(':
                    u.setSw(1);
                    pila.add(u);
                    x = new NodoLG();
                    u.setDato(x);
                    u = x;
                    break;
                case ')':
                    u = pila.pop();
                    break;
                case ',':
                    x = new NodoLG();
                    u.setLiga(x);
                    u = x;
                    break;
                default:
                    u.setDato(c);
                    u.setSw(0);
            }
        }
    }

    public String parseHilera() {

        StringBuilder hilera = new StringBuilder();

        if (raizLista == null) {
            hilera.append("()");
        } else {
            hilera.append("(");
            Stack<NodoLG> pila = new Stack();
            // Nodo para recorrer la lista generalizada
            NodoLG ultimoNodo = raizLista;
            while (ultimoNodo != null) {
                if (ultimoNodo.getSw() == 0) {
                    char dato = (char) ultimoNodo.getDato();
                    hilera.append(dato);
                    ultimoNodo = ultimoNodo.getLiga();
                    if (ultimoNodo != null) {
                        hilera.append(",");
                    } else {
                        hilera.append(")");
                        // Ciclo para ir subiendo en la pila siempre que estemos al final de una sublista
                        while (!pila.empty()) {
                            ultimoNodo = pila.pop().getLiga();
                            if (ultimoNodo != null) {
                                hilera.append(",");
                                break;
                            } else {
                                hilera.append(")");
                            }
                        }
                    }
                } else {
                    hilera.append("(");
                    pila.add(ultimoNodo);
                    ultimoNodo = (NodoLG) ultimoNodo.getDato();
                }
            }
        }
        return hilera.toString();
    }

}
