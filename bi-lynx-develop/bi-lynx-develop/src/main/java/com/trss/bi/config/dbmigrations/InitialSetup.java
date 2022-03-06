package com.trss.bi.config.dbmigrations;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@ChangeLog(order = "001")
public class InitialSetup {
    @ChangeSet(order = "01", author = "admin", id = "01-indexReport")
    public void indexReport(DB db) {
        DBCollection reportCollection = db.getCollection("report");

        // collab_key
        DBObject indexKeys = BasicDBObjectBuilder.start().add("collab_key", 1).get();
        DBObject indexOptions = BasicDBObjectBuilder.start().add("name", "report_collabkey_idx").get();
        reportCollection.createIndex(indexKeys, indexOptions);

        // user_id
        indexKeys = BasicDBObjectBuilder.start().add("user_id", 1).get();
        indexOptions = BasicDBObjectBuilder.start().add("name", "report_userid_idx").get();
        reportCollection.createIndex(indexKeys, indexOptions);

        // customer_id
        indexKeys = BasicDBObjectBuilder.start().add("customer_id", 1).get();
        indexOptions = BasicDBObjectBuilder.start().add("name", "report_customerid_idx").get();
        reportCollection.createIndex(indexKeys, indexOptions);
    }

    @ChangeSet(order = "02", author = "admin", id = "02-indexTemplateFavorites")
    public void indexTemplateFavorites(DB db) {
        DBCollection templateFavoritesCollection = db.getCollection("templateFavorites");

        // user_id
        DBObject indexKeys = BasicDBObjectBuilder.start().add("user_id", 1).get();
        DBObject indexOptions = BasicDBObjectBuilder.start().add("name", "templatefavorites_userid_idx").get();
        templateFavoritesCollection.createIndex(indexKeys, indexOptions);
    }
}
