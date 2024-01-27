package pl.pop.interview.master.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

interface AccountRepository extends JpaRepository<Account, String> {
}
