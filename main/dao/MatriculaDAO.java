package dao;

import domain.Curso;
import domain.Matricula;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class MatriculaDAO implements IMatriculaDAO{

    @Override
    public Matricula cadastrar(Matricula matri) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(matri);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return matri;
    }

    @Override
    public List<Matricula> buscarTodos() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Matricula> query = builder.createQuery(Matricula.class);
        Root<Matricula> root = query.from(Matricula.class);
        query.select(root);

        TypedQuery<Matricula> typedQuery = entityManager.createQuery(query);
        List<Matricula> list = typedQuery.getResultList();

        entityManager.close();
        entityManagerFactory.close();
        return list;
    }

    @Override
    public void excluir(Matricula matri) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        matri = entityManager.merge(matri);
        entityManager.remove(matri);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public Matricula buscarPorCurso(Curso curso) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT m FROM Matricula m ");
        sb.append("INNER JOIN Curso c on c = m.curso ");
        sb.append("WHERE c = :curso");

        entityManager.getTransaction().begin();
        TypedQuery<Matricula> query = entityManager.createQuery(sb.toString(), Matricula.class);
        query.setParameter("curso", curso);
        Matricula matricula = query.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();

        return matricula;
    }

    @Override
    public Matricula buscarPorCodigoCurso(String codigoCurso) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT m FROM Matricula m ");
        sb.append("INNER JOIN Curso c on c = m.curso ");
        sb.append("WHERE c.codigo = :codigoCurso");

        entityManager.getTransaction().begin();
        TypedQuery<Matricula> query = entityManager.createQuery(sb.toString(), Matricula.class);
        query.setParameter("codigoCurso", codigoCurso);
        Matricula matricula = query.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();

        return matricula;
    }

    @Override
    public Matricula buscarPorCodigoCursoCriteria(String codigoCurso) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Matricula> query = builder.createQuery(Matricula.class);
        Root<Matricula> root = query.from(Matricula.class);
        Join<Object, Object> join = root.join("curso", JoinType.INNER);
        query.select(root).where(builder.equal(join.get("codigo"), codigoCurso));

        TypedQuery<Matricula> tpQuery = entityManager.createQuery(query);
        Matricula matricula = tpQuery.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();
        return matricula;
    }

    @Override
    public Matricula buscarPorCursoCriteria(Curso curso) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Matricula> query = builder.createQuery(Matricula.class);
        Root<Matricula> root = query.from(Matricula.class);
        Join<Object, Object> join = root.join("curso", JoinType.INNER);
        query.select(root).where(builder.equal(join, curso));

        TypedQuery<Matricula> tpQuery = entityManager.createQuery(query);
        Matricula matricula = tpQuery.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();

        return matricula;
    }
}
