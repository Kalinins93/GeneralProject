package ru.neoflex.findcreditiduploadpdf.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.findcreditiduploadpdf.dao.ProcessedRequestsDao;
import ru.neoflex.findcreditiduploadpdf.model.ProcessedRequests;

import java.util.List;

@Service
public class ServiceProcessedRequestsImpl implements ServiceProcessedRequests {
    @Autowired
    private ProcessedRequestsDao processedRequestsDao;

    @Override
    public ProcessedRequests findProcessedRequestsByID(int id) {
        return processedRequestsDao.findProcessedRequestsID(id);
    }

    @Override
    public void addProcessedRequests(ProcessedRequests processedRequests)
    {
        this.processedRequestsDao.addProcessedRequests(processedRequests);
    }

    @Override
    public void updateProcessedRequests(ProcessedRequests processedRequests) {

    }

    @Override
    public void removeProcessedRequests(int id) {

    }

    @Override
    public List<ProcessedRequests> listProcessedRequests() {
        return null;
    }

    ;


}
