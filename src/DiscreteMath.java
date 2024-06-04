import java.util.ArrayList;
import java.util.List;

public class DiscreteMath {
    public static class Intervalo {
        private double limiteInferior;
        private double limiteSuperior;
        private int frecuenciaAbsoluta;

        public Intervalo(double limiteInferior, double limiteSuperior) {
            this.limiteInferior = limiteInferior;
            this.limiteSuperior = limiteSuperior;
            this.frecuenciaAbsoluta = 0;
        }

        public double getLimiteInferior() {
            return limiteInferior;
        }

        public double getLimiteSuperior() {
            return limiteSuperior;
        }

        public int getFrecuenciaAbsoluta() {
            return frecuenciaAbsoluta;
        }

        public void incrementarFrecuencia() {
            frecuenciaAbsoluta++;
        }
    }

    public static class TablaDescriptiva {
        private List<Intervalo> intervalos;
        private double rango;
        private double amplitudIntervalo;
        private double[][] datos; // Agregamos una referencia a los datos

        public TablaDescriptiva(double[][] datos, int numeroClases) {
            this.datos = datos; // Guardamos la referencia a los datos
            calcularRango(datos);
            calcularAmplitudIntervalo(numeroClases);
            generarIntervalos(numeroClases, datos);
            calcularFrecuencias(datos);
        }

        private void calcularRango(double[][] datos) {
            double max = datos[0][0];
            double min = datos[0][0];
            for (double[] dato : datos) {
                if (dato[0] > max) {
                    max = dato[0];
                }
                if (dato[0] < min) {
                    min = dato[0];
                }
            }
            rango = max - min;
        }

        private void calcularAmplitudIntervalo(int numeroClases) {
            amplitudIntervalo = rango / numeroClases;
        }

        private void generarIntervalos(int numeroClases, double[][] datos) {
            intervalos = new ArrayList<>();
            double limiteInferior = datos[0][0];
            double limiteSuperior = limiteInferior + amplitudIntervalo;
            for (int i = 0; i < numeroClases; i++) {
                intervalos.add(new Intervalo(limiteInferior, limiteSuperior));
                limiteInferior = limiteSuperior;
                limiteSuperior += amplitudIntervalo;
            }
        }

        private void calcularFrecuencias(double[][] datos) {
            for (double[] dato : datos) {
                for (Intervalo intervalo : intervalos) {
                    if (dato[0] >= intervalo.getLimiteInferior() && dato[0] < intervalo.getLimiteSuperior()) {
                        intervalo.incrementarFrecuencia();
                        break;
                    }
                }
            }
        }

        public void generarTabla() {
            System.out.println("Tabla Descriptiva:");
            System.out.println("Intervalo\tFrecuencia Absoluta\tPunto Medio\tFrecuencia Acumulada\tFrecuencia Relativa\tFrecuencia Relativa Acumulada\tPorcentaje");

            int frecuenciaAcumulada = 0; // reiniciamos la frecuencia acumulada para cada intervalo

            for (Intervalo intervalo : intervalos) {
                frecuenciaAcumulada += intervalo.getFrecuenciaAbsoluta();
                double puntoMedio = (intervalo.getLimiteInferior() + intervalo.getLimiteSuperior()) / 2;
                double frecuenciaRelativa = (double) intervalo.getFrecuenciaAbsoluta() / datos.length;
                double frecuenciaRelativaAcumulada = (double) frecuenciaAcumulada / datos.length;
                double porcentaje = frecuenciaRelativa * 100;

                System.out.printf("%.2f - %.2f\t%d\t%.2f\t%d\t%.2f\t%.2f\t%.2f%%\n",
                        intervalo.getLimiteInferior(), intervalo.getLimiteSuperior(), intervalo.getFrecuenciaAbsoluta(),
                        puntoMedio, frecuenciaAcumulada, frecuenciaRelativa, frecuenciaRelativaAcumulada * 100, porcentaje);
            }
        }

    }
}








