/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algev;
/**
 *
 * @author victoria
 */
public class Individuo{
    
    private int fitness = 0;
    private int[] genes = null;
    private int[] mejorsolucion;
   
    
    public Individuo(){
        genes = new int [AlgEv.tamanioProblema];
        randomIndividuo();
        calculaFitness();
        
    }
    
    
    private void copia(Individuo otro){
        this.genes = otro.getGenes();
        this.fitness = otro.getFitness();
        this.mejorsolucion = otro.mejorsolucion;
    }
    
    //Constructor de copia
    public Individuo(Individuo otro){
        copia(otro);
    }
    
    private void randomIndividuo(){
        for(int i=0; i<AlgEv.tamanioProblema; i++ ){
            genes[i] = i;
        }
        Herramientas.shuffleArray(genes);
    }
    
    public void setGene(int posicion, int nuevovalor) {
        genes[posicion] = nuevovalor;
    }
    
    public int[] getGenes() { 
        return genes;
    }
    
    public int getGenes(int posicion) {
        return this.genes[posicion];
    }

    public int tamanio() {
        return genes.length;
    }

    public int getFitness() {
        return fitness;
    }
    
    //Metodo para intercambiar elementos
    public void Intercambia(int primero, int segundo){
		int auxiliar;
		
		auxiliar = primero;
		primero = segundo;
		segundo = auxiliar;
    }
/*    
S = candidato inicial con coste c ( S )

do {

mejor = S

for i =1.. n
for j = i +1.. n
T = S tras intercambiar i con j
if c ( T ) < c ( S )
S = T


} while ( S != mejor )

*/    
    //Calcular 2-opt
    private Individuo greedy(/*Individuo[] mipoblacion*/) {
        //Individuo busqueda = new Individuo();
        double mejorg = 0;
        int mejorpos= 0;
        Individuo s = new Individuo(this);
        Individuo mejor;
        
        s.calculaFitness();
        
        do{
            mejor = s;
                
            for(int i=0; i<genes.length; ++i){
                for(int j=i+1; j<genes.length/*-1*/; ++j){
                    Individuo t = new Individuo(s);
                    Intercambia(t.getGenes(i), t.getGenes(j));
                    t.setGene(i, t.getGenes(i));
                    t.setGene(j, t.getGenes(j));
                    
                    t.nuevoFitness();
                    
                    if(s.getFitness()> t.getFitness()){ 
                        s = new Individuo(t);
                        //mejorind = new Individuo(mipoblacion[mejorpos]);
                    }
                }
            }
            
        }while(s.getFitness()<mejor.getFitness());
        
        return s;
    } 
    
    //Métodos para calcular el Fitness
    public void calculaFitness(){
        fitness = 0;
        int tamPro = AlgEv.tamanioProblema;
        
        for(int i=0; i<tamPro; i++){
            for(int j=0; j<tamPro; j++){
                fitness += AlgEv.pesos[i][j] * AlgEv.distancias[genes[i]][genes[j] ];
            }
        }
    }
    
    public void nuevoFitness(){}

    //Recalcular fitness con busqueda local para baldwiniano y lamarckiano
    void calcFitnessMejoradoBaldwin(/*AlgEv al*/) {
        int tamPro = AlgEv.tamanioProblema;
        Individuo mejors;
        //if (mejors == null) {mejorsolucion
            mejors = greedy();
        //}
        fitness = 0;
        for (int i = 0; i < tamPro; i++) {
            for (int j = 0; j < tamPro; j++) {
                fitness += AlgEv.pesos[i][j] * AlgEv.distancias[mejorsolucion[i]][mejorsolucion[j]];
            }
        }
    }
    
    void calcFitnessMejoradoLamarck(/*AlgEv al*/) {
        int tamPro = AlgEv.tamanioProblema;
        
        if (mejorsolucion == null) {
            //mejorsolucion = greedy(); CAMBIAR!
            genes = mejorsolucion;
        }
        fitness = 0;
        for (int i = 0; i < tamPro; i++) {
            for (int j = 0; j < tamPro; j++) {
                fitness += AlgEv.pesos[i][j] * AlgEv.distancias[mejorsolucion[i]][mejorsolucion[j]];
            }
        }
    }
    
    //Metodo de permutacion
    public int[] getSolucionPermutacion(){
        int tsolucion=genes.length;
        
        for(int i=0; i<tsolucion; i++ ){
            genes[i] = i;
        }
        Herramientas.shuffleArray(genes);
        calculaFitness();
        return mejorsolucion;
    }
    
    
    @Override
    public String toString() {
        String cadena = "[";

        for (int i : genes) {
            cadena += "" + i + " ";
        }

        return cadena.substring(0, cadena.length() - 1) + "]";
}
    
}
