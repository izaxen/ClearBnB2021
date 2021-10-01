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

    @Override
    public String toString() {
        return "DeleteRatingDTO{" +
                "id=" + id +
                '}';
    }
}
