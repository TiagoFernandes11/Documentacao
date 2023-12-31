package uservices.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uservices.domain.Entity.Alarm;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Integer> {
}
