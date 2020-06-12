package redes_01;

/**
 *
 * @author Abiud Salazar
 */
public class Paquete {
    protected double tamaño;
    
    public Paquete(double tamaño){
        this.tamaño=tamaño;
    }
    
    public double getTamaño(){
        return this.tamaño;
    }
}
