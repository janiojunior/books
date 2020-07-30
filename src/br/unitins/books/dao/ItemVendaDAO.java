package br.unitins.books.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.books.model.Livro;
import br.unitins.books.model.ItemVenda;
import br.unitins.books.model.Venda;

public class ItemVendaDAO extends DAO<ItemVenda> {
	
	@Override
	public boolean create(ItemVenda itemVenda) {
		
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO public.itemVenda ");
		sql.append("	(valor, idvenda, idlivro) ");
		sql.append("VALUES ");
		sql.append("	(?, ?, ?) ");
		
		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			
			stat.setFloat(1, itemVenda.getValor());
			stat.setInt(2, itemVenda.getVenda().getId());
			stat.setInt(3, itemVenda.getLivro().getId());
			stat.execute();
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;	
		
	}

	public List<ItemVenda> findByVenda(Venda venda) {
		List<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  v.id, ");
		sql.append("  v.valor, ");
		sql.append("  v.idlivro, ");
		sql.append("  l.descricao, ");
		sql.append("  l.isbn, ");
		sql.append("  l.preco, ");
		sql.append("  l.estoque ");
		sql.append("FROM ");
		sql.append("  public.itemvenda v, ");
		sql.append("  public.livro l ");
		sql.append("WHERE ");
		sql.append("  v.idlivro = l.id AND ");
		sql.append("  v.idvenda = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, venda.getId());
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				ItemVenda item = new ItemVenda();
				item.setId(rs.getInt("id"));
				item.setValor(rs.getFloat("valor"));
				
				Livro livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setIsbn(rs.getString("isbn"));
				livro.setPreco(rs.getFloat("preco"));
				livro.setEstoque(rs.getInt("estoque"));
				
				item.setLivro(livro);
				
				item.setVenda(venda);
				
				listaItemVenda.add(item);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaItemVenda;
	}
	
	@Override
	public boolean update(ItemVenda itemVenda) {
		return false;
	}

	@Override
	public boolean delete(int id) {
		return false;
	}

	@Override
	public List<ItemVenda> findAll() {
		return null;
	}

	@Override
	public ItemVenda findById(int id) {
		return null;
	}	

}
