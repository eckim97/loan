package com.example.loan.controller;

import com.example.loan.dto.ApplicationDTO;
import com.example.loan.dto.JudgmentDTO;
import com.example.loan.dto.ResponseDTO;
import com.example.loan.service.JudgmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.loan.dto.ApplicationDTO.*;
import static com.example.loan.dto.JudgmentDTO.Request;
import static com.example.loan.dto.JudgmentDTO.Response;

@RequiredArgsConstructor
@RestController
@RequestMapping("/judgments")
public class JudgmentController extends AbstractController {

    private final JudgmentService judgmentService;

    @PostMapping
    public ResponseDTO<Response> create(@RequestBody Request request) {
        return ok(judgmentService.create(request));
    }

    @GetMapping("/{judgmentId}")
    public ResponseDTO<Response> get(@PathVariable Long judgmentId) {
        return ok(judgmentService.get(judgmentId));
    }

    @GetMapping("/applications/{applicationId}")
    public ResponseDTO<Response> getJudgmentOfApplication(@PathVariable Long applicationId) {
        return ok(judgmentService.getJudgmentOfApplication(applicationId));
    }

    @PutMapping("/{judgmentId}")
    public ResponseDTO<Response> update(@PathVariable Long judgmentId, @RequestBody Request request) {
        return ok(judgmentService.update(judgmentId, request));
    }

    @DeleteMapping("/{judgmentId}")
    public ResponseDTO<Void> delete(@PathVariable Long judgmentId) {
        judgmentService.delete(judgmentId);
        return ok();
    }

    @PatchMapping("/{judgmentId}/grants")
    public ResponseDTO<GrantAmount> grant(@PathVariable Long judgmentId) {
        return ok(judgmentService.grant(judgmentId));
    }
}
