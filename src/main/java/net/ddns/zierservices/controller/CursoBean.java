package net.ddns.zierservices.controller;

import java.io.Serializable;
import net.ddns.zierservices.util.Msg;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import net.ddns.zierservices.factory.RepositoryFactory;
import net.ddns.zierservices.model.definition.CrudRepository;
import net.ddns.zierservices.model.impl.Curso;

@Named
@ViewScoped
public class CursoBean implements Serializable {

    private Curso curso;
    
    private CrudRepository<Curso> repository;
    
    private List<Curso> list;

    @PostConstruct
    public void init() {
        curso = new Curso();
        repository = RepositoryFactory.create(Curso.class);
        list = repository.findAll();
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Curso> getList() {
        return list;
    }

    public void setList(List<Curso> list) {
        this.list = list;
    }

    public String save() {

        try {

            boolean add = this.curso.getId() == null;

            repository.save(this.curso);

            Msg.msg(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");

            if (add) {
                list.add(curso);
            }
            
        } catch (Exception ex) {
            
            Msg.msg(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!");
            
        }
        
        return "";
    }

    public String delete() {
        
        if (this.curso.getAlunos() != null) {
        
            Msg.msg(FacesMessage.SEVERITY_ERROR, "Não é possível excluir! Existem alunos cadastrados neste curso.");
        
        }

        if (repository.delete(this.curso.getId())) {
        
            list.remove(curso);
            
            curso = new Curso();
            
            Msg.msg(FacesMessage.SEVERITY_INFO, "Excluido com sucesso!");
        }

        return "";
    }

    public String novo() {
        curso = new Curso();
        return "";
    }
}
