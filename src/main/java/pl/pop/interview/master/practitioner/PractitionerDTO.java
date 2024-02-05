package pl.pop.interview.master.practitioner;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PractitionerDTO {
    private Long id;

    public static PractitionerDTO mapToDTO(Practitioner practitioner) {
        return PractitionerDTO.builder()
                .id(practitioner.getId())
                .build();
    }
}
