package cz.muni.fi.tplevko.techsupport.dao;

import cz.muni.fi.tplevko.techsupport.entity.RequestComment;
import java.util.List;

/**
 * Interface for the basic request comment DAO
 *
 * @author tplevko
 */
public interface RequestCommentDao {

    public void createRequestComment(RequestComment requestComment);

    public void updateRequestComment(RequestComment requestComment);

    public void deleteRequestComment(RequestComment requestComment);

    public RequestComment findRequestCommentById(Long id);

    public List<RequestComment> getAllRequestComments();
}
