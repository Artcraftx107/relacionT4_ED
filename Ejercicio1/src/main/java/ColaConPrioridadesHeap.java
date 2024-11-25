public class ColaConPrioridadesHeap<T> {
    private static class Nodo<T> {
        T elemento;
        int prioridad;

        public Nodo(T elemento, int prioridad) {
            this.elemento = elemento;
            this.prioridad = prioridad;
        }
    }

    private Nodo<T>[] heap;
    private int tamaño;

    @SuppressWarnings("unchecked")
    public ColaConPrioridadesHeap() {
        heap = new Nodo[10];
        tamaño = 0;
    }

    private void expandirHeap() {
        Nodo<T>[] nuevoHeap = new Nodo[heap.length * 2];
        System.arraycopy(heap, 0, nuevoHeap, 0, heap.length);
        heap = nuevoHeap;
    }

    public void insertar(T elemento, int prioridad) {
        if (tamaño == heap.length) {
            expandirHeap();
        }
        heap[tamaño] = new Nodo<>(elemento, prioridad);
        burbujearHaciaArriba(tamaño);
        tamaño++;
    }

    private void burbujearHaciaArriba(int i) {
        while (i > 0) {
            int padre = (i - 1) / 2;
            if (heap[i].prioridad >= heap[padre].prioridad) {
                break;
            }
            intercambiar(i, padre);
            i = padre;
        }
    }

    public T extraerMinimo() {
        if (tamaño == 0) {
            throw new IllegalStateException("La cola está vacía.");
        }
        T minimo = heap[0].elemento;
        heap[0] = heap[tamaño - 1];
        tamaño--;
        burbujearHaciaAbajo(0);
        return minimo;
    }

    private void burbujearHaciaAbajo(int i) {
        while (true) {
            int izquierdo = 2 * i + 1;
            int derecho = 2 * i + 2;
            int menor = i;

            if (izquierdo < tamaño && heap[izquierdo].prioridad < heap[menor].prioridad) {
                menor = izquierdo;
            }
            if (derecho < tamaño && heap[derecho].prioridad < heap[menor].prioridad) {
                menor = derecho;
            }
            if (menor == i) {
                break;
            }
            intercambiar(i, menor);
            i = menor;
        }
    }

    private void intercambiar(int i, int j) {
        Nodo<T> temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }
}