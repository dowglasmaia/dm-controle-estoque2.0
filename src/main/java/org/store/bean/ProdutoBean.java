package org.store.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;
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

@Scope("request")
@Controller
public class ProdutoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProdutoDAO pDao;

	@Autowired
	private FornecedorDAO fDao;

	private List<Fornecedor> fornecedors = new ArrayList<>();

	private Produto produto;

	//
	public ProdutoBean() {
		this.produto = new Produto();
	}

	private Integer qtdaSaida;
	private Integer quantidade;

	private List<Fornecedor> fornecedores = new ArrayList<>();
	private List<Produto> produtos = new ArrayList<>();

	// Novo
	public String novo() {
		this.produto = new Produto();
		return "/pages/produtos?faces-redirect=true";
	}

	// Cancelar Baixar
	public String cancelarBaixa() {
		novo();
		return "/pages/listagem?faces-redirect=true";
	}

	// Cancelar
	public String cancelar() {
		return "/pages/produtos?faces-redirect=true";
	}

	// Salvar
	public void saveOrUpdate() {

		try {
			if (this.produto.getId() != null && produto.getId() != 0) {
				produto.setEstoque(quantidade + produto.getEstoque());
				pDao.update(produto);
				Messages.addGlobalWarn("Produto Atualizado com Sucesso!");
				// return "/pages/listagem?faces-redirect=true";
			} else {
				produto.setEstoque(quantidade);
				pDao.save(produto);
				Messages.addGlobalInfo("Produto Salvo com Sucesso!");
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
	public void baixarEstoque() {
		if (produto.getEstoque() == 0 || qtdaSaida >= produto.getEstoque()) {
			Messages.addGlobalFatal("Erro ao Tentar Atualizar Estoque, revise os dados.");

		} else {
			produto.setEstoque(produto.getEstoque() - qtdaSaida);
			try {
				pDao.update(produto);
				Messages.addGlobalWarn("Estoque Atualizado com Sucesso!");

			} catch (Exception e) {
				Messages.addGlobalWarn("Erro ao Tentar Atualizar Estoque!");
				e.printStackTrace();
			}
		}

	}

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

	public Integer getQtdaSaida() {
		return qtdaSaida;
	}

	public void setQtdaSaida(Integer qtdaSaida) {
		this.qtdaSaida = qtdaSaida;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public List<Fornecedor> getFornecedors() {
		return fornecedors;
	}

}
