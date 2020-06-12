package redes_01;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Abiud Salazar
 */
public class Archivo {
    public File f;
    public String line;
    public int nNodos;
    public int nEnlaces;
    public double tPaq;
    public String nOrigen;
    public String nDestino;
    public ArrayList<String[]> nodos=new ArrayList<>();
    public ArrayList<String[]> enlaces=new ArrayList<>();
    Nodo[] nodosN;
    
    public Archivo(){
        f = new File("info.txt");
    }
    
    public Nodo[] getNodos(){  
        this.nodosN=new Nodo[nNodos];
        for(int i=0; i<this.nNodos;i++){
            int auxId=Integer.parseInt(this.nodos.get(i)[0]);
            double auxTC=Double.parseDouble(this.nodos.get(i)[1]);
            nodosN[i]=(new Nodo(auxId,auxTC));
            if(auxId==Integer.parseInt(nOrigen) || auxId==Integer.parseInt(nDestino))
                nodosN[i].NodoIsTarget();
        }            
        return nodosN;
    }
    
    public Enlace[] getEnlaces(){
        Enlace[] enlace=new Enlace[nEnlaces];
        for(int i=0; i<nEnlaces;i++){
            int auxN1=Integer.parseInt(this.enlaces.get(i)[0]);
            int auxN2=Integer.parseInt(this.enlaces.get(i)[1]);
            double auxDis=Double.parseDouble(this.enlaces.get(i)[3]);
            double auxVel=Double.parseDouble(this.enlaces.get(i)[2]);
            double auxDC=Double.parseDouble(this.enlaces.get(i)[4]);
            double auxDU=Double.parseDouble(this.enlaces.get(i)[5]);
            enlace[i]=(new Enlace(findNodoById(auxN1),findNodoById(auxN2),auxDis, auxVel, auxDC, auxDU));
        }            
        return enlace;
    }
    
    private Nodo findNodoById(int id){
        for(int i=0;i<this.nNodos;i++){
            if(id==this.nodosN[i].getId())
                return this.nodosN[i];
        }            
        return null;
    }
    
    public void leerArchivo(){
        String[] st;
        //BufferedReader in = new BufferedReader(new FileReader(f));    
        try{            
            DataInputStream in = new DataInputStream(new FileInputStream(f));            
            line=in.readLine();
            //System.out.println(line);
            st=line.split(",");
            nNodos=Integer.parseInt(st[0]);
            nEnlaces=Integer.parseInt(st[1]);
            
            for(int i=0; i<nNodos;i++){
                line=in.readLine();
                st=line.split(",");
                nodos.add(st);
            }
            
            for(int i=0; i<nEnlaces;i++){
                line=in.readLine();
                st=line.split(",");
                enlaces.add(st);
            }   
            line=in.readLine();  
            
            tPaq=Double.parseDouble(line);
            
            line=in.readLine();
            st=line.split(",");
            nOrigen=st[0];
            nDestino=st[1];       
            in.close();
        }
        catch (IOException e) { 
            System.out.println(e.toString());
        }
    }
    
    public void imprimirArchivo(){
        System.out.println("# NODOS: "+ nNodos+"  # ENLACES: "+nEnlaces);
        for(int i=0;i<nNodos;i++){            
            System.out.println("# NODO: "+ nodos.get(i)[0]+"  T. COLA: "+nodos.get(i)[1]);
        }
        for(int i=0;i<nEnlaces;i++){            
            System.out.println("nO: "+ enlaces.get(i)[0]+" nD: "+enlaces.get(i)[1]+" v: "+enlaces.get(i)[2]);
            System.out.println("dis: "+ enlaces.get(i)[3]+" DC: "+enlaces.get(i)[4]+" DU: "+enlaces.get(i)[5]);   
            System.out.println();
        }
        System.out.println("Tam Transferencia: "+tPaq);
        System.out.println("NODO O: "+ nOrigen+"  NODO D: "+nDestino);
    }

    int getNOrigen() {
        return (Integer.parseInt(this.nOrigen));
    }

    int getNDestino() {
        return (Integer.parseInt(this.nDestino));
    }

    int getNNodos() {
        return nNodos;
    }

    int getNEnlaces() {
        return nEnlaces;
    }

    double getTPaquete() {
        return tPaq;
    }
}
