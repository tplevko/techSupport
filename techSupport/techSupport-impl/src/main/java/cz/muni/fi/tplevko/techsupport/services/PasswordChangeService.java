package cz.muni.fi.tplevko.techsupport.services;

import cz.muni.fi.tplevko.techsupport.entity.dto.AccountDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.PasswordChangeDto;

/**
 *
 * @author tplevko
 */
public interface PasswordChangeService {

    public String createPasswordChange(PasswordChangeDto passwordChangeDto);

    public void deletePasswordChange(PasswordChangeDto passwordChangeDto);

    public PasswordChangeDto findPasswordChangeById(String id);
    
    public PasswordChangeDto findPasswordChangeByAccount(AccountDto accountDto);
}
