package net.ddns.zierservices.controller;

import java.io.Serializable;
import net.ddns.zierservices.util.Msg;
import java.util.ArrayList;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import net.ddns.zierservices.factory.RepositoryFactory;
import net.ddns.zierservices.model.definition.CrudRepository;
import net.ddns.zierservices.model.impl.Curso;
import net.ddns.zierservices.model.impl.Pessoa;

@Named
@ViewScoped
public class CursosPessoasBean implements Serializable {

    private Pessoa pessoa;

    private Curso curso;

    private CrudRepository<Pessoa> pessoaRepository;

    @PostConstruct
    public void init() {

        pessoa = new Pessoa();

        curso = new Curso();

        pessoaRepository = RepositoryFactory.create(Pessoa.class);
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

            pessoaRepository.save(pessoa);

            Msg.msg(FacesMessage.SEVERITY_INFO, "Curso removido com sucesso!");

            this.pessoa = pessoaRepository.find(pessoa.getId());

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

            pessoaRepository.save(this.pessoa);

            Msg.msg(FacesMessage.SEVERITY_INFO, "Curso adicionado com sucesso!");
        }

        this.pessoa = pessoaRepository.find(pessoa.getId());

        return "";
    }
}
