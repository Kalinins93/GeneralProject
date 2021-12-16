package ru.neoflex.findcreditiduploadpdf.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.neoflex.findcreditiduploadpdf.model.ProcessedRequests;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProcessedRequestsDaoImpl implements ProcessedRequestsDao {
    private static final Logger logger = LoggerFactory.getLogger(ProcessedRequestsDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public ProcessedRequests findProcessedRequestsID(int id) {
        Integer findId=Integer.parseInt(String.valueOf(id));
        Session session =this.sessionFactory.getCurrentSession();
        ProcessedRequests processedRequests = (ProcessedRequests) session.load(ProcessedRequests.class, Integer.valueOf(findId));
        logger.info(processedRequests.toString());
        return processedRequests;
    }


    @Override
    public void addProcessedRequests(ProcessedRequests processedRequests) {
        Session session =this.sessionFactory.getCurrentSession();
        session.persist(processedRequests);

    }

    @Override
    public void updateProcessedRequests(ProcessedRequests ProcessedRequests) {
        Session session =this.sessionFactory.getCurrentSession();
        session.update(ProcessedRequests);
    }


    @Override
    public void removeProcessedRequests(int id) {
        Integer removeId=Integer.parseInt(String.valueOf(id));
        Session session =this.sessionFactory.getCurrentSession();
        ProcessedRequests processedRequests = (ProcessedRequests) session.load(ProcessedRequests.class, Integer.valueOf(removeId));
        if(processedRequests!=null){
            session.delete(processedRequests);
        }
    }


        @Override
        @SuppressWarnings("unchecked")
         public List<ProcessedRequests> listProcessedRequests() {
        Session session =this.sessionFactory.getCurrentSession();
        List<ProcessedRequests> processedRequests =session.createSQLQuery("select * from ProcessedRequests").addEntity(ProcessedRequests.class).list();

        return processedRequests;
    }
}
