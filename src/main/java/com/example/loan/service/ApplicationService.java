package com.example.loan.service;

import static com.example.loan.dto.ApplicationDTO.Request;
import static com.example.loan.dto.ApplicationDTO.Response;

public interface ApplicationService {

    Response create(Request request);
}
