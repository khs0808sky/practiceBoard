package models.board;

import lombok.Data;

@Data
public class Board {
    private long idx;
    private String title;
    private String content;
}
