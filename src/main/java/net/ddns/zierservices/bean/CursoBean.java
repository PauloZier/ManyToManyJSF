package net.ddns.zierservices.bean;

import java.io.Serializable;
import net.ddns.zierservices.util.Msg;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.ddns.zierservices.entity.impl.Curso;
import net.ddns.zierservices.repository.Repository;

@Named
@ViewScoped
public class CursoBean implements Serializable {

    private Curso curso;
    
    @Inject
    private Repository repository;
    
    private List<Curso> list;

    @PostConstruct
    public void init() {
        curso = new Curso();
        list = repository.findAll(Curso.class);
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

        if (repository.delete(Curso.class, this.curso.getId())) {
        
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
