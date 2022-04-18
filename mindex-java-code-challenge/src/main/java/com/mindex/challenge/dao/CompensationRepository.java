package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
//import com.mindex.challenge.data.MongoDBComparable;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import org.springframework.data.mongodb.repository.Query;

@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    List <Compensation> findByEmployeeEmployeeId(String id);
}
