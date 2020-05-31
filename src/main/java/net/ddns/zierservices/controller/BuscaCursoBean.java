package net.ddns.zierservices.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import net.ddns.zierservices.factory.RepositoryFactory;
import net.ddns.zierservices.model.definition.CrudRepository;
import net.ddns.zierservices.model.impl.Curso;
import net.ddns.zierservices.util.PairImpl;

@Named
@ViewScoped
public class BuscaCursoBean implements Serializable {

    private String pesquisa;
    
    private CrudRepository<Curso> repository;
    
    private List<Curso> cursos;

    @PostConstruct
    public void init() {
        
        repository = RepositoryFactory.create(Curso.class);
        
        cursos = repository.findAll();
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public void pesquisar() {
        
        if (pesquisa.isEmpty()) {
        
            cursos = repository.findAll();
        
        } else {
            
            cursos = repository.executeNamedQuery("Curso.findByDescription", new PairImpl<>("descricao", "%" + this.pesquisa + "%"));
            
        }
    }
}
