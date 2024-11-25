public class MultiConjuntoOrdenadoABB <T extends Comparable<T>>{
    public static class Nodo<T>{
        T elemento;
        int frecuencia;
        Nodo<T> izquierdo, derecho;

        public Nodo(T elemento){
            this.elemento=elemento;
            this.frecuencia=1;
        }
    }

    private Nodo<T> raiz;
    public MultiConjuntoOrdenadoABB(){
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
            nodo.izquierdo=insertarRec(nodo.izquierdo, elemento); 
        } else if (elemento.compareTo(nodo.elemento)>0) {
            nodo.derecho=insertarRec(nodo.derecho, elemento);
        }else{
            nodo.frecuencia++;
        }

        return nodo;
    }

    public int frecuencia(T elemento){
        return buscarFrecuencia(raiz, elemento); 
    }

    private int buscarFrecuencia(Nodo<T> raiz, T elemento) {
        if(raiz==null){
            return 0;
        }

        if(elemento.compareTo(raiz.elemento)<0){
            return buscarFrecuencia(raiz.izquierdo, elemento); 
        } else if (elemento.compareTo(raiz.elemento)>0) {
            return buscarFrecuencia(raiz.derecho, elemento);
        }else{
            return raiz.frecuencia;
        }
    }
}
