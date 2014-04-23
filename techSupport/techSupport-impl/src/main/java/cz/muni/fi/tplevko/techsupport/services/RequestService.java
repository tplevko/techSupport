package cz.muni.fi.tplevko.techsupport.services;

import cz.muni.fi.tplevko.techsupport.entity.Request;

/**
 *
 * @author tplevko
 */
public interface RequestService {

    public void assignPriority(Request request, long priority);

}
