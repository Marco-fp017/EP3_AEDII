package services;

import entities.IDs;
import entities.PilhaDinamica;
import java.util.ArrayList;
import java.util.List;

public class BuscaEmProfundidade {
    private IDs[] edgeTo;   // último vértice encontrado antes de encontrar um vértice 
    private final IDs source;   //id de início da componente
    
    List<IDs> componente = new ArrayList<>();   //lista para guardar a componente conexa que parte do id 'source'

    public BuscaEmProfundidade(List<IDs> Grafo, IDs source) {
        this.edgeTo = new IDs[tamToEdge(Grafo)+1];
        this.source = source;
        bfs(Grafo, source);
    }

    private void bfs(List<IDs> Grafo, IDs s) {
        PilhaDinamica pilha = new PilhaDinamica();  //pilha de IDs
        
        Grafo.get(Grafo.indexOf(s)).setVisitado();  //marcar a fonte como visitada na lista e na pilha
        s.setVisitado();
        pilha.inserirNo(s);  // e colocá-la na pilha.
        
        while(!pilha.estaVazia()){
            IDs v = pilha.removerNo();  // Remova o próximo vértice da pilha e guarda ele
            if(!componente.contains(v)) componente.add(v);
            
            for(IDs w : Grafo.get(Grafo.indexOf(v)).getAdj()){
                if(!w.getVisitado()){   // Para cada vértice adjacente não marcado
                    edgeTo[w.getId()] = v; // indico que o último vértice que eu visitei antes de chegar neste foi o v
                    Grafo.get(Grafo.indexOf(w)).setVisitado();  //marcando todos os vértices adjacentes a v como visitados
                    w.setVisitado();
                    pilha.inserirNo(w); //impilho todos os adjacentes a v na pilha
                    
                    if(!componente.contains(w)) componente.add(w);  //adicionando na lista todos os elementos conexos à source 
                }
            }
        }
    }

    public List<IDs> getComponente() {
        return componente;
    }
    
    public void closeComponente(){
        componente.clear();
    }

    //retorna o tamanho que deve conter o método toEdge
    public int tamToEdge(List<IDs> Grafo){
        int maiorID = 0;
        for(IDs id : Grafo) if(id.getId() > maiorID) maiorID = id.getId();

        return maiorID;
    }

    //verifica se existe um caminho entre quaisquer dois vértices do grafo
    public boolean existeCaminho(IDs inicio, IDs fim){
        if(edgeTo[inicio.getId()] != null && edgeTo[fim.getId()] != null) return true;
        
        return false;
    }
}
