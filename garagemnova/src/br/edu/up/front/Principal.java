package br.edu.up.front;

public class Principal {
    public static void main(String[] args) {
        int opc;

        do {
            System.out.println("\n\nSelecione a opção desejada:");
            System.out.println("1 - Motorista");
            System.out.println("2 - Ônibus");
            System.out.println("3 - Rotas");
            System.out.println("4 - Sair");

            opc = Console.readInt("Informe a opção: ");

            switch (opc) {
                case 1:
                	AppMotorista.main(args);
                    break;
                case 2:
                	AppOnibus.main(args);
                    break;
                case 3:
                	AppRota.main(args);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opc != 4);
    }
}