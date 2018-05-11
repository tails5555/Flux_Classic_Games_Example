package net.kang.main.mysql_repository;

import net.kang.main.mysql_domain.ConsolePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsolePhotoRepository extends JpaRepository<ConsolePhoto, Long> {
}
