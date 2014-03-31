package cz.muni.fi.tplevko.secureappexample.dao.impl;

import cz.muni.fi.tplevko.secureappexample.dao.ItemDao;
import cz.muni.fi.tplevko.secureappexample.entity.Item;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tplevko
 */
@Repository(value = "itemDao")
public class ItemDaoImpl implements ItemDao {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void createItem(Item item) {

        if (item == null) {
            throw new IllegalArgumentException("Item to be created is null");
        }
        if (item.getId() != null) {
            throw new IllegalArgumentException("Item to be created has already assigned id");
        }

        validateItem(item);
        em.persist(item);

    }

    @Override
    public void deleteItem(Item item) {

        if (item == null) {
            throw new IllegalArgumentException("Item to be created is null");
        }
        if (item.getId() == null) {
            throw new IllegalArgumentException("Item to be deleted has not assigned id");
        }

        validateItem(item);
        em.remove(item);
    }

    @Override
    public void updateItem(Item item) {

        if (item == null) {
            throw new IllegalArgumentException("Item to be updated is null");
        }
        if (item.getId() == null) {
            throw new IllegalArgumentException("Item to be updated has no ID set");
        }
        if (findItemById(item.getId()) == null) {
            throw new IllegalArgumentException("Item to be updated doesn't exist in DB");
        }

        validateItem(item);
        em.merge(item);
    }

    @Override
    public Item findItemById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Item id to be retrieved is null");
        }

        Item result = null;
        result = em.find(Item.class, id);
        return result;
    }

    @Override
    public Item findItemByName(String name) {

        Item item = null;
        final String qstring = "SELECT e FROM Item e WHERE e.name = :name";

        TypedQuery<Item> query = em.createQuery(qstring, Item.class);
        query.setParameter("name", name);
        item = query.getSingleResult();
        return item;
    }

    private void validateItem(Item item) {
        if (item.getName() == null) {
            throw new IllegalArgumentException("Item name must be set, it's null");
        }
        if (item.getName().isEmpty()) {
            throw new IllegalArgumentException("Item name must be set, it's empty");
        }
//        if (item.getCustomer() == null) {
//            throw new IllegalArgumentException("Customer must be set, it's null");
//        }
    }
}
