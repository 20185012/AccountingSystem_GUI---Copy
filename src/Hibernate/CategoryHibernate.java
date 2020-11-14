package Hibernate;

import Model.Category;
import Model.Payment;
import Model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CategoryHibernate {
    private EntityManagerFactory entityManagerFactory = null;

    public CategoryHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    private EntityManager getEntityManager() { return entityManagerFactory.createEntityManager(); }

    public void create(Category category)
    {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(entityManager.merge(category));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (entityManager != null)
            {
                entityManager.close();
            }
        }
    }

    public List<User> getUserList() {
        return getUserList(true, -1, -1);
    }

    public List<User> getUserList(boolean all, int maxRes, int firstRes) {

        EntityManager em = getEntityManager();
        try {

            CriteriaQuery criteriaQuery = em.getCriteriaBuilder().createQuery();
            criteriaQuery.select(criteriaQuery.from(User.class));
            Query query = em.createQuery(criteriaQuery);

            if (!all) {
                query.setMaxResults(maxRes);
                query.setFirstResult(firstRes);
            }
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public void edit(Category category) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            category = em.merge(category);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = Integer.toString(category.getCategoryID());
                if (findSection(id) == null) {
                    throw new Exception("The section with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Category findSection(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Category.class, id);
        } finally {
            em.close();
        }
    }

    public void remove(int id) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category category = null;
            try {
                category = em.getReference(Category.class, id);
                category.getCategoryID();

                Category parent = category.getParentCategory();
                parent.getSubCategories().remove(category);
                em.merge(parent);

                for (Category c : category.getSubCategories()) {
                    remove(c.getCategoryID());
                }
                category.getSubCategories().clear();
                em.merge(category);

            } catch (EntityNotFoundException enfe) {
                throw new Exception("The Category with id " + id + " no longer exists.", enfe);
            }
            em.remove(category);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Category> getCategoriesOfParent(Category parentCategory) {
        return getCategoriesOfParent(parentCategory,true, -1, -1);
    }

    public List<Category> getCategoriesOfParent(Category parentCategory, int maxResults, int firstResult) {
        return getCategoriesOfParent(parentCategory,false, maxResults, firstResult);
    }

    private List<Category> getCategoriesOfParent(Category parentCategory, boolean all, int maxResults, int firstResult ) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();

            Root from = cq.from(Category.class);
            cq.select(from);

            cq.where(em.getCriteriaBuilder().equal(from.get("parentCategory"), parentCategory));

            Query q = em.createQuery(cq);
            /*
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            */
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void AddExpense(Category category, Payment payment)
    {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            try {
                category.getExpense().add(payment);
                em.merge(category);
                em.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }




    public List<Category> getRootCategories() {
        return getRootCategories(true, -1, -1);
    }

    public List<Category> getRootCategories(int maxResults, int firstResult) {
        return getRootCategories(false, maxResults, firstResult);
    }

    private List<Category> getRootCategories(boolean all, int maxResults, int firstResult ) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();

            Root from = cq.from(Category.class);
            cq.select(from).where(from.get("parentCategory").isNull());

            Query q = em.createQuery(cq);
            /*
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            */
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
