package cz.muni.fi.tplevko.techsupport.services;

import cz.muni.fi.tplevko.techsupport.entity.dto.RequestCommentDto;
import java.util.List;

/**
 *
 * @author tplevko
 */
public interface RequestCommentService {

    public void createRequestComment(RequestCommentDto requestComment);

    public void updateRequestComment(RequestCommentDto requestComment);

    public void deleteRequestComment(RequestCommentDto requestComment);

    public RequestCommentDto findRequestCommentById(Long id);

    public List<RequestCommentDto> getAllRequestComments();
}
