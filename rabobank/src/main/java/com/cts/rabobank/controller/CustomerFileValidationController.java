package com.cts.rabobank.controller;

import com.cts.rabobank.exceptionhandling.RecordParseException;
import com.cts.rabobank.exceptionhandling.ResourceNotFoundException;
import com.cts.rabobank.model.ValidationRequest;
import com.cts.rabobank.model.Response;
import com.cts.rabobank.service.CustomerFileValidationService;
import com.cts.rabobank.utils.ProcessUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * processing file_validation Controller
 */

@RestController
@RequestMapping("/v1")
@Slf4j
public class CustomerFileValidationController {
    private static final String INVALID_FILE = "Invalid file format exception";
    private static final String EMPTY_FILE = "File should not be empty";
    @Autowired
    CustomerFileValidationService customerFileValidationService;

    /**
     * Validation request file format and processing remaining validation
     * @param file
     * @return
     * @throws RecordParseException
     * @throws ResourceNotFoundException
     */
    @PostMapping(value = "/rabobank")
    public ResponseEntity<Response> validateFile(@RequestParam("file") MultipartFile file) throws RecordParseException, ResourceNotFoundException {
        if (file.isEmpty()) {
            throw new ResourceNotFoundException(EMPTY_FILE);
        }
        String contentType = file.getContentType();
        log.info("contentType{}::", contentType);
        if (contentType != null && (contentType.equals("text/xml") || contentType.equals("text/csv"))) {
            List<ValidationRequest> recordList = customerFileValidationService.process(file, contentType);
            return ProcessUtils.processResponse(recordList, "Validation successfully done", 200);
        } else {
            throw new RecordParseException(INVALID_FILE);
        }
    }
}
