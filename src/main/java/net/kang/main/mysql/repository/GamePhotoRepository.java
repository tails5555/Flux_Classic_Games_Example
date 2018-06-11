package net.kang.main.mysql.repository;

import net.kang.main.mysql.domain.GamePhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GamePhotoRepository extends JpaRepository<GamePhoto, Long> {
    List<GamePhoto> findByGmId(String gmId);
}
