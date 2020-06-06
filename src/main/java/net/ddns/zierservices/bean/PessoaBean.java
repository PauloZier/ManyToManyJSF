package net.ddns.zierservices.bean;

import java.io.Serializable;
import net.ddns.zierservices.util.Msg;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.ddns.zierservices.entity.impl.Pessoa;
import net.ddns.zierservices.repository.Repository;
import net.ddns.zierservices.util.TextUtils;

@Named
@ViewScoped
public class PessoaBean implements Serializable {

    private Pessoa pessoa;
    
    @Inject
    private Repository repository;
    
    private List<Pessoa> lista;
    
    private String nascimento;

    @PostConstruct
    public void init() {
        
        pessoa = new Pessoa();

        this.setLista(repository.findAll(Pessoa.class));
        
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getLista() {
        return lista;
    }

    public void setLista(List<Pessoa> lista) {
        this.lista = lista;
    }

    public String salvar() {
        boolean add = this.pessoa.getId() == null;

        try {

            repository.save(this.pessoa);

            Msg.msg(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!");

            if (add) {
                lista.add(this.pessoa);
            }

        } catch (Exception ex) {
            
            ex.printStackTrace();
            
            Msg.msg(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!");

        }

        return "";
    }

    public String novo() {
        this.pessoa = new Pessoa();
        return "";
    }

    public String delete() {
        if (this.pessoa.getCursos() != null && this.pessoa.getCursos().size() > 0) {

            Msg.msg(FacesMessage.SEVERITY_INFO, "Não é possível excluir! Existem cursos cadastrado com essa pessoa.");

            return "";
        }

        repository.delete(Pessoa.class, this.pessoa.getId());

        Msg.msg(FacesMessage.SEVERITY_INFO, "Excluído com sucesso!");

        lista.remove(this.pessoa);

        this.pessoa = new Pessoa();

        return "";
    }

    public String formatDate(Date date) {
        return date != null ? TextUtils.dateToString(date) : "";
    }

    public void setNascimento(String date) {
        try {
            if (!date.isEmpty()) {
                this.pessoa.setDataNascimento(TextUtils.stringToDate(date));
            }
        } catch (ParseException ex) {

            ex.printStackTrace();

            Msg.msg(FacesMessage.SEVERITY_ERROR, "Formato de data inválida!");
        }
    }

    public String getNascimento() {
        
        return this.pessoa.getDataNascimento() != null ? TextUtils.dateToString(this.pessoa.getDataNascimento()) : "";
        
    }
}
