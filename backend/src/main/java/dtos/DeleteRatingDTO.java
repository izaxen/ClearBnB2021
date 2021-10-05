package dtos;

public class DeleteRatingDTO {

    int id;

    public DeleteRatingDTO() {
    }

    public DeleteRatingDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
