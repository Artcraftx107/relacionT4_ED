public class ColaConPrioridadAVL<T> {
    private static class Nodo<T> {
        T elemento;
        int prioridad;
        int altura;
        Nodo<T> izquierdo, derecho;

        public Nodo(T elemento, int prioridad) {
            this.elemento = elemento;
            this.prioridad = prioridad;
            this.altura = 1;
        }
    }

    private Nodo<T> raiz;

    public ColaConPrioridadAVL() {
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

        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
        return balancear(nodo);
    }

    private int altura(Nodo<T> nodo) {
        return nodo == null ? 0 : nodo.altura;
    }

    private Nodo<T> balancear(Nodo<T> nodo) {
        int balance = altura(nodo.izquierdo) - altura(nodo.derecho);

        if (balance > 1) {
            if (altura(nodo.izquierdo.izquierdo) >= altura(nodo.izquierdo.derecho)) {
                nodo = rotarDerecha(nodo);
            } else {
                nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
                nodo = rotarDerecha(nodo);
            }
        } else if (balance < -1) {
            if (altura(nodo.derecho.derecho) >= altura(nodo.derecho.izquierdo)) {
                nodo = rotarIzquierda(nodo);
            } else {
                nodo.derecho = rotarDerecha(nodo.derecho);
                nodo = rotarIzquierda(nodo);
            }
        }

        return nodo;
    }

    private Nodo<T> rotarDerecha(Nodo<T> nodo) {
        Nodo<T> nuevaRaiz = nodo.izquierdo;
        nodo.izquierdo = nuevaRaiz.derecho;
        nuevaRaiz.derecho = nodo;

        nodo.altura = Math.max(altura(nodo.izquierdo), altura(nodo.derecho)) + 1;
        nuevaRaiz.altura = Math.max(altura(nuevaRaiz.izquierdo), altura(nuevaRaiz.derecho)) + 1;

        return nuevaRaiz;
    }

    private Nodo<T> rotarIzquierda(Nodo<T> nodo) {
        Nodo<T> nuevaRaiz = nodo.derecho;
        nodo.derecho = nuevaRaiz.izquierdo;
        nuevaRaiz.izquierdo = nodo;

        nodo.altura = Math.max(altura(nodo.izquierdo), altura(nodo.derecho)) + 1;
        nuevaRaiz.altura = Math.max(altura(nuevaRaiz.izquierdo), altura(nuevaRaiz.derecho)) + 1;

        return nuevaRaiz;
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
