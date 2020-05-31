package net.ddns.zierservices.model.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import net.ddns.zierservices.model.definition.BaseModel;

@NamedQueries({
    @NamedQuery(
            name = "Pessoa.findByName",
            query = "from Pessoa p where lower(p.nome) like lower(:nome) order by p.nome"
    )
})

@Entity
@SequenceGenerator(name = "default", sequenceName = "pessoa_seq", allocationSize = 1)
public class Pessoa extends BaseModel {

    @Column(nullable = false, length = 150)
    private String nome;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pessoa_cursos",
            joinColumns = @JoinColumn(
                    name = "idPessoa",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "idCurso",
                    referencedColumnName = "id"
            )
    )
    private List<Curso> cursos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
