package br.edu.utfpr.model.service;

import java.util.List;
import br.edu.utfpr.model.dao.AbstractDAO;
import br.edu.utfpr.util.JPAUtil;
import java.io.Serializable;

public class AbstractService<PK, T> implements Serializable {

    protected AbstractDAO<PK, T> dao;

    public boolean save(T entity) {
        boolean isSuccess = true;
        try {
            JPAUtil.beginTransaction();
            dao.save(entity);
            JPAUtil.commit();
        } catch (Exception e) {
            System.out.println("Exceptio **********************");
            e.printStackTrace();
            JPAUtil.rollBack();
            isSuccess = false;
        } finally {
            JPAUtil.closeEntityManager();
        }
        return isSuccess;
    }

    public T update(T entity) {
        try {
            JPAUtil.beginTransaction();
            entity = dao.update(entity);
            JPAUtil.commit();
        } catch (Exception e) {
            e.printStackTrace();
            JPAUtil.rollBack();
        } finally {
            JPAUtil.closeEntityManager();
        }

        return entity;
    }

    public boolean delete(T entity) {
        boolean isSuccess = true;
        try {
            JPAUtil.beginTransaction();
            dao.delete(entity);
            System.out.println("Dando commit na remocao");
            JPAUtil.commit();
        } catch (Exception e) {
            JPAUtil.rollBack();
            isSuccess = false;
            System.out.println("Falhou na remoção");
            e.printStackTrace();
        } finally {
            System.out.println("Finally na remoção");
            JPAUtil.closeEntityManager();
        }
        return isSuccess;
    }

    public T getById(PK pk) {
        T entity = null;

        try {
            JPAUtil.beginTransaction();
            entity = dao.getById(pk);
            JPAUtil.commit();
        } catch (Exception e) {
            JPAUtil.rollBack();
        } finally {
            JPAUtil.closeEntityManager();
        }

        return entity;
    }

    public T getByProperty(String propertyName, String propertyValue) {
        T entity = null;
        try {
            JPAUtil.beginTransaction();
            entity = dao.getByProperty(propertyName, propertyValue);
            JPAUtil.commit();
        } catch (Exception e) {
            JPAUtil.rollBack();
            e.printStackTrace();
        } finally {
            JPAUtil.closeEntityManager();
        }

        return entity;
    }

    public List<T> findAll() {
        return dao.findAll();
    }
}
