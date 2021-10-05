package application;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import dtos.FilteredListingDTO;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class CacheLogic {

    MongoCollection collection;
    LogicHandler logicHandler;
    Repositories repositories;

    public CacheLogic(MongoCollection collection,Repositories repositories ,LogicHandler logicHandler){
        this.collection = collection;
        this.logicHandler = logicHandler;
        this.repositories = repositories;

    }

    public void updateMongoDBFromSql(){

        Document frontpage = new Document();
        ArrayList<Document> nyLista = new ArrayList<>();
        List<FilteredListingDTO> dto1 =  logicHandler.listingLogic.getAllListingsDTO();
        for (FilteredListingDTO item:dto1
        ) {
            Document nytt = new Document();
            nytt.put("description", item.getDescription());
            nytt.put("city", item.getCity());
            nytt.put("address", item.getAddress());
            nytt.put("price", item.getPrice());
            nytt.put("id", item.getID());
            nyLista.add(nytt);
        }
        frontpage.append("query", "newList");
        frontpage.append("allListings", nyLista);
         repositories.mongoDBRepository.updateAllListingCollection(frontpage, collection);
    }
}
