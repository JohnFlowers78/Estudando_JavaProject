package br.edu.up.front;

import br.edu.up.entidades.Onibus;
import br.edu.up.entidades.Motorista;
import br.edu.up.entidades.Rota;
import br.edu.up.persistencia.RotaPersistencia;
import br.edu.up.persistencia.MotoristaPersistencia;
import br.edu.up.persistencia.OnibusPersistencia;
import java.util.List;

public class AppRota {
    public static void main(String[] args) {
        int opc;

        do {
            System.out.println("\n\nMenu Rotas:");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Listar");
            System.out.println("5 - Estatísticas");
            System.out.println("6 - Sair");

            opc = Console.readInt("Informe a opção: ");

            switch (opc) {
                case 1:
                    Rota rota = new Rota();
                    int idOnibus = Console.readInt("\nInforme o ID do ônibus:");
                    int idMotorista = Console.readInt("Informe o ID do motorista:");

                    if (!OnibusPersistencia.onibusExiste(idOnibus)) {
                        System.out.println("ID do ônibus inválido. Não existe um ônibus com esse ID!");
                        break;
                    }

                    if (!MotoristaPersistencia.motoristaExiste(idMotorista)) {
                        System.out.println("ID do motorista inválido. Não existe um motorista com esse ID!");
                        break;
                    }

                    Onibus onibus = OnibusPersistencia.procurarPorId(idOnibus);
                    rota.setOnibus(onibus);

                    Motorista motorista = MotoristaPersistencia.procurarPorId(idMotorista);
                    rota.setMotorista(motorista);

                    rota.setNomeRota(Console.readString("Informe o nome da rota:"));
                    RotaPersistencia.incluir(rota);
                    System.out.println("\nRota cadastrada com sucesso!");
                    break;
                case 2:
                    Rota rotaAtualizada = new Rota();
                    rotaAtualizada.setId(Console.readInt("\nInforme o ID da rota que deseja atualizar:"));
                    int idNovoOnibus = Console.readInt("Informe o novo ID do ônibus:");
                    int idNovoMotorista = Console.readInt("Informe o novo ID do motorista:");

                    if (!RotaPersistencia.rotaExiste(rotaAtualizada.getId())) {
                        System.out.println("ID da Rota inválido. Não existe uma Rota com esse ID!");
                        break;
                    }

                    if (!OnibusPersistencia.onibusExiste(idNovoOnibus)) {
                        System.out.println("ID do ônibus inválido. Não existe um ônibus com esse ID!");
                        break;
                    }

                    if (!MotoristaPersistencia.motoristaExiste(idNovoMotorista)) {
                        System.out.println("ID do motorista inválido. Não existe um motorista com esse ID!");
                        break;
                    }

                    Onibus novoOnibus = OnibusPersistencia.procurarPorId(idNovoOnibus);
                    rotaAtualizada.setOnibus(novoOnibus);

                    Motorista novoMotorista = MotoristaPersistencia.procurarPorId(idNovoMotorista);
                    rotaAtualizada.setMotorista(novoMotorista);

                    rotaAtualizada.setNomeRota(Console.readString("Informe o novo nome da rota:"));
                    RotaPersistencia.alterar(rotaAtualizada);
                    System.out.println("\nRota atualizada com sucesso!");
                    break;
                case 3:
                    int idRotaExcluir = Console.readInt("\nInforme o ID da rota que deseja excluir:");
                    Rota rotaExcluir = RotaPersistencia.procurarPorId(idRotaExcluir);
                    if (rotaExcluir != null) {
                        RotaPersistencia.excluir(rotaExcluir);
                        System.out.println("\nRota deletada com sucesso!");
                    } else {
                        System.out.println("\nNão existe uma rota com esse ID!");
                    }
                    break;
                case 4:
                    List<Rota> rotas = RotaPersistencia.getRotas();
                    if (!rotas.isEmpty()) {
                        System.out.println("\nLista de Rotas:");
                        for (Rota r : rotas) {
                            System.out.println("ID: " + r.getId());
                            System.out.println("ID do ônibus: " + r.getOnibus().getId());
                            System.out.println("ID do motorista: " + r.getMotorista().getId());
                            System.out.println("Nome da rota: " + r.getNomeRota());
                            System.out.println();
                        }
                    } else {
                        System.out.println("Nenhuma rota encontrada.");
                    }
                    break;
                case 5:
                    List<Rota> rotasStats = RotaPersistencia.getRotas();
                    int totalRotas = rotasStats.size();

                    System.out.println("Estatísticas de Rotas:");
                    System.out.println("Total de rotas registradas: " + totalRotas);

                    // Verificar se o usuário deseja listar as rotas com o nome do motorista e a linha do ônibus
                    String opcaoListar = Console.readString("\nDeseja listar as rotas com o nome do motorista e a linha do ônibus (sim/não)?");
                    if (opcaoListar.equalsIgnoreCase("sim")) {
                        System.out.println("\nLista de Rotas com o nome do motorista e a linha do ônibus:");
                        for (Rota rotaItem : rotasStats) {
                            System.out.println("ID: " + rotaItem.getId());
                            System.out.println("Nome da rota: " + rotaItem.getNomeRota());
                            System.out.println("Nome do motorista: " + rotaItem.getMotorista().getNome());
                            System.out.println("Linha do ônibus: " + rotaItem.getOnibus().getNomeLinha());
                            System.out.println();
                        }
                    }
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opc != 6);
    }
}
