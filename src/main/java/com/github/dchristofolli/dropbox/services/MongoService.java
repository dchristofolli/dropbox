package com.github.dchristofolli.dropbox.services;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.stereotype.Service;

@Service
public class MongoService {
    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
    MongoDatabase db = mongoClient.getDatabase("dropbox");
    DBCollection collection = (DBCollection) db.getCollection("users");
}
