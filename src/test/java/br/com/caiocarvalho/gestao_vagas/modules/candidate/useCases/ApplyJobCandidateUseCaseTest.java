package br.com.caiocarvalho.gestao_vagas.modules.candidate.useCases;

import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.hibernate.id.uuid.UuidGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.caiocarvalho.gestao_vagas.exceptions.JobNotFoundException;
import br.com.caiocarvalho.gestao_vagas.exceptions.UserNotFoundException;
import br.com.caiocarvalho.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.caiocarvalho.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.caiocarvalho.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.caiocarvalho.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.caiocarvalho.gestao_vagas.modules.company.entities.JobEntity;
import br.com.caiocarvalho.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    public void shouldNotBeAbleToApplyJobWithJobNotFound() {
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(idCandidate, null);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    public void shoulBeAbleToCreateANewApplyJob() {
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder()
        .candidateId(idCandidate)
        .jobId(idJob).build();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));
        when(applyJobRepository.save(applyJob)).thenReturn(new ApplyJobEntity());

        var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        Assertions.assertThat(result).hasFieldOrProperty("id");
    }
}
