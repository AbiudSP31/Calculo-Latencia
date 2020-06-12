package redes_01;

import java.util.ArrayList; 
import java.util.List; 
/**
 *
 * @author Abiud Salazar
 */
public class Grafo {
    //No. de vértices del grafo
    private int v;        
    //lista de adyacencia
    private ArrayList<Integer>[] adjList;  
    //Lista de listas de caminos
    public ArrayList<ArrayList<Integer>> caminos=new ArrayList<>();
      
    public Grafo(int vertices){           
        this.v = vertices;           
        initAdjList();  
    } 
      
    private void initAdjList(){ 
        adjList = new ArrayList[v];           
        for(int i = 0; i < v; i++) { 
            adjList[i] = new ArrayList<>(); 
        }         
    } 
      
    public void agregarEnlace(int u, int v){ 
        //Se agrega el enlace entre u y v, y viceversa 
        adjList[u].add(v);  
        adjList[v].add(u);
    } 
      
    public void trazarTodosLosCaminos(int s, int d){ 
        boolean[] isVisited = new boolean[v]; 
        ArrayList<Integer> pathList = new ArrayList<>();           
        //se agrega el inicio a la lista de caminos
        pathList.add(s);           
        //Se llama al metodo recursivo para encontrar todos los caminos 
        getTodosLosCaminosHasta(s, d, isVisited, pathList); 
        
        /*System.out.println("Caminos");
        for(int i=0; i<caminos.size();i++){
            for(int j=0; j<caminos.get(i).size();j++){
                System.out.print(caminos.get(i).get(j)+" ");
            }
            System.out.println();
        }*/
    } 
  
    // A recursive function to print 
    // all paths from 'u' to 'd'. 
    // isVisited[] keeps track of 
    // vertices in current path. 
    // localPathList<> stores actual 
    // vertices in the current path 
    private void getTodosLosCaminosHasta(Integer u, Integer d, boolean[] isVisited, List<Integer> localPathList) {           
        isVisited[u] = true; 
          
        if (u.equals(d)){             
            ArrayList<Integer> aux=new ArrayList<>();
            for(int i=0; i<localPathList.size();i++){
                aux.add(localPathList.get(i));
            }
            caminos.add(aux);
            
            isVisited[u]= false; 
            return ; 
        } 
          
        for (Integer i : adjList[u]){ 
            if (!isVisited[i]){ 
                localPathList.add(i); 
                getTodosLosCaminosHasta(i, d, isVisited, localPathList); 
                  
                localPathList.remove(i); 
            } 
        } 
          
        //Marca el nodo actual
        isVisited[u] = false; 
    } 
    
    public ArrayList<ArrayList<Integer>> getCaminos(){
        return this.caminos;
    }
}
