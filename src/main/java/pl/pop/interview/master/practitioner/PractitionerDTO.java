package pl.pop.interview.master.practitioner;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class PractitionerDTO {
    private long id;

    public long getId() {
        return id;
    }
}
