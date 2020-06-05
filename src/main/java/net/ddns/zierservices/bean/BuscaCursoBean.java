package net.ddns.zierservices.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.ddns.zierservices.entity.impl.Curso;
import net.ddns.zierservices.repository.Repository;
import net.ddns.zierservices.util.PairImpl;

@Named
@ViewScoped
public class BuscaCursoBean implements Serializable {

    private String pesquisa;
    
    @Inject
    private Repository repository;
    
    private List<Curso> cursos;

    @PostConstruct
    public void init() {
        
        cursos = repository.findAll(Curso.class);
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
        
            cursos = repository.findAll(Curso.class);
        
        } else {
            
            cursos = repository.executeNamedQuery("Curso.findByDescription", new PairImpl<>("descricao", "%" + this.pesquisa + "%"));
            
        }
    }
}
