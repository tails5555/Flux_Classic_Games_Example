package net.kang.main.repository;

import net.kang.main.domain.ScreenShot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScreenShotRepository extends MongoRepository<ScreenShot, String> {
}
