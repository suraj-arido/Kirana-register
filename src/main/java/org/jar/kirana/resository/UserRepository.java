package org.jar.kirana.resository;

import org.jar.kirana.model.objects.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String>{
    UserModel findByEmail(String email);
    UserModel findOneByEmail(String email);
}
