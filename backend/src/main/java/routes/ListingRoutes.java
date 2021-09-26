package routes;

import application.ListingLogic;
import application.Repositories;
import com.mysql.cj.Session;
import dtos.FilteredListingDTO;
import express.Express;
import entityDO.Listing;
import entityDO.User;
import dtos.AddListingDTO;
import filter.ListingFilter;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import oracle.jdbc.OracleConnection;
import org.hibernate.annotations.Filter;
import repositories.ListingRepository;
import service.ListingService;

import java.sql.Timestamp;
import java.util.List;

public class ListingRoutes {

    private ListingLogic listingLogic;
    private ListingService ls;

    public ListingRoutes(Express app, Repositories repositories) {

        listingLogic= new ListingLogic(repositories);
        ls= new ListingService();

        app.post("/api/addListing", (req, res) -> {
            User currentUser = req.session("current-user");

            // req.body(AddListingDTO.class) is a fake object we got from frontpage

            // ls.convertAddListingToListing(req.body(AddListingDTO.class),currentUser)
            // turns our fake into the real object
            // currentUser is sent because we have USER in our entityDO for Listing

            // listingLogic.createNewListing saved that object to our DB

            Listing createdListing = listingLogic.createNewListing(
                    ls.convertAddListingToListing(
                            req.body(AddListingDTO.class),
                            currentUser
                    )
            );

//            req.body(Listing.class)
//            System compares what we got from frontend to Class Listing, if it has all attributes?

            req.session("current-Listing", createdListing);

            res.json(createdListing.getId());
        });

        //TODO CHECK IF NEEDED TO USE DTO
        app.get("/api/getAllListings", (req, res) -> {
           res.json(listingLogic.getAllListings());
//            System.out.println(listingLogic.getAllListings());
        });

        app.get("/api/getAllListingsDTO", (req, res) -> {
            res.json(listingLogic.getAllListingsDTO());
        });

        app.post("/api/getFilteredListing", (req, res) ->{
           // Listing list = req.body(Listing.class);

            // Kommer in nu är start & end
            //De skall verifieras och detta görs med DTO
            List<AddListingDTO> filteredListings = listingLogic.getFilteredListings(
            (req.body(ListingFilter.class)));

            //Jämnföra värden med alla och skapa en ny lista
            //getFilteredListings(Timestamp ts1, Timestamp ts2)
//            System.out.println("filteredListings: " + filteredListings);

            res.json(filteredListings);
        });
    }
}
