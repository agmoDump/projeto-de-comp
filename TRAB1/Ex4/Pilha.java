import java.util.ArrayList;

public class Pilha {
    private ArrayList<Integer> elementos;

    public Pilha() {
        elementos = new ArrayList<>();
    }

    public void push(int valor) {
        elementos.add(valor);
    }

    public int pop() {
        if (!isEmpty()) {
            return elementos.remove(elementos.size() - 1);
        } else {
            throw new RuntimeException("Pilha vazia. Não é possível executar pop.");
        }
    }

    public int top() {
        if (!isEmpty()) {
            return elementos.get(elementos.size() - 1);
        } else {
            throw new RuntimeException("Pilha vazia. Não é possível acessar o topo.");
        }
    }

    public boolean isEmpty() {
        return elementos.isEmpty();
    }

    public int size() {
        return elementos.size();
    }

    @Override
    public String toString() {
        return elementos.toString();
    }
}
