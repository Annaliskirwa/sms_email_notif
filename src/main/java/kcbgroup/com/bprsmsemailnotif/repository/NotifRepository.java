package kcbgroup.com.bprsmsemailnotif.repository;

import kcbgroup.com.bprsmsemailnotif.model.database.NotificationRequestDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifRepository extends JpaRepository<NotificationRequestDb, Integer> {
}
