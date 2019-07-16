package org.store.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.store.dao.FornecedorDAO;
import org.store.dao.ProdutoDAO;
import org.store.entity.Fornecedor;
import org.store.entity.Produto;

/*
 * Autor: Dowglas Maia
 * Skype: live:dowglasmaia
 * E-mail:dowglasmaia@live.com
 * 
 * */

@ViewScoped
@Controller
public class ProdutoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProdutoDAO pDao;

	@Autowired
	private FornecedorDAO fDao;

	private List<Fornecedor> fornecedors = new ArrayList<>();

	private Produto produto;	

	
	private List<Fornecedor> fornecedores = new ArrayList<>();
	private List<Produto> produtos = new ArrayList<>();


	//
	public ProdutoBean() {
		this.produto = new Produto();
		
	}

	// Novo
	public String novo() {
		this.produto = new Produto();
		return "/pages/produtos?faces-redirect=true";
	}

	// Cancelar Baixar
	public String cancelarBaixa() {
		this.produto = new Produto();
		return "/pages/listagem?faces-redirect=true";
	}

	// Cancelar
	public String cancelar() {
		this.produto = new Produto();
		return "/pages/produtos?faces-redirect=true";
	}

	// Salvar
	public void saveOrUpdate() {

		try {
			if (this.produto.getId() != null && produto.getId() != 0) {
				produto.setEstoque(produto.getQuantidade() + produto.getEstoque());
				pDao.update(produto);
				Messages.addGlobalWarn("Produto Atualizado com Sucesso!");
				this.produto = new Produto();
				// return "/pages/listagem?faces-redirect=true";
			} else {
				produto.setEstoque(produto.getQuantidade());
				pDao.save(produto);
				Messages.addGlobalInfo("Produto Salvo com Sucesso!");
				this.produto = new Produto();
				// return "/pages/listagem?faces-redirect=true";
			}
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Tentar Salvar ou Atualizar o Produto!");
			e.printStackTrace();
			// return null;
		}
		// return "/pages/listagem?faces-redirect=true";
	}

	// Listar Todos
	public List<Produto> ListarTodos() {		
		try {
			return produtos = pDao.FindAll();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Realizar Consulta de Produtos!");
			e.printStackTrace();
		}
		return null;
	}

	// Remove
	public void remove(Integer id) {
		try {
			pDao.delete(produto);
			Messages.addGlobalInfo("Produto Removido com Sucesso!");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Tentar Remover Produto!");
			e.printStackTrace();
		}
	}

	// Listar Fornecedores
	public List<Fornecedor> listarFornecedores() {
		try {
			this.fornecedores = fDao.FindAll();
			return fornecedores;
		} catch (Exception e) {
			Messages.addGlobalError("Erro de Sistema!" + Fornecedor.class);
			e.printStackTrace();
			return null;
		}

	}

	// Atualiza Estoque
	public String baixarEstoque() {
		if (this.produto.getQtdaSaida() == 0 || this.produto.getQtdaSaida() > produto.getEstoque()) {
			Messages.addGlobalFatal("Erro ao Tentar Atualizar Estoque, revise a qauntidade informada: " + this.produto.getQtdaSaida());			
			return "";
		} else {
			try {
				produto.setEstoque(produto.getEstoque() - this.produto.getQtdaSaida());
				pDao.update(produto);
				Messages.addGlobalWarn("Estoque Atualizado com Sucesso!");				
				return "";

			} catch (Exception e) {
				Messages.addGlobalWarn("Erro ao Tentar Atualizar Estoque!");
				e.printStackTrace();
				return "";			}
			
		}

	}

	
	// Pegando o produto Selecionado
	public void saidaPro(ActionEvent evento) {		
		produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
		
	}
	
	

	// **Getters e Setters**//
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public List<Fornecedor> getFornecedors() {
		return fornecedors;
	}

}
