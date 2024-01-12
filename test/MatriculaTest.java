import dao.ICursoDAO;
import dao.CursoDAO;
import domain.Curso;
import dao.IMatriculaDAO;
import dao.MatriculaDAO;
import domain.Matricula;
import org.junit.After;
import org.junit.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MatriculaTest {

    private IMatriculaDAO matriculaDAO;
    private ICursoDAO cursoDAO;

    public MatriculaTest() {
        matriculaDAO = new MatriculaDAO();
        cursoDAO = new CursoDAO();
    }

    @After
    public void end(){
        List<Matricula> list = matriculaDAO.buscarTodos();
        list.forEach(matri -> matriculaDAO.excluir(matri));

        List<Curso> listCurso = cursoDAO.buscarTodos();
        listCurso.forEach(cur -> cursoDAO.excluir(cur));
    }

    @Test
    public void cadastrar(){
        Matricula matri = new Matricula();
        matri.setCodigo("A1");
        matri.setDataMatricula(Instant.now());
        matri.setStatus("ATIVA");
        matri.setValor(2000D);
        matriculaDAO.cadastrar(matri);

        assertNotNull(matri);
        assertNotNull(matri.getId());
    }

    @Test
    public void pesquisarPorCurso() {
        Curso curso = criarCurso("A1");
        Matricula matri = new Matricula();
        matri.setCodigo("A1");
        matri.setDataMatricula(Instant.now());
        matri.setStatus("ATIVA");
        matri.setValor(2000D);
        matriculaDAO.cadastrar(matri);
        matri.setCurso(curso);
        matri = matriculaDAO.cadastrar(matri);

        assertNotNull(matri);
        assertNotNull(matri.getId());

        Matricula matricBD = matriculaDAO.buscarPorCurso(curso);
        assertNotNull(matricBD);
        assertEquals(matri.getId(), matricBD.getId());
    }

    @Test
    public void pesquisarPorCodigoCurso() {
        Curso curso = criarCurso("A1");
        Matricula matri = new Matricula();
        matri.setCodigo("A1");
        matri.setDataMatricula(Instant.now());
        matri.setStatus("ATIVA");
        matri.setValor(2000D);
        matriculaDAO.cadastrar(matri);
        matri.setCurso(curso);
        matri = matriculaDAO.cadastrar(matri);

        assertNotNull(matri);
        assertNotNull(matri.getId());

        Matricula matricBD = matriculaDAO.buscarPorCodigoCurso(curso.getCodigo());
        assertNotNull(matricBD);
        assertEquals(matri.getId(), matricBD.getId());
    }

    @Test
    public void pesquisarPorCodigoCursoCriteria() {
        Curso curso = criarCurso("A1");
        Matricula matri = new Matricula();
        matri.setCodigo("A1");
        matri.setDataMatricula(Instant.now());
        matri.setStatus("ATIVA");
        matri.setValor(2000D);
        matriculaDAO.cadastrar(matri);
        matri.setCurso(curso);
        matri = matriculaDAO.cadastrar(matri);

        assertNotNull(matri);
        assertNotNull(matri.getId());

        Matricula matricBD = matriculaDAO.buscarPorCodigoCursoCriteria(curso.getCodigo());
        assertNotNull(matricBD);
        assertEquals(matri.getId(), matricBD.getId());
    }

    @Test
    public void pesquisarPorCursoCriteria() {
        Curso curso = criarCurso("A1");
        Matricula matri = new Matricula();
        matri.setCodigo("A1");
        matri.setDataMatricula(Instant.now());
        matri.setStatus("ATIVA");
        matri.setValor(2000D);
        matriculaDAO.cadastrar(matri);
        matri.setCurso(curso);
        matri = matriculaDAO.cadastrar(matri);

        assertNotNull(matri);
        assertNotNull(matri.getId());

        Matricula matricBD = matriculaDAO.buscarPorCursoCriteria(curso);
        assertNotNull(matricBD);
        assertEquals(matri.getId(), matricBD.getId());
    }

    private Curso criarCurso(String codigo) {
        Curso curso = new Curso();
        curso.setCodigo(codigo);
        curso.setDescricao("Curso Teste");
        curso.setNome("Curso Java");
        return cursoDAO.cadastrar(curso);
    }
}
