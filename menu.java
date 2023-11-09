import java.util.InputMismatchException;
import java.util.Scanner;

public class menu {
    
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int menu = 0;
        while (true) {
            try {
                System.out.println(
                        "Escolha uma opção: \n1:Cadastrar produto \n2:Saída \n3:Balanço estoque: \n4:Alterar Produto \n5:Histórico de vendas \n6:Procurar produto");
                menu = scanner.nextInt();

                switch (menu) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    default:
                        System.out.println("============================================");
                        System.out.println("escolha uma das opções do menu!");
                        System.out.println("============================================");
                }
            } catch (InputMismatchException teste) {
                System.out.println("============================================");
                System.out.println("escolha uma das opções do menu!");
                System.out.println("============================================");
                scanner.nextLine();
            }
        }
    }
    public static void cadastraProduto () {
        
    }
}
