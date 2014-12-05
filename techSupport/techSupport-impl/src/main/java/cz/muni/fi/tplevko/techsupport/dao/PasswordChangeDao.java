package cz.muni.fi.tplevko.techsupport.dao;

import cz.muni.fi.tplevko.techsupport.entity.PasswordChange;

/**
 *
 * @author tplevko
 */
public interface PasswordChangeDao {

    public String createPasswordChange(PasswordChange passwordChange);

    public void deletePasswordChange(PasswordChange passwordChange);

    public PasswordChange findPasswordChangeId(Long id);
}
