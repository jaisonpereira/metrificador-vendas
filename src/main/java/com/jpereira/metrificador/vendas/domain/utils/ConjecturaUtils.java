package com.jpereira.metrificador.vendas.domain.utils;

public class ConjecturaUtils {

    public static Double calculateIteration(Double valorCalc) {
        if (0 == valorCalc % 2) {
            valorCalc = valorCalc / 2;
            return valorCalc;
        }
        valorCalc = (valorCalc * 3) + 1;
        return valorCalc;

    }

    public static void calculateTotalIteration(Double valorCalc) {
        System.out.println("Calculando valorCalc " + valorCalc);
        Integer contIteration = 0;
        do {
            if (valorCalc == 1) {
                contIteration++;
                break;
            }
            valorCalc = calculateIteration(valorCalc);
            contIteration++;


        } while (valorCalc > 0);

        System.out.println("total de interações " + contIteration);
    }


    public static void main(String[] args) {

        calculateTotalIteration(10.00);

        calculateTotalIteration(1.0);

        calculateTotalIteration(6.0);

        calculateTotalIteration(7.0);


    }
}
