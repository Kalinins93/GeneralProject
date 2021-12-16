package ru.neoflex.findcreditiduploadpdf.services;


import ru.neoflex.findcreditiduploadpdf.model.ProcessedRequests;

import java.util.List;

public interface ServiceProcessedRequests {

    public ProcessedRequests findProcessedRequestsByID(int id);

    public void addProcessedRequests(ProcessedRequests processedRequests);

    public void updateProcessedRequests(ProcessedRequests processedRequests);

    public void removeProcessedRequests(int id);

    public List<ProcessedRequests> listProcessedRequests();

}
