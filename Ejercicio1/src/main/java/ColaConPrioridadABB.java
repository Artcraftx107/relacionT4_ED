public class ColaConPrioridadABB<T> {
    private static class Nodo<T> {
        T elemento;
        int prioridad;
        Nodo<T> izquierdo, derecho;

        public Nodo(T elemento, int prioridad) {
            this.elemento = elemento;
            this.prioridad = prioridad;
        }
    }

    private Nodo<T> raiz;

    public ColaConPrioridadABB() {
        raiz = null;
    }

    public void insertar(T elemento, int prioridad) {
        raiz = insertarRec(raiz, elemento, prioridad);
    }

    private Nodo<T> insertarRec(Nodo<T> nodo, T elemento, int prioridad) {
        if (nodo == null) {
            return new Nodo<>(elemento, prioridad);
        }
        if (prioridad < nodo.prioridad) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, elemento, prioridad);
        } else {
            nodo.derecho = insertarRec(nodo.derecho, elemento, prioridad);
        }
        return nodo;
    }

    public T extraerMinimo() {
        if (raiz == null) {
            throw new IllegalStateException("La cola está vacía.");
        }

        Nodo<T> minPadre = null;
        Nodo<T> min = raiz;

        while (min.izquierdo != null) {
            minPadre = min;
            min = min.izquierdo;
        }

        if (minPadre == null) {
            raiz = raiz.derecho;
        } else {
            minPadre.izquierdo = min.derecho;
        }

        return min.elemento;
    }

    public boolean estaVacia() {
        return raiz == null;
    }
}