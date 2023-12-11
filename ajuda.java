package TrabalhoFinal;


import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class TrabPO extends JFrame {
    private JTextField inputTextField;
	private Object outputTextArea;

    public TrabPO() {
    	
    	
    	Beers bebidas = new Beers();
    	readFile(bebidas);
        setTitle("Cervejaria App");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel titleLabel = new JLabel("Cervejaria Artesanal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        inputTextField = new JTextField();

        JButton consultarEstoqueButton = new JButton("Consultar Estoque");
        JButton consultarReceitasButton = new JButton("Consultar Receitas");
        JButton apresentarInfoButton = new JButton("Apresentar Informações");

        consultarEstoqueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					menuConsultarEstoque(bebidas);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        consultarReceitasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	menuPrincipalReceitas(bebidas);
            }
        });

        apresentarInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                apresentarInformacoes(bebidas);
            }
        });

        setLayout(new GridLayout(5, 1));
        add(titleLabel);
        add(consultarEstoqueButton);
        add(consultarReceitasButton);
        add(apresentarInfoButton);
        add(inputTextField);  // Adicione aqui outros componentes conforme necessário

        // Carregar informações iniciais
        carregarEstoque();
        carregarReceitas();

        setVisible(true);
    }

    private void menuConsultarEstoque(Beers bebida) throws IOException {
        String[] options = {"Apresentar Estoque", "Adicionar/Remover do Estoque"};
        int choice = JOptionPane.showOptionDialog(this, "Escolha uma opção", "Consultar Estoque",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                apresentarEstoque(bebida);
                break;
            case 1:
                adicionarRemoverEstoque(bebida);
                break;
        }
    }

    private void apresentarEstoque(Beers bebida) {
        bebida.listarBebidas();
     //   JOptionPane.showMessageDialog(this, outputTextArea.toString(), "Estoque", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void adicionarRemoverEstoque(Beers bebida) throws IOException {
    	String[] options = {"Adicionar", "Remover"};
        int choice = JOptionPane.showOptionDialog(this, "Escolha uma opção", "Adicionar/Remover receita",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    	switch (choice) {
        case 0:
        	adicionar();
            break;
        case 1:
        	removeEstoque(bebida);
            break;
    	}
 
    }
    private void removeEstoque(Beers bebida) {
    	String caminho = "C:\\\\\\\\Users\\\\\\\\zz\\\\\\\\Documents\\\\\\\\ws - eclipse\\\\\\\\curso_programacao\\\\\\\\src\\\\\\\\TrabalhoFinal\\\\\\\\info.txt";
       try {
    	List<String> linhas = lerArquivo(caminho);
        removerArquivo(caminho, linhas);
       }catch(IOException E) {}
    }

    private void carregarEstoque() {
        // Implemente a lógica de carregar o estoque conforme necessário
        // Exemplo: outputTextArea = new StringBuilder("Estoque:\nItem 1: 10\nItem 2: 20");
    }
    private List<String> lerArquivo(String caminho) throws IOException {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        }
        return linhas;
    }
    //// remover do arquivo txt
    private void removerArquivo(String caminho, List<String> linhas) throws IOException {
    	String nomeReceita = JOptionPane.showInputDialog(this, "Informe o codigo da receita a ser buscada:");
    	try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            for (String linha : linhas) {
               if ()
            }
        }
    }
    
    
    
    private void menuPrincipalReceitas(Beers bebida) {
    	String[] options = {"Buscar receita", "Adicionar/Remover receita" , "Apresentar receitas"};
    	int choice = JOptionPane.showOptionDialog(this, "Escolha uma opção", "Consultar Receita",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    	switch (choice) {
        case 0:
        	menuConsultarReceitas();
            break;
        case 1:
        	adicionarRemoverReceitas();
            break;
        case 2:
        	
        	break;
    }
    	
    }

    private void menuConsultarReceitas() {
        String nomeReceita = JOptionPane.showInputDialog(this, "Informe o codigo da receita a ser buscada:");
        buscarReceita(nomeReceita);
    }

    private void buscarReceita(String nomeReceita) {
        carregarReceitas();
        JOptionPane.showMessageDialog(this, outputTextArea.toString(), "Receitas", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void adicionarRemoverReceitas() {
    	String[] options = {"Adicionar", "Remover"};
    int choice = JOptionPane.showOptionDialog(this, "Escolha uma opção", "Adicionar/Remover receita",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	switch (choice) {
    case 0:
    	adicionar();
        break;
    case 1:
    	
        break;
	}}
    private void adicionar() {
    	String nome = JOptionPane.showInputDialog(this, "Informe o nome da bebida");
    	String receita = JOptionPane.showInputDialog(this, "Informe a receita da bebida");
    	String quantidade = JOptionPane.showInputDialog(this, "Informe a quantidade da bebida");
    	String preco =	JOptionPane.showInputDialog(this, "Informe o preco da bebida");
    }
    private void ListarReceitas() {
    	
    }

    private void carregarReceitas() {

    }

    private void apresentarInformacoes(Beers bebida) {
        // Implemente a lógica de apresentar informações gerais conforme necessário
        // Exemplo: JOptionPane.showMessageDialog(this, "Informações gerais...", "Informações", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static void saveLineToFile(String fileName, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line + "\n");  // Adiciona uma nova linha ao final do arquivo
            System.out.println("Linha salva com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void readFile(Beers bebidas) {
    	String fileName = "C:\\\\Users\\\\zz\\\\Documents\\\\ws - eclipse\\\\curso_programacao\\\\src\\\\TrabalhoFinal\\\\info.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
            	Beer beer = parseLine(line);
            	bebidas.adicionarbebida(beer);
                System.out.println(line);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Beer parseLine(String line) {
    	String[] parts = line.split(",");
        String name = parts[0];
        int num1 = Integer.parseInt(parts[1]);
        int num2 = Integer.parseInt(parts[2]);
        int num3 = Integer.parseInt(parts[3]);
        InformBeer info = new InformBeer(num1,num2,num3);
        String receita = parts[4];
        int quantity = Integer.parseInt(parts[5]); // Remove o ';' no final
        double preco = Double.parseDouble(parts[6].replace(";", ""));
        return new Beer(name,info, receita, quantity,preco);
        
    }
    
    
    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(() -> new TrabPO());
    }
}

	
	

