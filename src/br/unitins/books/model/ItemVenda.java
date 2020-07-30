package br.unitins.books.model;

public class ItemVenda extends Entity<ItemVenda> {
	private static final long serialVersionUID = 4337294373727203428L;
	private Livro livro;
	private Float valor;
	private Venda venda;

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}
