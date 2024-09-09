import java.io.*;
import java.util.*;

public class MaquinaPilha {
    private Pilha pilha;

    public MaquinaPilha() {
        pilha = new Pilha();
    }

    public void executarArquivo(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                processarInstrucao(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private void processarInstrucao(String instrucao) {
        String[] partes = instrucao.trim().split(" ");
        String comando = partes[0];

        try {
            switch (comando) {
                case "PUSH":
                    int valor = Integer.parseInt(partes[1]);
                    pilha.push(valor);
                    break;
                case "POP":
                    pilha.pop();
                    break;
                case "DUP":
                    pilha.push(pilha.top());
                    break;
                case "SUM":
                    int b = pilha.pop();
                    int a = pilha.pop();
                    pilha.push(a + b);
                    break;
                case "SUB":
                    b = pilha.pop();
                    a = pilha.pop();
                    pilha.push(a - b);
                    break;
                case "MULT":
                    b = pilha.pop();
                    a = pilha.pop();
                    pilha.push(a * b);
                    break;
                case "DIV":
                    b = pilha.pop();
                    a = pilha.pop();
                    pilha.push(a / b);
                    break;
                case "PRINT":
                    System.out.println(pilha.pop());
                    break;
                default:
                    System.err.println("Instrução desconhecida: " + comando);
            }
        } catch (Exception e) {
            System.err.println("Erro ao processar instrução '" + instrucao + "': " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Uso: java MaquinaPilha <caminho do arquivo>");
            System.exit(1);
        }

        String caminhoArquivo = args[0];
        MaquinaPilha maquina = new MaquinaPilha();
        maquina.executarArquivo(caminhoArquivo);
    }
}
