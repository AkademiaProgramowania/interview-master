package pl.pop.interview.master.practitioner;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PractitionerDTO {
    private Long id;

    public Long getId() {
        return id;
    }
}
