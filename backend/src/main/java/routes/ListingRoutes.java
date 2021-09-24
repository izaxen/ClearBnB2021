package routes;

import application.ListingLogic;
import com.mysql.cj.Session;
import express.Express;
import entityDO.Listing;
import entityDO.User;
import dtos.AddListingDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import oracle.jdbc.OracleConnection;
import org.hibernate.annotations.Filter;
import repositories.ListingRepository;
import service.ListingService;

public class ListingRoutes {

    private ListingLogic listingLogic;
    private ListingService ls;

    public ListingRoutes(Express app, ListingRepository listingRepository) {

        listingLogic= new ListingLogic(listingRepository);
        ls= new ListingService();

        app.post("/api/addListing", (req, res) -> {
            User currentUser = req.session("current-user");

            Listing createdListing = listingLogic.createNewListing(
                    ls.convertAddListingToListing(
                            req.body(AddListingDTO.class),
                            currentUser
                    )
            );

            req.session("current-Listing", createdListing);

            res.json(createdListing.getId());

        });

    }
}
