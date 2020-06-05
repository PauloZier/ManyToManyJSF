package net.ddns.zierservices.bean;

import java.io.Serializable;
import net.ddns.zierservices.util.Msg;
import java.util.ArrayList;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.ddns.zierservices.entity.impl.Curso;
import net.ddns.zierservices.entity.impl.Pessoa;
import net.ddns.zierservices.repository.Repository;

@Named
@ViewScoped
public class CursosPessoasBean implements Serializable {

    private Pessoa pessoa;

    private Curso curso;

    @Inject
    private Repository repository;

    @PostConstruct
    public void init() {

        pessoa = new Pessoa();

        curso = new Curso();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String removerCurso() {

        this.pessoa.getCursos().remove(curso);

        try {

            repository.save(pessoa);

            Msg.msg(FacesMessage.SEVERITY_INFO, "Curso removido com sucesso!");

            this.pessoa = (Pessoa) repository.find(Pessoa.class, pessoa.getId());

            curso = new Curso();

        } catch (Exception ex) {

            Msg.msg(FacesMessage.SEVERITY_ERROR, "Erro ao excluir!");

        }

        return "";
    }

    public String addCurso(Curso curso) {

        if (this.pessoa.getId() == null) {

            Msg.msg(FacesMessage.SEVERITY_WARN, "Nenhuma pessoa foi selecionada!");

            return "";
        }

        if (this.pessoa.getCursos() == null) {

            this.pessoa.setCursos(new ArrayList<>());
        }

        if (this.pessoa.getCursos().stream().anyMatch(x -> Objects.equals(curso.getId(), x.getId()))) {

            Msg.msg(FacesMessage.SEVERITY_INFO, "Pessoa já matriculada neste curso!");

        } else {

            this.pessoa.getCursos().add(curso);

            repository.save(this.pessoa);

            Msg.msg(FacesMessage.SEVERITY_INFO, "Curso adicionado com sucesso!");
        }

        this.pessoa = (Pessoa) repository.find(Pessoa.class, pessoa.getId());

        return "";
    }
}
