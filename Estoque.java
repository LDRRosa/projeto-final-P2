import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Estoque {
    public static Scanner leia = new Scanner(System.in);
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String path = "produtos/";
        int id = inicializaDiretorio(path);
        int menu = 0;
        while (true) {
            try {
                System.out.println(
                        "Escolha uma opção: \n1:Cadastrar produto \n2:Saída \n3:Balanço de estoque: \n4:Alterar produto \n5:Histórico de vendas \n6:Procurar produto");
                menu = scanner.nextInt();
                switch (menu) {
                    case 1:
                        cadastraProduto(id);
                        id++;
                        gravarId(id);
                        break;
                    case 2:

                        break;
                    case 3:
                        balancoEstoque(path, id);
                        break;
                    case 4:

                        break;
                    case 5:
                        procuraProduto(path, id);
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

    private static void procuraProduto(String path, int id)
    {
        
    }

    private static int inicializaDiretorio(String path) {
        int id = 0;
        File dir = new File(path);
        if (!dir.exists()) { 
            dir.mkdir(); 
        }
        File arquivo = new File("id.txt");
        if (!arquivo.exists()) { 
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Não foi possível criar o ID");
                e.printStackTrace();
            }
            gravarId(0); 
        } else {
            id = lerId();
        }
        return id;
    }

    private static int lerId() {
        BufferedReader bf;
        int id = 0;
        try {
            bf = new BufferedReader(new FileReader("id.txt"));
            id = Integer.parseInt(bf.readLine());
            bf.close();
        } catch (IOException | NumberFormatException e) {
            System.err.println("Id não encontrado");
            e.printStackTrace();
        }
        return id;
    }

    private static void gravarId(int id) {
        PrintWriter pw;
        try {
            pw = new PrintWriter("id.txt");
            pw.println(id);
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }
    }

    public static void cadastraProduto(int id) {
        System.out.println("============================================");
        String caminhoarquivo = "produtos/" + id + ".txt";
        Path produtopath = Paths.get(caminhoarquivo);
        if (!Files.exists(produtopath)) {
            try {
                // Crie o arquivo
                Files.createFile(produtopath);
                System.out.println("============================================");
                System.out.println("produto cadastrado com sucesso!");
                System.out.println("============================================");
                informacoesProdutos(id);

            } catch (Exception e) {
                System.err.println("Erro ao criar o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("============================================");
            System.out.println("O produto ja existe!");
            System.out.println("============================================");
        }
    }

    public static void informacoesProdutos(int id) {
        try {
            FileWriter fileWriter = new FileWriter("produtos/"
                    + id + ".txt");
            PrintWriter pw = new PrintWriter(fileWriter);

            System.out.println("Digite o nome do produto");
            pw.println(leia.nextLine());
            pw.flush();

            System.out.println("Digite o Estoque");
            pw.println(leia.nextLine());
            pw.flush();

            System.out.println("DIgite o Valor unitário");
            pw.println(leia.nextLine());
            pw.flush();

            pw.close();
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("ocorreu um erro");
        }

    }

    public static void saida(String path) {
        int quantvenda, idproduto;
        
        System.out.println("Digite o id do produto que foi vendido: ");
        idproduto=leia.nextInt();
        System.out.println("Digite a quantidade que foi vendida:");
        quantvenda=leia.nextInt();
        String caminho = "produtos/" + idproduto + ".txt";
        Path prodpath = Paths.get(caminho);
        if(Files.exists(prodpath)){
            BufferedReader bf = new BufferedReader(new FileReader(path+idproduto+".txt"));
    }else{
        System.out.println("este produto não existe");
    }

    }


    public static void balancoEstoque(String path, int id)
    {File dir = new File(path);
		String [] arquivos = dir.list();
        System.out.println("======== Balanço de estoque ========");
		for(int i=0; i<arquivos.length; i++)
        {
			id = Integer.parseInt(arquivos[i].substring(0,arquivos[i].length()-4));
			try {
                BufferedReader bf = new BufferedReader(new FileReader(path+id+".txt"));
				String nome = bf.readLine();
                int estoque = Integer.parseInt(bf.readLine());
                Double valor = Double.parseDouble(bf.readLine());
				System.out.println("ID do produto: " + id + "\nNome do produto: "+ nome + 
                "\nQuantidade em estoque: " + estoque + "\nValor de compra: R$" + valor);
                bf.close();
			} catch (IOException e) {
				System.out.println("Contato não encontrado");
				e.printStackTrace();
			}
		}
		if(arquivos.length == 0) {
			System.out.println("Não tem contato cadastrado");
		}
    }
}