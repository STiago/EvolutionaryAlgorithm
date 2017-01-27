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
    
    //Actual poblacion
    private Poblacion poblacion;
    private final int tammPoblacion= 100;
    
    /*
    public AlgEv() {
        poblacion = new Poblacion(tammPoblacion); //crea poblacion e inicializa a random
        poblacion.calcFitnessMejorado();
    }*/
    
    public AlgEv(int tamPro, int [][] dist, int [][] pes){
        this.tamanioProblema = tamPro;
        this.distancias = new int[this.tamanioProblema][this.tamanioProblema];
        this.pesos = new int[this.tamanioProblema][this.tamanioProblema];
        //System.arrayCopy(aOrigen,inicioArrayOrigen,aDestino,inicioArrayDestino,numeroElementosACopiar);
        System.arraycopy(dist, 0, this.distancias, 0, dist.length);
        System.arraycopy(pes, 0, this.pesos, 0, pes.length);
    }
        
    //Métoo para leer el fichero de matrices
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
    
    //Metodo para devolver el tamanio del problema
    public int tamanio(){
        return tamanioProblema;
    }
    
    //Método de seleccion del individuo por torneo
    public Individuo [] Seleccion(Poblacion mipoblacion){
        Individuo[] padres = new Individuo[2];
        padres[0] = new Individuo();
        padres[1] = new Individuo();
               
        Poblacion torneo = new Poblacion(tamTorneo);
        Poblacion torneo2 = new Poblacion(tamTorneo);
        
        int mipobtamanio = mipoblacion.tamanioPoblacion();
        //Random ran = new Random();
        //int indicePoblacion; 
        
        for(int i=0; i<tamTorneo; i++){   
            int participante = (int) Math.floor(Math.random() * mipobtamanio);
            int participante2 = (int) Math.floor(Math.random() * mipobtamanio);
            
            torneo.setIndividuo(i,mipoblacion.getIndividuo(participante));
            torneo2.setIndividuo(i,mipoblacion.getIndividuo(participante2));
            //torneo[i] = new Individuo(mipoblacion[participante]);

            //indicePoblacion = ran.nextInt(mipobtamanio);
            //torneo.guardarIndividuo(i, mipoblacion.getIndividuo(indicePoblacion));    
        }
        padres[0] = torneo.getMejor();
        //torneo.eliminaElemento(torneo.getMejor());    
        do{
            padres[1] = torneo2.getMejor();
        }while(padres[0]==padres[1]);
        
        return padres;
    }
    
    //Metodo de cruce
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
    
    //Metodo para intercambiar elementos
    /*
    public void Intercambia(int primero, int segundo){
		int auxiliar;
		
		auxiliar = primero;
		primero = segundo;
		segundo = auxiliar;
    }*/
    
    //Método para realizar la mutacion    
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
    /*
    //Get Fittest
    public Individuo getFittest() {
        return poblacion.getMejor();
    }*/
    
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
    
    //Algoritmo Baldwiniano
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
    
    //Algoritmo Lamarckiano
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
            //Insertamos los hijos en la nueva poblacion
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        leerFicheros("qap.datos/tai256c.dat");
        System.out.println("Tamanio del problema:");
        System.out.println(tamanioProblema);
        
        //Imprime matriz distancias
        System.out.println("Matriz de distancias:");
        for (int x=0; x < distancias.length; x++) {
            for (int y=0; y < distancias.length; y++) {
                System.out.print (distancias[x][y]);
                if (y!=pesos[x].length-1) System.out.print("\t");
            }
        }
        //Imprime matriz pesos
        System.out.println("\nMatriz de pesos:");
        for (int x=0; x < pesos.length; x++) {
            for (int y=0; y < pesos.length; y++) {
                System.out.print (pesos[x][y]);
                if (y!=pesos[x].length-1) System.out.print("\t");
            }
        }
        
        
        int tamPob = tamanioProblema, generations = 100;
	double mutProb = 0.15;
        int [][] d = distancias;
        int [][] p = pesos;      
                
        AlgEv solucion;
        solucion = new AlgEv(tamPob,d,p);
        
        long inicioAlg, finAlg;
        
        System.out.println("Elige el algoritmo introducieno:");
        System.out.println("\t-Algoritmo Simple -> A:");
        System.out.println("\t-Algoritmo Baldwiniano -> B:");
        System.out.println("\t-Algoritmo Lamarckiano -> L:");
        
        Scanner scan = new Scanner(System.in);
        String caso = scan.nextLine();
                
        switch(caso){
                case "A":
                    System.out.println("Algoritmo simple");
                    System.out.println("Ejecutando...");
                    inicioAlg= System.currentTimeMillis();
                    solucion.AlgoritmoSimple();
                    finAlg= System.currentTimeMillis();
                    System.out.println("-------------- Resultados Algoritmo simple --------------");
                    System.out.println("Time: " + (finAlg - inicioAlg) / 1000);
                    break;
                case "B":
                    System.out.println("Algoritmo Baldwiniano");
                    System.out.println("Ejecutando...");
                    inicioAlg= System.currentTimeMillis();
                    solucion.AlgoritmoBaldwiniano();
                    finAlg= System.currentTimeMillis();
                    System.out.println("-------------- Resultados Algoritmo Baldwiniano --------------");
                    System.out.println("Time: " + (finAlg - inicioAlg) / 1000);
                    break;
                case "L":
                    System.out.println("Algoritmo Lamarckiano");
                    System.out.println("Ejecutando...");
                    inicioAlg= System.currentTimeMillis();
                    solucion.AlgoritmoLamarckiano();
                    finAlg= System.currentTimeMillis();
                    System.out.println("-------------- Resultados Algoritmo Lamarckiano --------------");
                    System.out.println("Time: " + (finAlg - inicioAlg) / 1000);
                    break;
                default:
                    System.out.println("[ERROR]-Opcion invalida.");
        }
                    
    }
    
}