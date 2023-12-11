package TrabalhoFinal;


import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
    private static final String String = null;
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
    }
    
    private void adicionarRemoverEstoque(Beers bebida) throws IOException {
    	String[] options = {"Adicionar", "Remover"};
        int choice = JOptionPane.showOptionDialog(this, "Escolha uma opção", "Adicionar/Remover receita",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    	switch (choice) {
        case 0:
        	adicionarEstoque(bebida);
            break;
        case 1:
        	removeEstoque(bebida);
            break;
    	}
 
    }
    @SuppressWarnings("static-access")
	private void adicionarEstoque(Beers bebida) throws IOException {
    	String nomeReceita = JOptionPane.showInputDialog(this, "Informe o codigo da receita a ser adicionada:");
    	int quantidade;
    	quantidade = bebida.adicionarQuantidade(nomeReceita);
    	String caminho = "C:\\Users\\zz\\Documents\\ws-elipse\\curso_programacao\\src\\TrabalhoFinal\\info.txt\\info.txt";

    	String palavraaux = null;
    	File arquivo = new File(caminho);

        // Leitura do conteúdo do arquivo
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
            	String[] parts = linha.split(",");
            	if (parts[0].equals(nomeReceita)) {
            		palavraaux=parts[6];
            	}
                conteudo.append(linha).append(System.lineSeparator());
                
            }
        }
        
        // Substituição da palavra no conteúdo
        String novoConteudo = conteudo.toString().replaceAll("\\b" + palavraaux + "\\b",String.valueOf(quantidade) );

        // Escrita do novo conteúdo de volta no arquivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            bw.write(novoConteudo);
        }
    }
    @SuppressWarnings("static-access")
	private void removeEstoque(Beers bebida) throws IOException {
    	String nomeReceita = JOptionPane.showInputDialog(this, "Informe o codigo da receita a ser removida:");
    	int quantidade;
    	quantidade = bebida.removeQuantidade(nomeReceita);
    	String caminho = "C:\\Users\\zz\\Documents\\ws-elipse\\curso_programacao\\src\\TrabalhoFinal\\info.txt\\info.txt";
    	if(quantidade==-1) {
    		return;
    	}else {
    	String palavraaux = null;
    	File arquivo = new File(caminho);

        // Leitura do conteúdo do arquivo
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
            	String[] parts = linha.split(",");
            	if (parts[0].equals(nomeReceita)) {
            		palavraaux=parts[6];
            	}
                conteudo.append(linha).append(System.lineSeparator());
                
            }
        }

        // Substituição da palavra no conteúdo
        String novoConteudo = conteudo.toString().replaceAll("\\b" + palavraaux + "\\b",String.valueOf(quantidade) );

        // Escrita do novo conteúdo de volta no arquivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            bw.write(novoConteudo);
        }
    	}
    }
    
    
    
    
    
    
    private void removeReceita(Beers bebida) {
    	String caminho = "C:\\Users\\zz\\Documents\\ws-elipse\\curso_programacao\\src\\TrabalhoFinal\\info.txt";
       try {
    	List<String> linhas = lerArquivo(caminho);
        removerArquivo(caminho, linhas);
       }catch(IOException E) {}
    }
    private void adicionarReceita(Beers bebida) {
    	String caminho = "C:\\Users\\zz\\Documents\\ws-elipse\\curso_programacao\\src\\TrabalhoFinal\\info.txt";
        try {
     	List<String> linhas = lerArquivo(caminho);
         addArquivo(caminho, linhas);
        }catch(IOException E) {}
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
    	String nomeReceita = JOptionPane.showInputDialog(this, "Informe o codigo da receita a ser excluida:");
    	
    	File arquivoTemporario = new File("C:\\Users\\zz\\Documents\\ws-elipse\\curso_programacao\\src\\TrabalhoFinal\\arquivoaux.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(caminho));
             BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTemporario))) {

            
            String linha;
           
            while ((linha = br.readLine()) != null) {
            	String[] parts = linha.split(",");
            	
                if (!nomeReceita.equals(parts[0])) {
                    bw.write(linha);
                  
                        bw.newLine();
                    
                }
            }
        }
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoTemporario));
                BufferedWriter escritor = new BufferedWriter(new FileWriter(caminho))) {

               // Mover o conteúdo de volta para o arquivo original
               String linha;
               while ((linha = leitor.readLine()) != null) {
                   escritor.write(linha);
                   escritor.newLine(); // Adiciona uma nova linha no arquivo original
               }
           }

        }
    
    //// adicionar linha no arquivo
    private void addArquivo(String caminho, List<String> linhas) throws IOException {
    	String nomeReceita = adicionar();
    	try {
            // Passo 1: Ler o conteúdo do arquivo para a memória
            BufferedReader leitor = new BufferedReader(new FileReader(caminho));
            StringBuilder conteudo = new StringBuilder();
            String linha;

            while ((linha = leitor.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }

            leitor.close();

            // Passo 2: Adicionar a nova frase
            conteudo.append(nomeReceita);

            // Passo 3: Escrever de volta o conteúdo atualizado no arquivo
            BufferedWriter escritor = new BufferedWriter(new FileWriter(caminho));
            escritor.write(conteudo.toString());
            escritor.close();

            System.out.println("Frase adicionada no final do arquivo com sucesso.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void menuPrincipalReceitas(Beers bebida) {
    	String[] options = {"Buscar receita", "Adicionar/Remover receita" , "Apresentar receitas"};
    	int choice = JOptionPane.showOptionDialog(this, "Escolha uma opção", "Consultar Receita",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    	switch (choice) {
        case 0:
        	menuConsultarReceitas(bebida);
            break;
        case 1:
        	adicionarRemoverReceitas(bebida);
            break;
        case 2:
        	bebida.listarReceitas();
        	break;
    }
    	
    }

    private void menuConsultarReceitas(Beers bebida) {
    bebida.buscarReceita();
    }

    
    private void adicionarRemoverReceitas(Beers bebida) {
    	String[] options = {"Adicionar", "Remover"};
    int choice = JOptionPane.showOptionDialog(this, "Escolha uma opção", "Adicionar/Remover receita",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	System.out.println(choice);
    switch (choice) {
    case 0:
    	adicionarReceita(bebida);
        break;
    case 1:
    	removeReceita(bebida);
        break;
	}}
    private String adicionar() {
    	String codigo = JOptionPane.showInputDialog(this, "Informe o codigo da bebida");
    	String nome = JOptionPane.showInputDialog(this, "Informe o nome da bebida");
    	String num1 = JOptionPane.showInputDialog(this, "Informe o ABV");
    	String num2 = JOptionPane.showInputDialog(this, "Informe o IBU");
    	String num3 = JOptionPane.showInputDialog(this, "Informe o SMR");
    	String receita = JOptionPane.showInputDialog(this, "Informe a receita da bebida");
    	String quantidade = JOptionPane.showInputDialog(this, "Informe a quantidade da bebida");
    	String preco =	JOptionPane.showInputDialog(this, "Informe o preco da bebida");
    	return codigo+","+nome+","+num1+","+num2+","+num3+","+receita+","+quantidade+","+preco+";";
    }

    private void apresentarInformacoes(Beers bebida) {
        bebida.apresentarinformacao();
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
    	String fileName = "C:\\\\Users\\\\zz\\\\Documents\\\\ws-elipse\\\\curso_programacao\\\\src\\\\TrabalhoFinal\\\\info.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
            	Beer beer = parseLine(line);
            	bebidas.adicionarbebida(beer);
                //System.out.println(line);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    private static Beer parseLine(String line) {
    	String[] parts = line.split(",");
    	String codigo = parts[0];
        String name = parts[1];
        int num1 = Integer.parseInt(parts[2]);
        int num2 = Integer.parseInt(parts[3]);
        int num3 = Integer.parseInt(parts[4]);
        InformBeer info = new InformBeer(num1,num2,num3);
        String receita = parts[5];
        int quantity = Integer.parseInt(parts[6]); 
        double preco = Double.parseDouble(parts[7].replace(";", ""));
        return new Beer(codigo,name,info, receita, quantity,preco);
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TrabPO());
    }
}

	
	

