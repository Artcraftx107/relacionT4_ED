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
}
