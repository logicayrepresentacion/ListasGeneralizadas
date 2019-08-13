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

    NodoLG l;

    public void construir(String hilera) {
        Stack<NodoLG> pila = new Stack();
        NodoLG x = new NodoLG();
        l = x;
        NodoLG u = x;
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

    void mostrar() {
        if (l == null) {
            System.out.println("()");
        } else {
            System.out.print("(");
            Stack<NodoLG> pila = new Stack();
            NodoLG ulNodo = l;
            while (ulNodo != null) {
                if (ulNodo.getSw() == 0) {
                    char dato = (char) ulNodo.getDato();
                    System.out.print(dato);
                    ulNodo = ulNodo.getLiga();
                    if(ulNodo!=null){
                        System.out.print(",");
                    }else {
                        System.out.print(")");
                        if(!pila.empty()){
                            System.out.print(",");
                            ulNodo = pila.pop().getLiga();
                        }
                    }
                }
                else {
                    System.out.print("(");
                    pila.add(ulNodo);
                    ulNodo = (NodoLG) ulNodo.getDato();
                }
            }
            
        }

    }

}
