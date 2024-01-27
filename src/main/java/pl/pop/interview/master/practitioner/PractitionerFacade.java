package pl.pop.interview.master.practitioner;

import java.util.List;

public interface PractitionerFacade {
    Practitioner createNewPractitioner();

    List<PractitionerDTO> listPractitioners();
}
