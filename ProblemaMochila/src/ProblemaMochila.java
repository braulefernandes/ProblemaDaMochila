import java.util.ArrayList;
import java.util.List;

public class ProblemaMochila { //SOLUÇÃO ÓTIMA

    public static void main(String[] args) {

        List<Objeto> objetos = new ArrayList<>();
        
        objetos.add(new Objeto("Câmera", 2, 100));
        objetos.add(new Objeto("Notebook", 1, 60));
        objetos.add(new Objeto("Drone", 3, 120));

        int capacidadeMochila = 4;

        int precoMaximo = resolverProblemaMochila(objetos, capacidadeMochila);

        System.out.println("Preço mais caro possível na mochila: " + precoMaximo);
        System.out.println();
    }

    public static int resolverProblemaMochila(List<Objeto> objetos, int capacidadeMochila) {
        int quantidadeObjetos = objetos.size();

        int[][] tabelaprecos = new int[quantidadeObjetos + 1][capacidadeMochila + 1]; 
        int[][] tabelaDecisoes = new int[quantidadeObjetos + 1][capacidadeMochila + 1];

        for (int objetoAtual = 1; objetoAtual <= quantidadeObjetos; objetoAtual++) {
            Objeto objeto = objetos.get(objetoAtual - 1);
            for (int capacidadeAtual = 0; capacidadeAtual <= capacidadeMochila; capacidadeAtual++) {
                if (objeto.peso <= capacidadeAtual && (objeto.preco + tabelaprecos[objetoAtual - 1][capacidadeAtual - objeto.peso] > tabelaprecos[objetoAtual - 1][capacidadeAtual])) { 
                    tabelaprecos[objetoAtual][capacidadeAtual] = objeto.preco + tabelaprecos[objetoAtual - 1][capacidadeAtual - objeto.peso];
                    tabelaDecisoes[objetoAtual][capacidadeAtual] = 1; 
                } else {
                    tabelaprecos[objetoAtual][capacidadeAtual] = tabelaprecos[objetoAtual - 1][capacidadeAtual];
                    tabelaDecisoes[objetoAtual][capacidadeAtual] = 0;
                }
            }
        }
        
        System.out.println("\nTabela de Decisões na Mochila:\n");
        System.out.print("     ");
        for (int c = 0; c <= capacidadeMochila; c++) {
            System.out.printf("%4d", c);
        }
        System.out.println();

        for (int i = 0; i <= quantidadeObjetos; i++) {
            if (i == 0) {
                System.out.printf("%4d ", i);
            } else {
                System.out.printf("%4d ", i);
            }
            for (int j = 0; j <= capacidadeMochila; j++) {
                System.out.printf("%4d", tabelaDecisoes[i][j]);
            }
            System.out.println();
        }
        System.out.println();
                
        List<Objeto> objetosSelecionados = new ArrayList<>();
        
        int capacidadeAtual = capacidadeMochila;
        for (int objetoAtual = quantidadeObjetos; objetoAtual > 0; objetoAtual--) {
            if (tabelaDecisoes[objetoAtual][capacidadeAtual] == 1) {
                Objeto objeto = objetos.get(objetoAtual - 1);
                objetosSelecionados.add(objeto);
                capacidadeAtual -= objeto.peso;
            }
        }
        
        System.out.println("Tabela de Valores Obtidos na Mochila:\n");
        System.out.print("     ");
        for (int c = 0; c <= capacidadeMochila; c++) {
            System.out.printf("%4d", c);
        }
        System.out.println();

        for (int i = 0; i <= quantidadeObjetos; i++) {
            if (i == 0) {
                System.out.printf("%4d ", i);
            } else {
                System.out.printf("%4d ", i, objetos.get(i - 1).peso);
            }
            for (int j = 0; j <= capacidadeMochila; j++) {
                System.out.printf("%4d", tabelaprecos[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.print("Objetos selecionados: ");
        for (int i = 0; i < objetosSelecionados.size(); i++) {
            if (i == objetosSelecionados.size()-1){
                System.out.print(objetosSelecionados.get(i).nome + ".");
            }
            else{
                System.out.print(objetosSelecionados.get(i).nome + ", ");
            }
        }
        System.out.println();

        return tabelaprecos[quantidadeObjetos][capacidadeMochila];
    }
}
