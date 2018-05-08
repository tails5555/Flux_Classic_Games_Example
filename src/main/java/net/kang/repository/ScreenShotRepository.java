package net.kang.repository;

import net.kang.domain.ScreenShot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScreenShotRepository extends MongoRepository<ScreenShot, String> {
}
