package redes_01;

import java.util.ArrayList;

/**
 *
 * @author Abiud Salazar
 */
public class Enlace {
    protected Nodo[] nodos;
    protected double distancia;
    protected double velocidad;
    protected double latencia;
    protected double dC, dU;

    public double getdU() {
        return dU;
    }
    protected double paqMaximo;
    protected final double c;
    
    public Enlace(Nodo n1, Nodo n2, double distancia, double velocidad, double dC, double dU){
        this.nodos=new Nodo[2];
        nodos[0]=n1;
        nodos[1]=n2;
        this.distancia=distancia;
        this.velocidad=velocidad;  
        this.c=300000000;
        this.dC=dC;
        this.dU=dU;
        this.paqMaximo=dC+dU;
        calcularLatenciaPrevia();
    }
    
    public void calcularLatenciaPrevia(){           
        double tPropagacion=distancia/c;
        double tTransmision=((this.dC+this.dU)*8)/(velocidad*1000*1000);
        this.latencia=tPropagacion+tTransmision;
    }
    
    public double getLatencia(Nodo destino){     
        
        return (this.latencia+destino.getTCola());
    }
    
    public ArrayList<Paquete> enviar(ArrayList<Paquete> pkt){
        ArrayList<Paquete> paquetes=new ArrayList<>();
        if(pkt.size()==0)
            paquetes.add(new Paquete(this.paqMaximo));
        else{
            for(int i=0; i<pkt.size();i++){
                if(pkt.get(i).getTamaño()<=this.paqMaximo){
                    paquetes.add(pkt.get(i));
                }
                else{                    
                    double tPaq=pkt.get(i).getTamaño();
                    while(tPaq>this.paqMaximo){
                        paquetes.add(new Paquete(this.paqMaximo));
                        tPaq=tPaq-this.paqMaximo;
                    }
                    paquetes.add(new Paquete(tPaq));
                }
            }
        }
        return paquetes;
    }
    
    public boolean isEnlace(int a, int b){
        if((a==this.nodos[0].getId() || a==this.nodos[1].getId()) && (b==this.nodos[0].getId() || b==this.nodos[1].getId()))
            return true;
        return false;
    }
    
    public int getIdNodo1(){
        return nodos[0].getId();
    }
    
    public int getIdNodo2(){
        return nodos[1].getId();
    }
}
