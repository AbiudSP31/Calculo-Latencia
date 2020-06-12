package redes_01;

/**
 *
 * @author Abiud Salazar
 */
public class main {
    public static void main(String[] args) {
        Red red=new Red();
        red.leerArchivo();
        red.encontrarRuta();
        
        //Archivo archivo=new Archivo();
        
        //archivo.leerArchivo();
        //archivo.imprimirArchivo();
        
        // Create a sample graph 
        /*Grafo g = new Grafo(6); 
        g.addEdge(0,1); 
        g.addEdge(1,2); 
        g.addEdge(1,4); 
        g.addEdge(2,3); 
        g.addEdge(3,4); 
        g.addEdge(3,5); 
        g.addEdge(4,5); 
      
        // arbitrary source 
        int s = 0; 
      
        // arbitrary destination 
        int d = 5; 
      
        System.out.println("Encontrando todos los caminos de "+s+" a "+d); 
        g.printAllPaths(s, d); */
    }
}
