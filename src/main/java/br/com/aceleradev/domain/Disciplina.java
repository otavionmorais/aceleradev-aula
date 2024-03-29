package br.com.aceleradev.domain;

import br.com.aceleradev.exceptions.NumeroMaximoDeAlunosException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "disciplina")
public class Disciplina {

    public enum Tipo {
        HUMANAS, BIOLOGICAS, EXATAS
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String nome;
    private Tipo tipo;

    @ManyToMany
    @JoinTable(name = "disciplina_aluno", joinColumns = {@JoinColumn(name = "id_disciplina")}
            , inverseJoinColumns = {@JoinColumn(name="id_aluno")})
    private List<Aluno> alunos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;

    public Disciplina(String nome, Professor professor, Tipo tipo) {
        this.nome = nome;
        this.professor = professor;
        this.tipo = tipo;
    }

    public void matricular(Aluno aluno) {
        if(alunos.size() == 10){
            throw new NumeroMaximoDeAlunosException("Disciplina lotada");
        }
        alunos.add(aluno);
    }

    public void mostraAlunos() {
        alunos.forEach(o -> System.out.println(o));

//        alunos.forEach(aluno -> {
//            System.out.println(aluno);
//        });
//
//        alunos.forEach(aluno -> System.out.println(aluno));
//
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "nome='" + nome + '\'' +
                ", alunos=" + alunos +
                ", professor=" + professor +
                "\n";
    }
}
