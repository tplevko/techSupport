package cz.muni.fi.tplevko.techsupport.services;

import cz.muni.fi.tplevko.techsupport.entity.dto.PasswordChangeDto;

/**
 *
 * @author tplevko
 */
public interface PasswordChangeService {

    public void createPasswordChange(PasswordChangeDto passwordChange);

    public void deletePasswordChange(PasswordChangeDto passwordChange);

    public PasswordChangeDto findPasswordChangeId(Long id);
}
