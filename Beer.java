package TrabalhoFinal;

public class Beer {
private String codigo;
private InformBeer info;
private String name;
private String receita;
private int quantidade;
private double preco;

public Beer(String codigo,String name,InformBeer info,String receita, int quantidade,double preco) {
	this.codigo=codigo;
	this.info = info;
	this.name = name;
	this.receita =receita;
	this.quantidade=quantidade;
	this.preco=preco;
}
public String getcode() {
	return codigo;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getReceita() {
	return receita;
}
public void setReceita(String receita) {
	this.receita = receita;
}
public int getQuantidade() {
	return quantidade;
}
public void setQuantidade(int quantidade) {
	this.quantidade = quantidade;
}
public double getPreco() {
	return preco;
}
public void setPreco(double preco) {
	this.preco = preco;
}
public String apresentarinfo() {
	return "\n"+"Codigo: "+this.codigo + "\n"+ "Cerveja: "+this.name + "\n" + "Informações" + this.info ;
}
public String apresentarBebida() {
	return "Codigo: "+ this.codigo +"\n"+"Cerveja: "+this.name+"\n"+"Quantidade: "+this.quantidade+"\n"+"preco: "+this.preco +"\n";
}
public String toString() {
	return this.codigo + ","+ this.name + "," + this.info + this.receita + "," + this.quantidade + "," + this.preco;
}



}
