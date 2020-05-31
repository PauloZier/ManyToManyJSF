package net.ddns.zierservices.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import net.ddns.zierservices.factory.RepositoryFactory;
import net.ddns.zierservices.model.definition.CrudRepository;
import net.ddns.zierservices.model.impl.Pessoa;
import net.ddns.zierservices.util.PairImpl;

@Named
@ViewScoped
public class BuscaPessoaBean implements Serializable {
    
    private String pesquisa;
    private CrudRepository<Pessoa> reposirory;
    private List<Pessoa> pessoas;
    
    @PostConstruct
    public void init() {
        reposirory = RepositoryFactory.create(Pessoa.class);
        pessoas = reposirory.findAll();
    }
    
    public String getPesquisa() {
        return pesquisa;
    }
    
    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }
    
    public List<Pessoa> getPessoas() {
        return pessoas;
    }
    
    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
    
    public void pesquisar() {
        if (pesquisa.isEmpty()) {
            
            pessoas = reposirory.findAll();
            
        } else {
            
            pessoas = reposirory.executeNamedQuery("Pessoa.findByName", new PairImpl<>("nome", "%" + pesquisa + "%"));
            
        }
    }
}
