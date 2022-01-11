package ru.neoflex.findcreditiduploadpdf.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import net.bytebuddy.pool.TypePool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;
import ru.neoflex.findcreditiduploadpdf.model.ProcessedRequests;
import ru.neoflex.findcreditiduploadpdf.services.ServiceProcessedRequests;
import ru.neoflex.findcreditiduploadpdf.utils.GeneratePdfReport;

import java.io.ByteArrayInputStream;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);
    @Autowired
    private ServiceProcessedRequests processedRequests;

    @RequestMapping(value = "/generate-agreement/{requestId}", method = GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public Mono<ResponseEntity<InputStreamResource>> citiesReport(@PathVariable("requestId") long requestId) {
        try {
            ProcessedRequests credit = processedRequests.findProcessedRequestsByID((int) requestId);
            logger.info(credit.toString());
            if (credit != null && "ОДОБРЕН".contains(credit.getStatus())) {
                ByteArrayInputStream bis = GeneratePdfReport.pdfReports(credit);

                var headers = new HttpHeaders();
                headers.add("Content-Disposition", "inline; filename=creditreport.pdf");

                return Mono.just(ResponseEntity
                        .ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(new InputStreamResource(bis)));
            } else {
                return Mono.just(ResponseEntity
                        .status(405).body(null));
            }
        } catch (Exception e) {
            return Mono.just(ResponseEntity
                    .status(405).body(null));
        }

    }
}
