package redes_01;

import java.util.ArrayList;

/**
 *
 * @author Abiud Salazar
 */
public class Red {
    protected Nodo[] nodos;
    protected Enlace[] enlaces;
    protected int nOrigen, nDestino, nNodos, nEnlaces;
    protected double tPaquete;
    ArrayList<ArrayList<Integer>> caminos;
    
    public Red(){
        
    }
    
    public void leerArchivo(){
        Archivo a=new Archivo();        
        a.leerArchivo();
        this.nodos=a.getNodos();
        this.enlaces=a.getEnlaces();
        this.nOrigen=a.getNOrigen();
        this.nDestino=a.getNDestino();
        this.nNodos=a.getNNodos();
        this.nEnlaces=a.getNEnlaces();        
        this.tPaquete=a.getTPaquete();
    }
    
    public void encontrarRuta(){
        Grafo grafo=new Grafo(this.nNodos);
        for(int i=0;i<this.nEnlaces;i++){
            grafo.agregarEnlace(enlaces[i].getIdNodo1(),enlaces[i].getIdNodo2()); 
        }
        grafo.trazarTodosLosCaminos(nOrigen, nDestino);         
        caminos=grafo.getCaminos();
        
        Enrutador ruta=new Enrutador(nNodos, nEnlaces, nodos, enlaces, findNodoById(nOrigen), findNodoById(nDestino), tPaquete, caminos);
        ruta.calcularMejorCamino();
    }
    
    private Nodo findNodoById(int id){
        for(int i=0;i<this.nNodos;i++){
            if(id==this.nodos[i].getId())
                return this.nodos[i];
        }            
        return null;
    }
}
