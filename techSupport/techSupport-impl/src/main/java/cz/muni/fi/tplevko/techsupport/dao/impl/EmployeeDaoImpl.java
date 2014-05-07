package cz.muni.fi.tplevko.techsupport.dao.impl;

import cz.muni.fi.tplevko.techsupport.dao.EmployeeDao;
import cz.muni.fi.tplevko.techsupport.entity.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tplevko
 */
@Repository(value = "employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    @Override
    public void createEmployee(Employee employee) {

        if (employee == null) {
            throw new IllegalArgumentException("Employee to be created is null");
        }
        if (employee.getId() != null) {
            throw new IllegalArgumentException("Employee to be created has already assigned id");
        }

        validateEmployee(employee);
        em.persist(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {

        if (employee == null) {
            throw new IllegalArgumentException("Employee to be updated is null");
        }
        if (employee.getId() == null) {
            throw new IllegalArgumentException("Employee to be updated has null id");
        }
        if (findEmployeeById(employee.getId()) == null) {
            throw new IllegalArgumentException("Employee to be updated doesn't exist in DB");
        }

        validateEmployee(employee);

        em.merge(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee to be removed is null");
        }
        if (employee.getId() == null) {
            throw new IllegalArgumentException("Employee to be removed has not assigned id");
        }

        validateEmployee(employee);

        em.remove(em.contains(employee) ? employee : em.merge(employee));
    }

    @Override
    public Employee findEmployeeById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Employee id to be retrieved is null");
        }

        Employee result = null;
        result = em.find(Employee.class, id);
        return result;
    }

    @Override
    public Employee findEmployeeByEmail(String email) {

        Employee employee = null;
        final String qstring = "SELECT e FROM Employee e WHERE e.email = :email";

        try {

            TypedQuery<Employee> query = em.createQuery(qstring, Employee.class);
            query.setParameter("email", email);
            employee = query.getSingleResult();
            return employee;
        } catch (NoResultException ex) {
            return null;
        }

    }

    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> employees = new ArrayList<>();

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Employee.class));
        Query q = em.createQuery(cq);
        employees = q.getResultList();
        return employees;
    }

    // TODO : revise.. 
    private void validateEmployee(Employee employee) {
        if (employee.getLastName() == null) {
            throw new IllegalArgumentException("Employee first name must be set, it's null");
        }
        if (employee.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Employee first name must be set, it's empty");
        }
        if (employee.getFirstName() == null) {
            throw new IllegalArgumentException("Employee last name must be set, it's null");
        }
        if (employee.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("Employee last name must be set, it's empty");
        }
        if (employee.getPassword() == null) {
            throw new IllegalArgumentException("Employee password must be set, it's null");
        }
        if (employee.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Employee address must be set, it's empty");
        }
        if (employee.getEmail() == null) {
            throw new IllegalArgumentException("Employee email must be set, it's null");
        }
        if (employee.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Employee email must be set, it's empty");
        }
    }
}
