package com.example.loan.service;


import com.example.loan.dto.ApplicationDTO;

import static com.example.loan.dto.JudgmentDTO.*;

public interface JudgmentService {

    Response create(Request request);

    Response get(Long judgmentId);

    Response getJudgmentOfApplication(Long applicationId);

    Response update(Long judgmentId, Request request);

    void delete(Long judgmentId);

    ApplicationDTO.GrantAmount grant(Long judgmentId);
}

