package ru.neoflex.kafkatopostgresql.dao;

import ru.neoflex.kafkatopostgresql.model.ProcessedRequests;

import java.util.List;


public interface ProcessedRequestsDao {

    public ProcessedRequests findProcessedRequestsID(int id);

    public void addProcessedRequests(ProcessedRequests processedRequests);

    public void updateProcessedRequests(ProcessedRequests processedRequests);

    public void removeProcessedRequests(int id);

    public List<ProcessedRequests> listProcessedRequests();
}
