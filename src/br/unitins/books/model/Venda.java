package br.unitins.books.model;

import java.time.LocalDate;
import java.util.List;

public class Venda extends Entity<Venda> {

	private static final long serialVersionUID = 3530239405197865065L;
	private LocalDate data;
	private Usuario usuario;
	private List<ItemVenda> listaItemVenda;

	// campo calculado
	private Float totalVenda = 0.0f;

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ItemVenda> getListaItemVenda() {
		return listaItemVenda;
	}

	public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
		this.listaItemVenda = listaItemVenda;
	}

	public Float getTotalVenda() {
		return totalVenda;
	}

	public void setTotalVenda(Float totalVenda) {
		this.totalVenda = totalVenda;
	}

}
