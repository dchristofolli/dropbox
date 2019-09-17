package com.github.dchristofolli.dropbox.services;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Service;

@Service
public class MongoService {
    MongoClient mongoClient = new MongoClient("localhost", 27017);
    DB db = mongoClient.getDB("dropbox");
}
