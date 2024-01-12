package dao;

import domain.Curso;
import domain.Matricula;

import java.util.List;

public interface IMatriculaDAO {
    public Matricula cadastrar(Matricula matri);

    List<Matricula> buscarTodos();

    void excluir(Matricula matri);

    Matricula buscarPorCurso(Curso curso);

    Matricula buscarPorCodigoCurso(String codigo);

    Matricula buscarPorCodigoCursoCriteria(String codigo);

    Matricula buscarPorCursoCriteria(Curso curso);
}
