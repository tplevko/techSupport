package cz.muni.fi.tplevko.techsupport.services;

import cz.muni.fi.tplevko.techsupport.entity.dto.AccountDto;
import java.util.List;

/**
 *
 * @author tplevko
 */
public interface AccountService {

    public void createAccount(AccountDto account);

    public void updateAccount(AccountDto account);

    public void deleteAccount(AccountDto account);

    public AccountDto findAccountById(Long id);

    public AccountDto findAccountByEmail(String email);

    public List<AccountDto> getAllAccounts();

}
