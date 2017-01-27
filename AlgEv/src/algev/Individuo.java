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
    
    private void randomIndividuo(){
        for(int i=0; i<AlgEv.tamanioProblema; i++ ){
            genes[i] = i;
        }
        Herramientas.shuffleArray(genes);
    }
    
    public void setGene(int posicion, int nuevovalor) {
        genes[posicion] = nuevovalor;
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

    //Recalcular fitness con busqueda local para baldwiniano y lamarckiano
    void calcFitnessMejorado(/*AlgEv al*/) {
        //Busqueda local
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Metodo solucion
    public int[] getSolucion(){
        int tsolucion=mejorsolucion.length;
        
        for(int i=0; i<tsolucion; i++ ){
            mejorsolucion[i] = i;
        }
        Herramientas.shuffleArray(mejorsolucion);
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
