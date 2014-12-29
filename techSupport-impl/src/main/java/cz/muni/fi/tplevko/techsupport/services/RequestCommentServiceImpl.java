package cz.muni.fi.tplevko.techsupport.services;

import cz.muni.fi.tplevko.techsupport.dao.RequestCommentDao;
import cz.muni.fi.tplevko.techsupport.entity.RequestComment;
import cz.muni.fi.tplevko.techsupport.entity.dto.RequestCommentDto;
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
@Service(value = "requestCommentService")
@Transactional
public class RequestCommentServiceImpl implements RequestCommentService {

    // Use logger
    private static final Logger log = Logger.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    //@Qualifier(value = "customerDao")
    private RequestCommentDao requestCommentDao;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Transactional
    public void createRequestComment(RequestCommentDto requestComment) {

        if (requestComment == null) {
            throw new IllegalArgumentException("Request Comment to be created is null");
        }

        if (requestComment.getId() != null) {
            throw new IllegalArgumentException("Request Comment has have id set");
        }

        RequestComment reqestComm = mapper.map(requestComment, RequestComment.class);
        log.info("new request comment created by : " + reqestComm.getCommenter().getEmail());
        requestCommentDao.createRequestComment(reqestComm);
    }

    @Override
    @Transactional
    public void updateRequestComment(RequestCommentDto requestComment) {

        if (requestComment == null) {
            throw new IllegalArgumentException("Request Comment to be updated is null");
        }

        if (requestComment.getId() != null) {
            throw new IllegalArgumentException("Request Comment has have id set");
        }

        RequestComment reqestComm = mapper.map(requestComment, RequestComment.class);
        requestCommentDao.updateRequestComment(reqestComm);
    }

    @Override
    @Transactional
    public void deleteRequestComment(RequestCommentDto requestComment) {
        if (requestComment == null) {
            throw new IllegalArgumentException("Request Comment to be updated is null");
        }

        if (requestComment.getId() != null) {
            throw new IllegalArgumentException("Request Comment has have id set");
        }

        RequestComment reqestComm = mapper.map(requestComment, RequestComment.class);
        requestCommentDao.deleteRequestComment(reqestComm);
    }

    @Override
    @Transactional(readOnly = true)
    public RequestCommentDto findRequestCommentById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("ID to retrieve can't be null");
        }
        RequestComment reqestComment = requestCommentDao.findRequestCommentById(id);

        return mapper.map(reqestComment, RequestCommentDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RequestCommentDto> getAllRequestComments() {

        List<RequestComment> requests = requestCommentDao.getAllRequestComments();

        List<RequestCommentDto> requestsReturn = new ArrayList<>();
        for (RequestComment r : requests) {
            requestsReturn.add(mapper.map(r, RequestCommentDto.class));
        }
        return requestsReturn;
    }
}
