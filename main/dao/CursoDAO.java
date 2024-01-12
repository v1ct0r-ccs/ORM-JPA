package dao;

import domain.Curso;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CursoDAO implements ICursoDAO{
    @Override
    public Curso cadastrar(Curso curso) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(curso);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return curso;
    }

    @Override
    public List<Curso> buscarTodos() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Curso> query = builder.createQuery(Curso.class);
        Root<Curso> root = query.from(Curso.class);
        query.select(root);

        TypedQuery<Curso> typedQuery = entityManager.createQuery(query);
        List<Curso> list = typedQuery.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return list;
    }

    @Override
    public void excluir(Curso curso) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        curso = entityManager.merge(curso);
        entityManager.remove(curso);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public Curso buscarCurso(String curso) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT c FROM CURSO c");
        sb.append("WHERE c = :curso");

        entityManager.getTransaction().begin();
        TypedQuery<Curso> query = entityManager.createQuery(sb.toString(), Curso.class);
        query.setParameter("curso", curso);
        Curso cursoEncontrado = query.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();

        return cursoEncontrado;
    }

    @Override
    public Integer atualizar(Curso cursoBD) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Curso curso = entityManager.find(Curso.class, cursoBD.getId());
        curso.setCodigo(cursoBD.getCodigo());
        curso.setNome(cursoBD.getNome());
        curso.setDescricao(cursoBD.getDescricao());

        entityManager.getTransaction().begin();
        entityManager.merge(curso);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return 1;
    }
}
