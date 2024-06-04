// En el archivo Main.java
public class Main {
    public static void main(String[] args) {
        double[][] datos = DataSet.getData(); // Corregido para obtener un array bidimensional
        int numeroClases = 5;

        DiscreteMath.TablaDescriptiva tabla = new DiscreteMath.TablaDescriptiva(datos, numeroClases);
        tabla.generarTabla();
    }
}




