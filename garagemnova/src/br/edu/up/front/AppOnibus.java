package br.edu.up.front;

import br.edu.up.entidades.Onibus;
import br.edu.up.persistencia.OnibusPersistencia;
import java.util.List;

public class AppOnibus {
    public static void main(String[] args) {
        int opc;

        do {
            System.out.println("\n\nMenu Ônibus:");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Listar");
            System.out.println("5 - Estatísticas");
            System.out.println("6 - Voltar");

            opc = Console.readInt("Informe a opção: ");

            switch (opc) {
                case 1:
                    // Lógica para cadastrar ônibus
                    Onibus onibus = new Onibus();
                    // Preencha os dados do ônibus, por exemplo:
                    onibus.setNomeLinha(Console.readString("\nInforme o nome da linha:"));
                    onibus.setNumeroLinha(Console.readInt("Informe o número da linha:"));
                    OnibusPersistencia.incluir(onibus);
                    System.out.println("\nÔnibus cadastrado com sucesso!");
                    break;
                case 2:
                    // Lógica para atualizar ônibus
                    int idOnibusAtualizar = Console.readInt("\nInforme o ID do ônibus que deseja atualizar:");
                    Onibus onibusAtualizar = OnibusPersistencia.procurarPorId(idOnibusAtualizar);
                    if (onibusAtualizar != null) {
                        onibusAtualizar.setNomeLinha(Console.readString("Informe o novo nome da linha:"));
                        onibusAtualizar.setNumeroLinha(Console.readInt("Informe o novo número da linha:"));
                        OnibusPersistencia.alterar(onibusAtualizar);
                        System.out.println("\nÔnibus atualizado com sucesso!");
                    } else {
                        System.out.println("\nNão existe um ônibus com esse ID!");
                    }
                    break;
                case 3:
                    // Lógica para excluir ônibus
                    int idOnibusExcluir = Console.readInt("\nInforme o ID do ônibus que deseja excluir:");
                    Onibus onibusExcluir = OnibusPersistencia.procurarPorId(idOnibusExcluir);
                    if (onibusExcluir != null) {
                        OnibusPersistencia.excluir(onibusExcluir);
                        System.out.println("\nÔnibus deletado com sucesso!");
                    } else {
                        System.out.println("\nNão existe um ônibus com esse ID!");
                    }
                    break;
                case 4:
                    // Lógica para listar ônibus
                    System.out.println("\nLista de Ônibus:");
                    List<Onibus> onibusList = OnibusPersistencia.getOnibus();
                    if (!onibusList.isEmpty()) {
                        for (Onibus o : onibusList) {
                            System.out.println("ID: " + o.getId());
                            System.out.println("Nome da linha: " + o.getNomeLinha());
                            System.out.println("Número da linha: " + o.getNumeroLinha());
                        }
                    } else {
                        System.out.println("Nenhum ônibus encontrado.");
                    }
                    break;
                case 5:
                    // Obter a lista de ônibus
                    List<Onibus> onibusListStats = OnibusPersistencia.getOnibus();
                    int totalOnibus = onibusListStats.size();

                    System.out.println("Lista de Ônibus:");
                    for (Onibus onibusItem : onibusListStats) {
                        System.out.println("ID: " + onibusItem.getId());
                        System.out.println("Nome da linha: " + onibusItem.getNomeLinha());
                        System.out.println("Número da linha: " + onibusItem.getNumeroLinha());
                        System.out.println();
                    }

                    System.out.println("Estatísticas de Ônibus:");
                    System.out.println("Total de ônibus registrados: " + totalOnibus);

                    // Verificar se o usuário deseja saber os IDs dos ônibus ociosos/trabalhando
                    String opcaoIDs = Console.readString("\nDeseja obter os IDs dos ônibus ociosos/trabalhando (sim/não)?");
                    if (opcaoIDs.equalsIgnoreCase("sim")) {
                        String opcaoTipo = Console.readString("Deseja os IDs dos ônibus ociosos ou em rota (ociosos/rota)?");

                        if (opcaoTipo.equalsIgnoreCase("ociosos")) {
                            System.out.println("\nIDs dos ônibus ociosos:");
                            for (Onibus onibusItem : onibusListStats) {
                                if (onibusItem.getRota() == null) {
                                    System.out.println("ID: " + onibusItem.getId());
                                }
                            }
                        } else if (opcaoTipo.equalsIgnoreCase("rota")) {
                            System.out.println("\nIDs dos ônibus em rota:");
                            for (Onibus onibusItem : onibusListStats) {
                                if (onibusItem.getRota() != null) {
                                    System.out.println("ID: " + onibusItem.getId() + " está em rota: " + onibusItem.getRota().getNomeRota());
                                }
                            }
                        } else {
                            System.out.println("\nOpção inválida. Os IDs não serão exibidos.");
                        }
                    }
                    break;	
                    
                case 6:
                	Principal.main(args);
                    System.out.println("\nVoltando...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opc != 6);
    }
}
