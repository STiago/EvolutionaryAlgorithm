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
public class Poblacion {
    private Individuo [] individuos;
    private int fitnessPoblacion = 0;
    private int tamanioPoblacion = 0;
    
    //Constructor Poblacion
    public Poblacion(int numeroindividuos ) {
        tamanioPoblacion = numeroindividuos;
        individuos = new Individuo[tamanioPoblacion];      
        
        for(int i=0; i<tamanioPoblacion; i++){
            individuos[i] = new Individuo();
        }
    }
    
    public int tamanioPoblacion(){
        return tamanioPoblacion;
    }
    
    public Individuo getIndividuo(int posicion){
        return individuos[posicion];
    }
    
    public Individuo getMejor(){
        Individuo mejor = individuos[0];
        
        for(int i=1; i<tamanioPoblacion; i++){
            if(mejor.getFitness()> getIndividuo(i).getFitness()){
                mejor = getIndividuo(i);
            }
        }
        return mejor;
    }
    
    public void guardarIndividuo(int posicion, Individuo indivi){
        individuos[posicion] = indivi;
    }
    
}
