package repositories;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import jakarta.persistence.EntityManager;
import org.bson.Document;

public class MongoDBRepository {
    EntityManager entityManager;

    public MongoDBRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Document getAllListingFromMDB(MongoCollection collection){
        return (Document) collection.find(Filters.eq(
                "query", "newList")).first();
    }

    public void updateAllListingCollection(Document frontpage, MongoCollection collection){
        collection.deleteOne(Filters.eq("query", "newList"));

        try{
            collection.insertOne(frontpage);
            System.out.println("Successfully inserted document of frontpage " + frontpage);
        }catch (Exception e){
            System.err.println(e.getClass().getName() +
                    ": " + e.getMessage());
        }
    }





}
