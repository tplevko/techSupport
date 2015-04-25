//package cz.muni.fi.tplevko.techsupport.managedbeans.init;
//
//import cz.muni.fi.tplevko.techsupport.services.AccountService;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.logging.Logger;
//import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSessionEvent;
//import javax.servlet.http.HttpSessionListener;
//import org.picketlink.common.constants.GeneralConstants;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
///**
// *
// * @author tplevko
// */
////@Component(value = "sessionStartListener")
////@ManagedBean
////@Scope("request")
//public class SessionStartListener implements HttpSessionListener {
//
////    @Autowired
////    private AccountService accountService;
//
////    @Autowired
////    private HttpSession httpSession;
//    private static final Logger LOG = Logger.getLogger(SessionStartListener.class.getName());
////
////    @PostConstruct
////    private void initMethod() {
////        
////        
////    }
//    
//    @Override
//    public void sessionCreated(HttpSessionEvent arg0) {
//
//        HttpSession newSession = arg0.getSession();
//
//        Map<String, List<Object>> sessionMap
//                = (Map<String, List<Object>>) newSession.getAttribute(GeneralConstants.SESSION_ATTRIBUTE_MAP);
//
//        Set<String> keySet = sessionMap.keySet();
////        sessionMap.get("emailAddress");
////        sessionMap.get("givenName");
////        sessionMap.get("userId");
//
//        LOG.info("***************************");
//        LOG.info("***************************");
//        LOG.info("***************************");
//        LOG.info(keySet.toString());
//        LOG.info("***************************");
//        LOG.info("emailAddress : " + ((String) sessionMap.get("emailAddress").get(0)));
//        LOG.info("***************************");
//        LOG.info("givenName : " + ((String) sessionMap.get("givenName").get(0)));
//        LOG.info("***************************");
//        LOG.info("surname : " + (String) sessionMap.get("surname").get(0));
//        LOG.info("***************************");
//        LOG.info("userid : " + (String) sessionMap.get("userid").get(0));
//        LOG.info("***************************");
//        LOG.info("***************************");
//        LOG.info("***************************");
//
//    }
//
//    @Override
//    public void sessionDestroyed(HttpSessionEvent arg0) {
////      System.out.println("sessionDestroyed - deduct one session from counter");
//    }
//    
////    private boolean checkExistenceOfUserProfile() {
////        
//////        return accountService.findAccountByEmail();
////    }
//}
