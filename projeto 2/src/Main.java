import java.io.*;
import java.util.*;

class AgendaPessoal {
    private static final String ARQUIVO_COMPROMISSOS = "compromissos.txt";
    private final List<String> compromissos;

    public AgendaPessoal() {
        compromissos = new ArrayList<>();
        carregarCompromissos();
    }

    private void carregarCompromissos() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_COMPROMISSOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                compromissos.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar compromissos: " + e.getMessage());
        }
    }

    private void salvarCompromissos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_COMPROMISSOS))) {
            for (String compromisso : compromissos) {
                bw.write(compromisso);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar compromissos: " + e.getMessage());
        }
    }

    public void adicionarCompromisso(String compromisso) {
        compromissos.add(compromisso);
        salvarCompromissos();
        System.out.println("Compromisso adicionado: " + compromisso);
    }

    public void listarCompromissos() {
        if (compromissos.isEmpty()) {
            System.out.println("Nenhum compromisso agendado.");
        } else {
            System.out.println("Compromissos:");
            for (int i = 0; i < compromissos.size(); i++) {
                System.out.println((i + 1) + ". " + compromissos.get(i));
            }
        }
    }

    public void removerCompromisso(int indice) {
        if (indice < 1 || indice > compromissos.size()) {
            System.out.println("Índice inválido.");
            return;
        }
        String compromissoRemovido = compromissos.remove(indice - 1);
        salvarCompromissos();
        System.out.println("Compromisso removido: " + compromissoRemovido);
    }

    public static void main(String[] args) {
        AgendaPessoal agenda = new AgendaPessoal();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nAgenda Pessoal");
            System.out.println("1. Adicionar Compromisso");
            System.out.println("2. Listar Compromissos");
            System.out.println("3. Remover Compromisso");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite o compromisso: ");
                    String compromisso = scanner.nextLine();
                    agenda.adicionarCompromisso(compromisso);
                    break;
                case 2:
                    agenda.listarCompromissos();
                    break;
                case 3:
                    agenda.listarCompromissos();
                    System.out.print("Digite o número do compromisso a ser removido: ");
                    int indice = scanner.nextInt();
                    agenda.removerCompromisso(indice);
                    break;
                case 4:
                    System.out.println("Saindo da agenda.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
    }
}
