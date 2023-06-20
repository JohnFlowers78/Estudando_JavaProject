package br.edu.up.front;

import br.edu.up.entidades.Motorista;
import br.edu.up.persistencia.MotoristaPersistencia;
import java.util.List;

public class AppMotorista {
    public static void main(String[] args) {
        int opc;
        
        do {
            System.out.println("\n\nMenu Motorista:");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Listar");
            System.out.println("5 - Estatísticas");
            System.out.println("6 - Sair");

            opc = Console.readInt("Informe a opção: ");

            switch (opc) {
                case 1:
                    // Lógica para cadastrar motorista
                    Motorista motorista = new Motorista();
                    // Preencha os dados do motorista, por exemplo:
                    System.out.println("\n\nInforme os dados do motorista abaixo, lembrando que o CPF deve conter apenas 11 dígitos!");
                    motorista.setNome(Console.readString("\nInforme o nome do motorista:"));
                    motorista.setCpf(Console.readString("Informe o CPF do motorista:"));
                    motorista.setNumero(Console.readString("Informe o número do motorista:"));

                    MotoristaPersistencia.incluir(motorista);
                    System.out.println("\nMotorista cadastrado com sucesso!");
                    break;
                case 2:
                    // Lógica para atualizar motorista
                    int idMotoristaAtualizar = Console.readInt("\nInforme o ID do motorista que deseja atualizar:");
                    Motorista motoristaAtualizar = MotoristaPersistencia.procurarPorId(idMotoristaAtualizar);
                    if (motoristaAtualizar != null) {
                        motoristaAtualizar.setNome(Console.readString("Informe o novo nome do motorista:"));
                        motoristaAtualizar.setCpf(Console.readString("Informe o novo CPF do motorista:"));
                        motoristaAtualizar.setNumero(Console.readString("Informe o novo número do motorista:"));
                        MotoristaPersistencia.alterar(motoristaAtualizar);
                        System.out.println("\nMotorista atualizado com sucesso!");
                    } else {
                        System.out.println("\nNão existe um motorista com esse ID!");
                    }
                    break;
                case 3:
                    // Lógica para excluir motorista
                    int idMotoristaExcluir = Console.readInt("\nInforme o ID do motorista que deseja excluir:");
                    Motorista motoristaExcluir = MotoristaPersistencia.procurarPorId(idMotoristaExcluir);
                    if (motoristaExcluir != null) {
                        MotoristaPersistencia.excluir(motoristaExcluir);
                        System.out.println("\nMotorista deletado com sucesso!");
                    } else {
                        System.out.println("\nNão existe um motorista com esse ID!");
                    }
                    break;
                case 4:
                    // Lógica para listar motoristas
                    System.out.println("\nLista de Motoristas:");
                    List<Motorista> motoristaList = MotoristaPersistencia.getMotorista();
                    if (!motoristaList.isEmpty()) {
                        for (Motorista m : motoristaList) {
                            System.out.println("ID: " + m.getId());
                            System.out.println("Nome: " + m.getNome());
                            System.out.println("Cpf: " + m.getCpf());
                            System.out.println("Número: " + m.getNumero());
                        }
                    } else {
                        System.out.println("Nenhum motorista encontrado.");
                    }
                    break;

                case 5:
                    List<Motorista> motoristas = MotoristaPersistencia.getMotorista();
                    int totalMotoristas = motoristas.size();
                    int motoristasOciosos = 0;
                    int motoristasAssociadosARotas = 0;

                    for (Motorista motoristaItem : motoristas) {
                        if (motoristaItem.getRota() == null) {
                            motoristasOciosos++;
                        } else {
                            motoristasAssociadosARotas++;
                        }
                    }

                    System.out.println("Lista de Motoristas:");
                    for (Motorista motoristaItem : motoristas) {
                        System.out.println("ID: " + motoristaItem.getId());
                        System.out.println("Nome: " + motoristaItem.getNome());
                        System.out.println("CPF: " + motoristaItem.getCpf());
                        System.out.println("Rota: " + (motoristaItem.getRota() != null ? motoristaItem.getRota().getNomeRota() : "N/A"));
                        System.out.println();
                    }

                    System.out.println("Estatísticas de Motoristas:");
                    System.out.println("Total de motoristas registrados: " + totalMotoristas);
                    System.out.println("Quantidade de motoristas ociosos: " + motoristasOciosos);
                    System.out.println("Quantidade de motoristas associados a rotas: " + motoristasAssociadosARotas);

                    // Verificar se o usuário deseja saber os IDs dos motoristas ociosos/trabalhando
                    String opcaoIDs = Console.readString("\nDeseja obter os IDs dos motoristas ociosos/trabalhando (sim/não)?");
                    if (opcaoIDs.equalsIgnoreCase("sim")) {
                        String opcaoTipo = Console.readString("Deseja os IDs dos motoristas ociosos ou trabalhando (ociosos/trabalhando)?");

                        if (opcaoTipo.equalsIgnoreCase("ociosos")) {
                            System.out.println("\nIDs dos motoristas ociosos:");
                            for (Motorista motoristaItem : motoristas) {
                                if (motoristaItem.getRota() == null) {
                                    System.out.println("ID: " + motoristaItem.getId());
                                }
                            }
                        } else if (opcaoTipo.equalsIgnoreCase("trabalhando")) {
                            System.out.println("\nIDs dos motoristas trabalhando:");
                            for (Motorista motoristaItem : motoristas) {
                                if (motoristaItem.getRota() != null) {
                                    System.out.println("ID: " + motoristaItem.getId() + " está trabalhando na rota " + motoristaItem.getRota().getNomeRota());
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
	