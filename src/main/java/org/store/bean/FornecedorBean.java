package org.store.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.store.dao.FornecedorDAO;
import org.store.entity.Fornecedor;

/*
 * Autor: Dowglas Maia
 * Skype: live:dowglasmaia
 * E-mail:dowglasmaia@live.com
 * 
 * */


@ViewScoped
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

	public void novo() {
		fornecedor = new Fornecedor();
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
	public void listarFornecedores() {
		try {
			fornecedores = fDao.FindAll();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Realizar Consulta de Fornecedores!");
			e.printStackTrace();
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
