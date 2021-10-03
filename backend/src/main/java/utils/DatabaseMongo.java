package utils;

import application.ListingLogic;
import application.Repositories;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import dtos.FilteredListingDTO;
import org.bson.Document;

import java.util.ArrayList;

import java.util.List;


public class DatabaseMongo {

    public MongoClient mongoClient = new MongoClient("localhost", 27017);
    public MongoDatabase database = mongoClient.getDatabase("clearbnb");
    public MongoCollection collection = database.getCollection("cache");

    ListingLogic listingLogic;
    Repositories repositories;

    public DatabaseMongo(Repositories repositories){
        this.repositories = repositories;
        this.listingLogic = new ListingLogic(repositories);
        test();
    }

    private void test(){

        System.out.println(database.listCollections());
        insertListingIntoDb();
    }

    private void insertListingIntoDb(){
        collection.deleteOne(Filters.eq("allListings", "FrontPage"));
        Document frontpage = new Document();
        Document allListings = new Document();
        List<FilteredListingDTO> dto1 = listingLogic.getAllListingsDTO();
        System.out.println(dto1.toString());
            ArrayList<Document> nyLista = new ArrayList<>();


        for (FilteredListingDTO item:dto1
             ) {
            Document nytt = new Document();
            nytt.put("description", item.getDescription());
            nytt.put("city", item.getCity());
            nytt.put("address", item.getAddress());
            nytt.put("price", item.getPrice());
            nytt.put("listingid", item.getID());
            nyLista.add(nytt);
        }
            frontpage.append("Frontpage", nyLista);
            allListings.append("allListings", frontpage);
        try{

            collection.insertOne(frontpage);
            System.out.println("Successfully inserted document of frontpage " + frontpage);

        }catch (Exception e){

            System.err.println(e.getClass().getName() +

                    ": " + e.getMessage());
        }
        //getAllListingFromMDB();
    }
    public Document getAllListingFromMDB(){
         //FindIterable<Document> doc = collection.find(Filters.eq("Frontpage",asList("kattafan"))).first();

        //System.out.println(doc.toJson());

        MongoCursor<Document> cursor = collection.find().iterator();
        try{
            while(cursor.hasNext()){
                System.out.println(cursor.next().toJson());
            }
        }finally {
            cursor.close();
        }

         return null;
    }
}
