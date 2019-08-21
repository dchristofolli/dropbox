package com.github.dchristofolli.dropbox.services;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Service;

@Service
public class MongoConfig {
    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
    DB db = mongoClient.getDB("dropbox");
    DBCollection collection = db.getCollection("users");
}
