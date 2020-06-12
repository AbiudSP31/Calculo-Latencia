package redes_01;

/**
 *
 * @author Abiud Salazar
 */
public class Nodo {
    protected double tCola;
    protected int id;
    
    public Nodo(int id, double tCola){
        this.tCola=tCola;
        this.id=id;
    }
    
    public double getTCola(){
        return this.tCola;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void NodoIsTarget(){
        this.tCola=0;
    }
}
