package com.github.dchristofolli.dropbox.mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;

public class MongoConfig {
    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
    DB db = mongoClient.getDB("dropbox");
    DBCollection collection = db.getCollection("users");
}
