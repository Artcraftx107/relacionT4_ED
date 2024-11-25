public class DiccionarioABB <K extends Comparable<K>, V>{
    public static class Nodo<K, V>{
        K clave;
        V valor;
        Nodo<K, V> izquierdo, derecho;

        public Nodo(K clave, V v){
            this.clave=clave;
            this.valor=v;
        }
    }

    private Nodo<K, V> raiz;

    public DiccionarioABB(){
        raiz=null;
    }

    public void insertar(K clave, V valor){
        raiz=insertarRec(raiz, clave, valor);
    }

    private Nodo<K,V> insertarRec(Nodo<K,V> raiz, K clave, V valor) {
        if(raiz==null){
            return new Nodo<>(clave, valor);
        }

        if(clave.compareTo(raiz.clave)<0){
            raiz.izquierdo=insertarRec(raiz.izquierdo, clave, valor);
        } else if (clave.compareTo(raiz.clave)>0) {
            raiz.derecho=insertarRec(raiz.derecho, clave, valor);
        }else {
            raiz.valor=valor;
        }
        return raiz;
    }

    public V buscar(K clave){
        Nodo<K, V> nodo = buscarRec(raiz, clave);
        return nodo == null ? null: nodo.valor;
    }

    private Nodo<K,V> buscarRec(Nodo<K,V> raiz, K clave) {
        if(raiz==null){
            return null;
        }

        if(clave.compareTo(raiz.clave)<0){
            return buscarRec(raiz.izquierdo, clave); 
        } else if (clave.compareTo(raiz.clave)>0) {
            return buscarRec(raiz.derecho, clave);
        }else {
            return raiz;
        }
    }
}
