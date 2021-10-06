package routes;

import application.LogicHandler;
import application.RatingLogic;
import application.Repositories;
import dtos.DeleteRatingDTO;
import dtos.createNewRatingFromFrontendDTO;
import entityDO.User;
import express.Express;
import mapper.RatingMapper;

import static java.lang.Integer.parseInt;

public class RatingRoutes {

    Express app;
    LogicHandler logicHandler;

    public RatingRoutes(Express app, LogicHandler logicHandler) {
        this.app = app;
        this.logicHandler = logicHandler;
        getAllRatingsOfUser();
        getAvgRatingOfUser();
        checkIfThereIsAnyRatingToFill();
        createANewRating();
        deleteRating();
    }

    public void getAllRatingsOfUser(){
        app.get("/rest/rating/:userID", (req, res) ->{
            int userID = parseInt(req.params("userID"));
            res.json(logicHandler.getRatingLogic().getAllRatingsOfUser(userID));
        });
    }

    public void getAvgRatingOfUser(){
        app.get("/rest/avg-rating/:userID", (req, res) ->{
            int userID = parseInt(req.params("userID"));
            res.json(logicHandler.getRatingLogic().getAvgRatingsOfUser(userID));
        });
    }

    public void checkIfThereIsAnyRatingToFill(){
        app.get("/api/check-if-there-is-ratings-to-fill/", (req, res) ->{
            User currentUser = req.session("current-user");
            res.json(logicHandler.getRatingLogic().checkIfThereIsAnyRatingToFill(currentUser));
        });
    }

    public void createANewRating(){
        app.post("/api/createNewRating", (req, res) -> {
            logicHandler.getRatingLogic().createNewRating(req.body(createNewRatingFromFrontendDTO.class));
        });
    }

    public void deleteRating(){
        app.delete("/rest/delete-rating", (req, res) -> {
            DeleteRatingDTO dto = req.body(DeleteRatingDTO.class);
            res.json(logicHandler.getRatingLogic().deleteRating(dto.getId()));
        });
    }
}
