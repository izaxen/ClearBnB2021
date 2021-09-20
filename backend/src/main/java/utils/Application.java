package utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.User;
import models.UserRepostitory;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class Application {

    public Application() {
        new Routes();
        }
    }
