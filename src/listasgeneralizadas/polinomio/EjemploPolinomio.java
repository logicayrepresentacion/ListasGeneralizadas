/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listasgeneralizadas.polinomio;

/**
 *
 * @author ealej
 */
public class EjemploPolinomio {

    public static void main(String[] args) throws Exception {
        Polinomio polinomio = new Polinomio();

       // NodoPolinomioLG cabeza = polinomio.construir("5x^3+6x^1y^2-10z^1+8+1y^1+8x^4y^2z^1");
        NodoPolinomioLG cabeza = polinomio.construir("5x^3+1y^1+8");

        System.out.println("cabeza");
        //NodoPolinomioLG nodoCabezaGrande = polinomio.insertarTermino(, null, 0);
    }

}
