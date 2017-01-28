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
        //tamanioPoblacion = numeroindividuos;
        individuos = new Individuo[numeroindividuos];   
        tamanioPoblacion = numeroindividuos;
        
        for(int i=0; i<tamanioPoblacion; i++){
            individuos[i] = new Individuo();
        }
    }
    
    //Metodo para conocer el tamanio de la poblacion
    public int tamanioPoblacion(){
        return tamanioPoblacion;
    }
    
    //Metodo para insertar un nuevo individuo dada su posicion y el valor 
    public void setIndividuo(int posicion, Individuo valor){
        individuos[posicion] = valor;
    }
    
    //Metodo para consultar un individuo dada su posicion
    public Individuo getIndividuo(int posicion){
        return individuos[posicion];
    }
    
    //Metodo para consultar toda la poblacion
    public Individuo[] getTodosIndividuos() {
        return individuos;
    }

    //Metodo para quedarse con el mejor
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

//Métodos para buscar y eliminar
    public int buscaElemento(Individuo ele){
        int posicion = -1;
        boolean encontrado = false;
        
        for(int i=0; i<tamanioPoblacion && !encontrado ; i++){
           if(individuos[i] == ele){
              encontrado = true;
              posicion=i; 
           }
        }
        return posicion;
      }
      
    public void eliminaPosicion(int pos){
        if(pos>-1 && pos<tamanioPoblacion){
           for(int i=pos; i<tamanioPoblacion ; i++){
              individuos[i] = individuos[i+1];
           }
           tamanioPoblacion--;
        }
      }

    public void eliminaElemento(Individuo ele){
         int posicion;
    
         posicion= buscaElemento(ele);
         
         if(posicion > -1){
           eliminaPosicion(posicion);
         }
    }
    
    public void calcFitnessMejoradoBaldwin(/*AlgEv al*/) {
        for (int i = 0; i < individuos.length; i++) {
            individuos[i].calcFitnessMejoradoBaldwin(/*al*/);
        }
    }
    
    public void calcFitnessMejoradoLamarck(/*AlgEv al*/) {
        for (int i = 0; i < individuos.length; i++) {
            individuos[i].calcFitnessMejoradoLamarck(/*al*/);
        }
    }
    
}
