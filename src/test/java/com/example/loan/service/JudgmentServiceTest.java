package com.example.loan.service;

import com.example.loan.domain.Application;
import com.example.loan.domain.Judgment;
import com.example.loan.dto.ApplicationDTO;
import com.example.loan.repository.ApplicationRepository;
import com.example.loan.repository.JudgmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static com.example.loan.dto.ApplicationDTO.*;
import static com.example.loan.dto.JudgmentDTO.Request;
import static com.example.loan.dto.JudgmentDTO.Response;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.optional;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JudgmentServiceTest {

    @InjectMocks
    private JudgmentServiceImpl judgmentService;

    @Mock
    private JudgmentRepository judgmentRepository;

    @Mock
    private ApplicationRepository applicationRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseOfNewJudgmentEntity_WHen_RequestNewJudgment() {
        Judgment judgment = Judgment.builder()
                .applicationId(1L)
                .name("Member Kim")
                .approvalAmount(BigDecimal.valueOf(5000000))
                .build();

        Request request = Request.builder()
                .applicationId(1L)
                .name("Member Kim")
                .approvalAmount(BigDecimal.valueOf(5000000))
                .build();

        // application find
        when(applicationRepository.findById(1L)).thenReturn(Optional.ofNullable(Application.builder().build()));
        // judgment save
        when(judgmentRepository.save(ArgumentMatchers.any(Judgment.class))).thenReturn(judgment);

        Response actual = judgmentService.create(request);

        assertThat(actual.getName()).isSameAs(judgment.getName());
        assertThat(actual.getApplicationId()).isSameAs(judgment.getApplicationId());
        assertThat(actual.getApprovalAmount()).isSameAs(judgment.getApprovalAmount());
    }


    @Test
    void Should_ReturnResponseOfExistJudgmentEntity_When_RequestExistJudgmentId() {

        Judgment entity = Judgment.builder()
                .judgmentId(1L)
                .build();
        when(judgmentRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));

        Response actual = judgmentService.get(1L);

        assertThat(actual.getJudgmentId()).isSameAs(1L);
    }

    @Test
    void Should_ReturnResponseOfExistJudgmentEntity_When_RequestExistApplicationId() {

        Judgment judgmentEntity = Judgment.builder()
                .judgmentId(1L)
                .build();

        Application applicationEntity = Application.builder()
                .applicationId(1L)
                .build();

        when(applicationRepository.findById(1L)).thenReturn(Optional.ofNullable(applicationEntity));
        when(judgmentRepository.findAllByApplicationId(1L)).thenReturn(Optional.ofNullable(judgmentEntity));

        Response actual = judgmentService.getJudgmentOfApplication(1L);

        assertThat(actual.getJudgmentId()).isSameAs(1L);
    }

    @Test
    void Should_ReturnUpdatedResponseOfExistJudgmentEntity_When_RequestUpdateExistJudgmentInfo() {

        Judgment entity = Judgment.builder()
                .judgmentId(1L)
                .name("Member Kim")
                .approvalAmount(BigDecimal.valueOf(5000000))
                .build();

        Request request = Request.builder()
                .name("Member Lee")
                .approvalAmount(BigDecimal.valueOf(10000000))
                .build();

        when(judgmentRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        when(judgmentRepository.save(ArgumentMatchers.any(Judgment.class))).thenReturn(entity);

        Response actual = judgmentService.update(1L, request);

        assertThat(actual.getJudgmentId()).isSameAs(1L);
        assertThat(actual.getName()).isSameAs(request.getName());
        assertThat(actual.getApprovalAmount()).isSameAs(request.getApprovalAmount());
    }

    @Test
    void Should_DeletedJudgmentEntity_When_RequestDeleteExistJudgmentInfo() {
        Judgment entity = Judgment.builder()
                .judgmentId(1L)
                .build();

        when(judgmentRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        when(judgmentRepository.save(ArgumentMatchers.any(Judgment.class))).thenReturn(entity);

        judgmentService.delete(1L);

        assertThat(entity.getIsDeleted()).isTrue();
    }

    @Test
    void Should_ReturnUpdateResponseOfExistJudgmentEntity_When_RequestGrantApprovalAmountExistJudgmentInfo() {

        Judgment judgmentEntity = Judgment.builder()
                .name("Member Kim")
                .applicationId(1L)
                .approvalAmount(BigDecimal.valueOf(5000000))
                .build();

        Application applicationEntity = Application.builder()
                .applicationId(1L)
                .approvalAmount(BigDecimal.valueOf(5000000))
                .build();

        when(judgmentRepository.findById(1L)).thenReturn(Optional.ofNullable(judgmentEntity));
        when(applicationRepository.findById(1L)).thenReturn(Optional.ofNullable(applicationEntity));
        when(applicationRepository.save(ArgumentMatchers.any(Application.class))).thenReturn(applicationEntity);

        GrantAmount actual = judgmentService.grant(1L);

        assertThat(actual.getApplicationId()).isSameAs(1L);
        assertThat(actual.getApprovalAmount()).isSameAs(applicationEntity.getApprovalAmount());
    }
}