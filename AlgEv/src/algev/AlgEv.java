/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algev;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author victoria
 */
public class AlgEv { 
    public static int tamanioProblema;
    public static int [][] distancias;
    public static int [][] pesos;
    
    private static final double mutacion = 0.015;
    private static final int numeroGeneraciones = 90;
    private static final int tamTorneo = 5;
    
    public static void leerFicheros(String fichero) throws FileNotFoundException{
        Scanner lector = new Scanner(new File(fichero));
        tamanioProblema = lector.nextInt();
        distancias = new int [tamanioProblema][tamanioProblema];
        pesos = new int [tamanioProblema][tamanioProblema];
        
        for(int i=0; i<tamanioProblema; i++){
            for(int j=0; j<tamanioProblema; j++){
                distancias[i][j]=lector.nextInt();
            }
        }
        
        for(int i=0; i<tamanioProblema; i++){
            for(int j=0; j<tamanioProblema; j++){
                pesos[i][j]=lector.nextInt();
            }
        }
    }
    
    public int tamanio(){
        return tamanioProblema;
    }
    
    public Individuo [] Seleccion(Poblacion mipoblacion){
        Individuo[] padres = new Individuo[2];
        padres[0] = new Individuo();
        padres[1] = new Individuo();
        
        Poblacion torneo = new Poblacion(tamTorneo);
        Random ran = new Random();
        
        int indicePoblacion; 
        int mipobtamanio = mipoblacion.tamanioPoblacion();
	//Set<Integer> selectedIndiv = new HashSet<>();

        indicePoblacion = ran.nextInt(mipobtamanio);
        
        for(int i=0; i<tamTorneo; i++){
            //Individuo torneoIndividuo = mipoblacion.getIndividuo(i);
            

        }
        torneo.getMejor();
 
        return padres;
    }
    
    public Individuo [] Cruce(Individuo padre, Individuo madre){
        Individuo[] hijos = new Individuo[2];
        hijos[0] = new Individuo();
        hijos[1] = new Individuo();
        boolean [] hijo0 = new boolean [tamanioProblema];
        boolean [] hijo1 = new boolean [tamanioProblema];
        int contadorPadre=0, contadorMadre=0;
        
        Arrays.fill(hijo0,false);
        Arrays.fill(hijo1,false);
        
        int posicionAleatoria1 = (int) (Math.random() * tamanioProblema);
        int posicionAleatoria2;
        
        do{//comprobamos que la posicion no es la misma
            posicionAleatoria2 = (int) (Math.random() * tamanioProblema); 
        }while(posicionAleatoria1==posicionAleatoria2);
        
        int inicio = Math.min(posicionAleatoria1, posicionAleatoria2);
        int fin = Math.max(posicionAleatoria1, posicionAleatoria2);
        
        for(int i=inicio; i<fin; i++){
            hijos[0].setGene(i, padre.getGenes(i));
            hijo0[padre.getGenes(i)]=true;
            hijos[1].setGene(i, madre.getGenes(i));
            hijo1[madre.getGenes(i)]=true;
        }
       
        for(int i=0; i<inicio; i++){
            while(hijo0[contadorMadre]){
                contadorMadre++;
            }
            while(hijo1[contadorPadre]){
                contadorPadre++;
            }
            hijos[0].setGene(i,madre.getGenes(contadorMadre));
            hijos[1].setGene(i,padre.getGenes(contadorPadre));
        }
        
        for(int i=fin; i<tamanioProblema; i++){
            while(hijo0[contadorMadre]){
                contadorMadre++;
            }
            while(hijo1[contadorPadre]){
                contadorPadre++;
            }
            hijos[0].setGene(i,madre.getGenes(contadorMadre));
            hijos[1].setGene(i,padre.getGenes(contadorPadre));
        }
        
        return hijos;
    }
    /*
    public void Intercambia(int primero, int segundo){
		int auxiliar;
		
		auxiliar = primero;
		primero = segundo;
		segundo = auxiliar;
    }*/
    
    public void Mutacion(Individuo aMutar){
        for(int i=0; i<aMutar.tamanio(); i++){
            if (Math.random() <= mutacion) {   
                int gen = (int) Math.round(Math.random());
                int aux = aMutar.getGenes(gen);
                aMutar.setGene(gen, aMutar.getGenes(i));
                aMutar.setGene(i, aux);
            }
        }
                 
    }
    
    //Algoritmo simple
    public void AlgoritmoSimple(){
        Poblacion poblacion = new Poblacion(30);
        
        for(int i=0; i<numeroGeneraciones; i++){
            Poblacion nuevapoblacion = new Poblacion(30);
            //Seleccion
            Individuo[] padres = Seleccion(poblacion);
            //Cruce
            Individuo[] hijos = Cruce(padres[0], padres[1]);
            //Mutacion
            Mutacion(hijos[0]);
            Mutacion(hijos[1]);
            
        }
    
    }
    
    //Algoritmo B
    public void AlgoritmoBaldwiniano(){
        Poblacion poblacion = new Poblacion(30);
        
        for(int i=0; i<numeroGeneraciones; i++){
            Poblacion nuevapoblacion = new Poblacion(30);
            //Seleccion
            Individuo[] padres = Seleccion(poblacion);
            //Cruce
            Individuo[] hijos = Cruce(padres[0], padres[1]);
            //Mutacion
            Mutacion(hijos[0]);
            Mutacion(hijos[1]);
            
        }
    }
    
    //Algoritmo L
    public void AlgoritmoLamarckiano(){
        Poblacion poblacion = new Poblacion(30);
        
        for(int i=0; i<numeroGeneraciones; i++){
            Poblacion nuevapoblacion = new Poblacion(30);
            //Seleccion
            Individuo[] padres = Seleccion(poblacion);
            //Cruce
            Individuo[] hijos = Cruce(padres[0], padres[1]);
            //Mutacion
            Mutacion(hijos[0]);
            Mutacion(hijos[1]);
            
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        leerFicheros("qap.datos/tai256c.dat");
        System.out.println(tamanioProblema);
        
        AlgEv solucion = null;
        
        System.out.println("Elige el algoritmo introducieno:");
        System.out.println("\t-Algoritmo Simple -> A:");
        System.out.println("\t-Algoritmo Baldwiniano -> B:");
        System.out.println("\t-Algoritmo Lamarckiano -> L:");
        
        Scanner scan = new Scanner(System.in);
        String caso = scan.nextLine();
                
        switch(caso){
                case "A":
                    System.out.println("Algoritmo simple");
                    solucion.AlgoritmoSimple();
                    break;
                case "B":
                    System.out.println("Algoritmo Baldwiniano");
                    solucion.AlgoritmoBaldwiniano();
                    break;
                case "C":
                    System.out.println("Algoritmo Lamarckiano");
                    solucion.AlgoritmoLamarckiano();
                    break;
                default:
                    System.out.println("[ERROR]-Opcion invalida.");
        }
                    
    }
    
}