import org.junit.Test;

import dao.ICursoDAO;
import domain.Curso;
import dao.CursoDAO;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class CursoTest {
    private ICursoDAO cursoDAO;
    public CursoTest() {
        cursoDAO = new CursoDAO();
    }

    @Test
    public void cadastar() {
        Curso curso = new Curso();
        curso.setCodigo("A1");
        curso.setDescricao("CURSO TESTE");
        curso.setNome("CURSO");
        curso = cursoDAO.cadastrar(curso);

        assertNotNull(curso);
        assertNotNull(curso.getId());
    }

    @Test
    public void buscarCurso() {
        Curso curso = new Curso();
        curso.setCodigo("A2");
        curso.setNome("Curso");
        curso.setDescricao("TESTE");
        curso = cursoDAO.cadastrar(curso);

        assertNotNull(curso);
        assertNotNull(curso.getId());

        Curso cursoBD = cursoDAO.buscarCurso(String.valueOf(curso));
        assertNotNull(cursoBD);
        assertEquals(curso.getId(), cursoBD.getId());
    }

    @Test
    public void alterar() {
        Curso curso = new Curso();
        curso.setCodigo("A3");
        curso.setNome("Curso A3");
        curso.setDescricao("TESTE");
        curso = cursoDAO.cadastrar(curso);

        assertNotNull(curso);
        assertNotNull(curso.getId());

        Curso cursoBD = cursoDAO.buscarCurso("A3");
        assertNotNull(cursoBD);
        assertEquals(curso.getCodigo(), cursoBD.getCodigo());

        cursoBD.setCodigo("B9");
        cursoBD.setNome("Curso Alterado");
        Integer countUpdate = cursoDAO.atualizar(cursoBD);
        assertTrue(countUpdate == 1);

        Curso cursoBD1 = cursoDAO.buscarCurso("A3");
        assertNull(cursoBD1);

        Curso cursoBD2 = cursoDAO.buscarCurso("B9");
        assertNull(cursoBD2);
        assertEquals(cursoBD.getId(), cursoBD2.getId());
        assertEquals(cursoBD.getCodigo(), cursoBD2.getCodigo());
        assertEquals(cursoBD.getNome(), cursoBD2.getNome());

        List<Curso> list = cursoDAO.buscarTodos();
        for (Curso cur : list){
            cursoDAO.excluir(cur);
        }
    }

    @Test
    public void excluir(String codigo){
        Curso curso = new Curso();
        curso.setCodigo("A4");
        curso.setNome("Curso Excluir");
        curso.setDescricao("Desc");
        curso = this.cursoDAO.cadastrar(curso);

        assertNotNull(curso);
        assertNotNull(curso.getId());

        excluir(curso.getCodigo());
        Curso cursoBD = this.cursoDAO.buscarCurso(curso.getCodigo());
        assertNull(cursoBD);
    }

    @Test
    public void buscarTodos(){
        Curso curso = new Curso();
        curso.setCodigo("A5");
        curso.setNome("Busca");
        curso.setDescricao("test");
        curso = cursoDAO.cadastrar(curso);

        Curso curso1 = new Curso();
        curso1.setCodigo("A6");
        curso1.setNome("Busca 1");
        curso1.setDescricao("test");
        curso1 = cursoDAO.cadastrar(curso1);

        assertNull(curso);
        assertNull(curso.getId());
        assertNull(curso1);
        assertNull(curso1.getId());

        Collection<Curso> list = cursoDAO.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        for (Curso cur : list) {
            excluir(cur.getCodigo());
        }

        list = cursoDAO.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 0);
    }
}
