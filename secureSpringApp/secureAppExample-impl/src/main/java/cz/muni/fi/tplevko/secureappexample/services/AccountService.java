package cz.muni.fi.tplevko.secureappexample.services;

import cz.muni.fi.tplevko.secureappexample.entity.dto.AccountDto;
import java.util.List;

/**
 *
 * @author tplevko
 */
public interface AccountService {

    public void createAccount(AccountDto accountDto);

    public void updateAccount(AccountDto accountDto);

    public void deleteAccount(AccountDto accountDto);

    public AccountDto findAccount(Long id);

    public AccountDto findAccountByName(String name);

    public AccountDto findAccountByEmail(String email);

    public List<AccountDto> getAllAccounts();
    
    public void activateAccount(AccountDto account);
}
