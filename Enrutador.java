package redes_01;

import java.util.ArrayList;

/**
 *
 * @author Abiud Salazar
 */
public class Enrutador {
    protected double tTransferencia;
    protected Nodo origen, destino;
    protected int nNodos, nEnlaces;
    //protected Grafo grafo;
    protected Nodo[] nodos;
    protected Enlace[] enlaces;
    protected ArrayList<ArrayList<Integer>> caminos;
    protected ArrayList<Double> latencias;
    int indexMejor;
    double latMenor;
    
    public Enrutador( int nNodos, int nEnlaces, Nodo[] nodos, Enlace[] enlaces, Nodo origen, Nodo destino, double tTransferencia, ArrayList<ArrayList<Integer>> caminos){
        this.tTransferencia=tTransferencia;
        this.nNodos=nNodos;
        this.nEnlaces=nEnlaces;
        this.nodos=nodos;
        this.enlaces=enlaces;
        this.origen=origen;
        this.destino=destino;
        //this.grafo=new Grafo(nNodos);
        this.caminos=caminos;
        this.latencias=new ArrayList<>();
    }
    
    public void calcularMejorCamino(){
        ArrayList<Integer> caminoActual;
        ArrayList<Paquete> paquetes=new ArrayList<>();
        System.out.println("Se encontraron "+caminos.size()+" caminos");
        System.out.println("-------------------------------------------------------------------------------");
        for(int cCam=0;cCam<caminos.size();cCam++){
            caminoActual=caminos.get(cCam);            
            double latCam=0;
            //System.out.println("Latencia del Camino "+(cCam+1));
            for(int i=0, j=1;j<caminoActual.size();i++,j++){
                double lat=0;
                for(int k=0;k<enlaces.length;k++){
                    if(enlaces[k].isEnlace(caminoActual.get(i), caminoActual.get(j))){
                        lat=enlaces[k].getLatencia(findNodoById(caminoActual.get(j)));   
                        paquetes=enlaces[k].enviar(paquetes);
                        //System.out.println("#paq :"+paquetes.size());
                        lat=paquetes.size()*lat;
                        break;
                    }                    
                }
                latCam=latCam+lat;
                //System.out.println(i+":"+lat);
            }
            //System.out.println(cCam+":::"+latCam);
            latencias.add(latCam);
            System.out.println("Camino "+caminos.get(cCam));
            System.out.println("Latencia del Camino: "+latencias.get(cCam));
            System.out.println("-------------------------------------------------------------------------------");
            paquetes.clear();
            //caminoActual.clear();
        }
        indexMejor=-1;
        latMenor=latencias.get(0);
        
        for(int i=0; i<latencias.size();i++){
            if(latMenor>=latencias.get(i)){
                latMenor=latencias.get(i);
                indexMejor=i;
            }                
        }
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("El camino con la menor latencia es "+caminos.get(indexMejor)+" con "+latencias.get(indexMejor));
        System.out.println("-------------------------------------------------------------------------------");
        calcularTiempo();
    }
    
    public void calcularTiempo(){
        int n1=caminos.get(indexMejor).get(0);
        int n2=caminos.get(indexMejor).get(1);
        Enlace enlace=findEnlaceByIdNodos(n1,n2);
        int nPaquetes=(int) Math.ceil((tTransferencia*1024*1024*1024)/enlace.getdU());
        System.out.println("Numero paquetes: "+nPaquetes);
        System.out.println("-------------------------------------------------------------------------------");
        double tiempo=latencias.get(indexMejor)*nPaquetes;                
        
        System.out.println("El tiempo total en segundos para una transferencia de "+tTransferencia+"Gb es: "+tiempo+" segundos");
        
        int hor,min,seg;
        hor=(int)tiempo/3600;
        min=((int)tiempo-(3600*hor))/60;
        seg=(int)tiempo-((hor*3600)+(min*60));
        System.out.println("Tiempo Total: "+hor+"h "+min+"m "+seg+"s");
        
    }
    
    private Nodo findNodoById(int id){
        for(int i=0;i<this.nNodos;i++){
            if(id==this.nodos[i].getId())
                return this.nodos[i];
        }            
        return null;
    }
    
    private Enlace findEnlaceByIdNodos(int n1, int n2){        
        for(int i=0; i<nEnlaces;i++){
            if((enlaces[i].getIdNodo1()==n1 || enlaces[i].getIdNodo2()==n1) && enlaces[i].getIdNodo1()==n2 || enlaces[i].getIdNodo2()==n2 )
                return enlaces[i];
        }
        return null;
    }
}
