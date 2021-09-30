package routes;

import application.RatingLogic;
import application.Repositories;
import dtos.CreateRatingDTO;
import dtos.FrontendRatingDTO;
import entityDO.Rating;
import entityDO.User;
import express.Express;

import static java.lang.Integer.parseInt;

public class RatingRoutes {

    Express app;
    Repositories repositories;
    RatingLogic ratingLogic;

    public RatingRoutes(Express app, Repositories repositories) {
        this.app = app;
        this.repositories = repositories;
        this.ratingLogic = new RatingLogic(repositories);
        getAllRatingsOfUser();
        getAvgRatingOfUser();
        checkIfThereIsAnyRatingToFill();
        createANewRating();
    }

    public void getAllRatingsOfUser(){
        app.get("/rest/rating/:userID", (req, res) ->{
            int userID = parseInt(req.params("userID"));
            res.json(ratingLogic.getAllRatingsOfUser(userID));
        });
    }

    public void getAvgRatingOfUser(){
        app.get("/rest/avg-rating/:userID", (req, res) ->{
            int userID = parseInt(req.params("userID"));
            res.json(ratingLogic.getAvgRatingsOfUser(userID));
        });
    }

    public void checkIfThereIsAnyRatingToFill(){
        app.get("/rest/check-if-there-is-ratings-to-fill/:userID", (req, res) ->{
            User currentUser = req.session("current-user");
            System.out.println(currentUser.toString());
            res.json(ratingLogic.checkIfThereIsAnyRatingToFill(currentUser));
        });
    }

    public void createANewRating(){
        app.post("/rest/createNewRating", (req, res) -> {
            FrontendRatingDTO dto = req.body(FrontendRatingDTO.class);
            ratingLogic.createNewRating(dto);
        });
    }
}
