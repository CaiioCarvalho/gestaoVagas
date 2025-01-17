package br.com.caiocarvalho.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    @Schema(example = "Desenvolvedora Java")
    private String description;

    @Schema(example = "mariajulia")
    private String username;

    @Schema(example = "juliam@gmail.com")
    private String email;
    private UUID id;

    @Schema(example = "Maria Julia Silva")
    private String name;
}
