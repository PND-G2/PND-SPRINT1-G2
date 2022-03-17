package Util;

public class Aleatorio {

    private static void Randomizador() {
        int cero = 0;
        int uno = 0;
        int dos = 0;
        int tres = 0;
        int cuatro = 0;
        int cinco = 0;

        for (int i = 0; i <= 10; i++) {
            int solucion = generadorRandom();
            if (solucion == 0) {
                cero++;
            } else if (solucion == 1) {
                uno++;
            } else if (solucion == 2) {
                dos++;
            } else if (solucion == 3) {
                tres++;
            } else if (solucion == 4) {
                cuatro++;
            } else {
                cinco++;
            }
        }
        System.out.println("Número 0 -> " + cero + "\n" + "Número 1 -> " + uno + "\n" + "Número 2 -> " + dos + "\n" + "Número 3 -> " + tres + "\n" + "Número 4 -> " + cuatro + "\n" + "Número 5 -> " + cinco);

    }

    public static int generadorRandom() {
        double rate0 = 0.25; //     00-25 %
        double rate1 = 0.30; //     26-55 %
        double rate2 = 0.20; //     56-75 %
        double rate3 = 0.10; //     76-85 %
        double rate4 = 0.05; //     86-90 %
        double rate5 = 0.02; //     92-93 %
        double rate6 = 0.02; //     94-95 %
        double rate7 = 0.02; //     96-97 %
        double rate8 = 0.02; //     98    %
        double rate9 = 0.01; //     99    %
        double rate10 = 0.01;//     100   %

        double randomNumber;
        randomNumber = Math.random();

        if (randomNumber >= 0 && randomNumber <= rate0) {
            return 0;
        } else if (randomNumber >= rate0 / 100 && randomNumber <= rate0 + rate1) {
            return 1;
        } else if (randomNumber >= rate0 + rate1 && randomNumber <= rate0 + rate1 + rate2) {
            return 2;
        } else if (randomNumber >= rate0 + rate1 + rate2 && randomNumber <= rate0 + rate1 + rate2 + rate3) {
            return 3;
        } else if (randomNumber >= rate0 + rate1 + rate2 + rate3 && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4) {
            return 4;
        } else if (randomNumber >= rate0 + rate1 + rate2 + rate3 + rate4 && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4 + rate5) {
            return 5;
        } else if (randomNumber >= rate0 + rate1 + rate2 + rate3 + rate4 + rate5 && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4 + rate5 + rate6) {
            return 6;
        } else if (randomNumber >= rate0 + rate1 + rate2 + rate3 + rate4 + rate5 + rate6 && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4 + rate5 + rate6 + rate7) {
            return 7;
        } else if (randomNumber >= rate0 + rate1 + rate2 + rate3 + rate4 + rate5 + rate6 + rate7 && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4 + rate5 + rate6 + rate7 + rate8) {
            return 8;
        } else if (randomNumber >= rate0 + rate1 + rate2 + rate3 + rate4 + rate5 + rate6 + rate7 + rate8 && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4 + rate5 + rate6 + rate7 + rate8 + rate9) {
            return 9;
        } else if (randomNumber >= rate0 + rate1 + rate2 + rate3 + rate4 + rate5 + rate6 + rate7 + rate8 + rate9 && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4 + rate5 + rate6 + rate7 + rate8 + rate9 + rate10) {
            return 10;
        }
        return -1;
    }

    public static int probablidadLesion() {
        int tiempoLesion = 0;

        double rate0 = 0.95;
        double rate1 = 0.01;
        double rate2 = 0.01;
        double rate3 = 0.01;
        double rate4 = 0.01;
        double rate5 = 0.01;

        double randomNumber;
        randomNumber = Math.random();

        if (randomNumber >= 0 && randomNumber <= rate0) {
            tiempoLesion = 0;
        } else if (randomNumber >= rate0 / 100 && randomNumber <= rate0 + rate1) {
            tiempoLesion = 1;
        } else if (randomNumber >= rate0 + rate1 && randomNumber <= rate0 + rate1 + rate2) {
            tiempoLesion = 2;
        } else if (randomNumber >= rate0 + rate1 + rate2 && randomNumber <= rate0 + rate1 + rate2 + rate3) {
            tiempoLesion = 3;
        } else if (randomNumber >= rate0 + rate1 + rate2 + rate3 && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4) {
            tiempoLesion = 4;
        } else if (randomNumber >= rate0 + rate1 + rate2 + rate3 + rate4 && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4 + rate5) {
            tiempoLesion = 5;
        }


        return tiempoLesion;
    }
}
