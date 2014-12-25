package cz.muni.fi.tplevko.techsupport.services;

import cz.muni.fi.tplevko.techsupport.dao.PasswordChangeDao;
import cz.muni.fi.tplevko.techsupport.entity.PasswordChange;
import cz.muni.fi.tplevko.techsupport.entity.dto.PasswordChangeDto;
import java.util.logging.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tplevko
 */
@Service(value = "passwordChangeService")
@Transactional
public class PasswordChangeServiceImpl implements PasswordChangeService {

    // Use logger
    private static final Logger log = Logger.getLogger(PasswordChangeServiceImpl.class.getName());

    @Autowired
    private PasswordChangeDao passwordChangeDao;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Transactional
    public String createPasswordChange(PasswordChangeDto passwordChangeDto) {

        if (passwordChangeDto == null) {
            throw new IllegalArgumentException("Password Change to be created is null");
        }

        PasswordChange passwordChange = mapper.map(passwordChangeDto, PasswordChange.class);

        String id = passwordChangeDao.createPasswordChange(passwordChange);

        return id;
    }

    @Override
    @Transactional
    public void deletePasswordChange(PasswordChangeDto passwordChangeDto) {

        if (passwordChangeDto == null) {
            throw new IllegalArgumentException("password Change to be created is null");
        }

        if (passwordChangeDto.getId() == null) {
            throw new IllegalArgumentException("password Change has not set id");
        }

        PasswordChange passwordChange = mapper.map(passwordChangeDto, PasswordChange.class);
        passwordChangeDao.deletePasswordChange(passwordChange);
    }

    @Override
    public PasswordChangeDto findPasswordChangeById(String id) {

        if (id == null) {
            throw new IllegalArgumentException("ID to retrieve can't be null");
        }

        PasswordChange passwordChange = passwordChangeDao.findPasswordChangeId(id);

        return mapper.map(passwordChange, PasswordChangeDto.class);
    }

    @Override
    public void updatePasswordChange(PasswordChangeDto passwordChangeDto) {

        if (passwordChangeDto == null) {
            throw new IllegalArgumentException("passwordChangeDto to be created is null");
        }

        if (passwordChangeDto.getId() == null) {
            throw new IllegalArgumentException("passwordChangeDto has not set id");
        }

        PasswordChange passwordChange = mapper.map(passwordChangeDto, PasswordChange.class);
        passwordChangeDao.updatePasswordChange(passwordChange);
    }
}
