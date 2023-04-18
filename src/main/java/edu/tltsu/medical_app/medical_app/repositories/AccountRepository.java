package edu.tltsu.medical_app.medical_app.repositories;

import edu.tltsu.medical_app.medical_app.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  Account findAccountByLogin(String login);

}
