import java.util.ArrayList;
import java.util.List;

public class ConjuntoOrdenadoABB <T extends Comparable<T>>{
    public static class Nodo<T>{
        T elemento;
        Nodo<T> izq, der;

        public Nodo(T elemento){
            this.elemento=elemento;
        }
    }

    private Nodo<T> raiz;

    public ConjuntoOrdenadoABB(){
        raiz=null;
    }

    public void insertar(T elemento){
        raiz=insertarRec(raiz, elemento);
    }

    private Nodo<T> insertarRec(Nodo<T> nodo, T elemento) {
        if(nodo==null){
            return new Nodo<>(elemento);
        }

        if(elemento.compareTo(nodo.elemento)<0){
            nodo.izq=insertarRec(nodo.izq, elemento);
        }else if(elemento.compareTo(nodo.elemento)>0){
            nodo.der=insertarRec(nodo.der, elemento);
        }

        return nodo;
    }

    public T minimo(){
        if(raiz==null){
            throw new IllegalStateException("El conjunto esta vacio");
        }

        Nodo<T> actual = raiz;
        while(actual.izq !=null){
            actual=actual.izq;
        }
        return actual.elemento;
    }

    public ConjuntoOrdenadoABB<T> diferenciaSimetrica(ConjuntoOrdenadoABB<T> otro){
        ConjuntoOrdenadoABB<T> result = new ConjuntoOrdenadoABB<>();

        for(T elemento : this.elementos()){
            if(!otro.contiene(elemento)){
                result.insertar(elemento);
            }
        }

        for(T elemento : otro.elementos()){
            if(!this.contiene(elemento)){
                result.insertar(elemento);
            }
        }

        return result;
    }

    private boolean contiene(T elemento) {
        return contieneRec(raiz, elemento);
    }

    private boolean contieneRec(Nodo<T> raiz, T elemento) {
        if(raiz==null){
            return false;
        }

        if(elemento.compareTo(raiz.elemento)<0){
            return contieneRec(raiz.izq, elemento);
        } else if (elemento.compareTo(raiz.elemento)>0) {
            return contieneRec(raiz.der, elemento);
        }else{
            return true;
        }
    }

    public T[] elementos() {
        List<T> elementos = new ArrayList<>();
        recorrerEnOrden(raiz, elementos);
        return (T[]) elementos.toArray();
    }

    private void recorrerEnOrden(Nodo<T> nodo, List<T> elementos) {
        if(nodo!=null){
            recorrerEnOrden(nodo.izq, elementos);
            elementos.add(nodo.elemento);
            recorrerEnOrden(nodo.der, elementos);
        }
    }
}
