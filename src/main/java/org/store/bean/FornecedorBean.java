package org.store.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.store.dao.FornecedorDAO;
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
public class FornecedorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private FornecedorDAO fDao;

	private Fornecedor fornecedor;

	public FornecedorBean() {
		this.fornecedor = new Fornecedor();
	}

	private List<Fornecedor> fornecedores = new ArrayList<>();

	// Novo
	public String novo() {
		this.fornecedor = new Fornecedor();
		return "/pages/fornecedor?faces-redirect=true";
	}

	// Salvar ou Atualiza de acordo com a Regra
	public void saveOrUpdate() {
		try {
			if (this.fornecedor.getId() == null) {
				fDao.save(fornecedor);
				novo();
				Messages.addGlobalInfo("Fornecedor Salvo com Sucesso!");
			} else {
				fDao.update(fornecedor);
				novo();
				Messages.addGlobalInfo("Fornecedor Atualizado com Sucesso!");
			}
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Tentar Salvar ou Atualizar o Fornecedor!");
			e.printStackTrace();
		}
	}

	// listar
	public List<Fornecedor> listarFornecedores() {
		try {
			return this.fornecedores = fDao.FindAll();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Realizar Consulta de Fornecedores!");
			e.printStackTrace();
			return null;
		}
	}

	// Getters e Setters

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
