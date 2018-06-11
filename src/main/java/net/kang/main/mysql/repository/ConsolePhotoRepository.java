package net.kang.main.mysql.repository;

import net.kang.main.mysql.domain.ConsolePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsolePhotoRepository extends JpaRepository<ConsolePhoto, Long> {
    List<ConsolePhoto> findByConId(String conId);
}
