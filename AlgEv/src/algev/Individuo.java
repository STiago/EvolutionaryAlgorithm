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
public class Individuo {
    
    private int fitness = 0;
    private int[] genes = null;
   
    
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
    
}
