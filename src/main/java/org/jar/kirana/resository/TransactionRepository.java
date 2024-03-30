package org.jar.kirana.resository;

import org.jar.kirana.model.objects.TransactionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<TransactionModel, String> {
}