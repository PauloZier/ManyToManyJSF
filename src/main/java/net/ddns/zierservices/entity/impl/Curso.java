package net.ddns.zierservices.entity.impl;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import net.ddns.zierservices.entity.definition.BaseEntity;

@NamedQueries({
    @NamedQuery(
            name = "Curso.findByDescription",
            query = "from Curso c where lower(c.descricao) like lower(:descricao) order by c.descricao"
    )
})

@Entity
@SequenceGenerator(name = "default", sequenceName = "curso_seq", allocationSize = 1)
public class Curso extends BaseEntity {

    @Column(nullable = false, length = 180)
    private String descricao;

    @Column(nullable = false)
    private Integer duracao;

    @ManyToMany(mappedBy = "cursos", fetch = FetchType.EAGER)
    private List<Pessoa> alunos;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public List<Pessoa> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Pessoa> alunos) {
        this.alunos = alunos;
    }

}
