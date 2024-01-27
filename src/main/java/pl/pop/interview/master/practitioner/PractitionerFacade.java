package pl.pop.interview.master.practitioner;

import java.util.List;
/**
 * The {@code PractitionerFacade} interface defines high-level operations for managing practitioners.
 * Practitioners are associated with user accounts, and this facade provides methods to create
 * new practitioners and retrieve information about existing practitioners.
 */
public interface PractitionerFacade {
    Practitioner createNewPractitioner();

    List<PractitionerDTO> listPractitioners();
}
