package cz.muni.fi.tplevko.techsupport.dao;

import cz.muni.fi.tplevko.techsupport.entity.Account;
import cz.muni.fi.tplevko.techsupport.entity.RequestComment;
import java.util.List;

/**
 *
 * @author tplevko
 */
public interface RequestCommentDao {
    
    public void createRequestComment(RequestComment requestComment);

    public void updateRequestComment(RequestComment requestComment);

    public void deleteRequestComment(RequestComment requestComment);

    public RequestComment findRequestCommentById(Long id);

    public List<RequestComment> findRequestCommentByCommenter(Account owner);

    public List<RequestComment> getAllRequestComments();
}
