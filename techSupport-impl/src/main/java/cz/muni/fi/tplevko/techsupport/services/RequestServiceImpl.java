package cz.muni.fi.tplevko.techsupport.services;

import cz.muni.fi.tplevko.techsupport.dao.RequestDao;
import cz.muni.fi.tplevko.techsupport.entity.Request;
import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.RequestDto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tplevko
 */
@Service(value = "requestService")
@Transactional
public class RequestServiceImpl implements RequestService {

    // Use logger
    private static final Logger log = Logger.getLogger(RequestServiceImpl.class.getName());

    @Autowired
    //@Qualifier(value = "customerDao")
    private RequestDao requestDao;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Transactional
    public void createRequest(RequestDto requestDto) {

        if (requestDto == null) {
            throw new IllegalArgumentException("request to be created is null");
        }

        if (requestDto.getId() != null) {
            throw new IllegalArgumentException("request has have id set");
        }

        Request request = mapper.map(requestDto, Request.class);

        requestDao.createRequest(request);
    }

    @Override
    @Transactional
    public void updateRequest(RequestDto requestDto) {
        if (requestDto == null) {
            throw new IllegalArgumentException("Request to be created is null");
        }

        if (requestDto.getId() == null) {
            throw new IllegalArgumentException("Request has not set id");
        }

        Request request = mapper.map(requestDto, Request.class);
        requestDao.updateRequest(request);
    }

    @Override
    @Transactional
    public void deleteRequest(RequestDto requestDto) {

        if (requestDto == null) {
            throw new IllegalArgumentException("Request to be created is null");
        }

        if (requestDto.getId() == null) {
            throw new IllegalArgumentException("Request has not set id");
        }

        Request request = mapper.map(requestDto, Request.class);
        requestDao.deleteRequest(request);
    }

    @Override
    @Transactional(readOnly = true)
    public RequestDto findRequestById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("ID to retrieve can't be null");
        }
        Request product = requestDao.findRequestById(id);

        return mapper.map(product, RequestDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public RequestDto findRequestByOwner(CustomerDto owner) {

        //TODO
//      
//        if (owner == null) {
//            throw new IllegalArgumentException("name can't be nulll");
//        }
//        
//
//        Request request = requestDao.findRequestByCustomer(owner);
//
//        return mapper.map(request, ProductDto.class);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true)
    public RequestDto findRequestByAssignee(EmployeeDto assignee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true)
    public List<RequestDto> getAllRequests() {

        List<Request> requests = requestDao.getAllRequests();

        List<RequestDto> productsReturn = new ArrayList<>();
        for (Request p : requests) {
            productsReturn.add(mapper.map(p, RequestDto.class));
        }
        return productsReturn;
    }

    @Override
    public List<RequestDto> getActiveRequests() {

        List<Request> requests = requestDao.getActiveRequests();

        List<RequestDto> productsReturn = new ArrayList<>();
        for (Request p : requests) {
            productsReturn.add(mapper.map(p, RequestDto.class));
        }
        return productsReturn;
    }

}
