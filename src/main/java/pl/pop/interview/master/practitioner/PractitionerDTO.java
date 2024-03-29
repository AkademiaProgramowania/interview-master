package pl.pop.interview.master.practitioner;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PractitionerDTO {
    private Long id;

    public static PractitionerDTO mapToDTO(Practitioner practitioner) {
        return PractitionerDTO.builder()
                .id(practitioner.getId())
                .build();
    }
}
