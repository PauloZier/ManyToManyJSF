package net.ddns.zierservices.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.ddns.zierservices.entity.impl.Pessoa;
import net.ddns.zierservices.repository.Repository;
import net.ddns.zierservices.util.PairImpl;

@Named
@ViewScoped
public class BuscaPessoaBean implements Serializable {
    
    private String pesquisa;
    
    @Inject
    private Repository reposirory;
    
    private List<Pessoa> pessoas;
    
    @PostConstruct
    public void init() {
        pessoas = reposirory.findAll(Pessoa.class);
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
            
            pessoas = reposirory.findAll(Pessoa.class);
            
        } else {
            
            pessoas = reposirory.executeNamedQuery("Pessoa.findByName", new PairImpl<>("nome", "%" + pesquisa + "%"));
            
        }
    }
}
