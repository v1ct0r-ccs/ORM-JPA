package dao;

import domain.Curso;

import java.util.List;

public interface ICursoDAO {
    public Curso cadastrar(Curso curso);

    List<Curso> buscarTodos();

    void excluir(Curso curso);

    Curso buscarCurso(String curso);

    Integer atualizar(Curso cursoBD);
}
