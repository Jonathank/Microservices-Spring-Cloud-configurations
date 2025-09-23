package app.nanaBank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import app.nanaBank.model.Accounts;
import jakarta.transaction.Transactional;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    /**
     * @param customerId
     * @return
     */
    Optional<Accounts> findByCustomerId(Long customerId);
    // Additional query methods can be defined here if needed

    /**
     * @param customerId
     */
    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);

}
