package net.kang.main.mysql.repository;

import net.kang.main.mysql.domain.ConsolePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsolePhotoRepository extends JpaRepository<ConsolePhoto, Long> {
}
