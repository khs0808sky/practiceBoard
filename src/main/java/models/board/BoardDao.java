package models.board;

import controllers.RegistForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BoardDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean insert(RegistForm registForm) {
        String sql = "INSERT INTO BOARD (IDX, TITLE, CONTENT) VALUES (BOARD_IDX.NEXTVAL, ?, ?)";
        int cnt = jdbcTemplate.update(sql, registForm.getTitle(), registForm.getContent());

        return cnt > 0;
    }

    public List<Board> getList() {
        String sql = "SELECT * FROM BOARD";
        List<Board> boards = jdbcTemplate.query(sql, this::mapper);
        return boards;
    }

    public Board getOne(String idx) {
        try {
            String sql = "SELECT * FROM BOARD WHERE IDX = ?";
            Board board = jdbcTemplate.queryForObject(sql, this::mapper, idx);
            return board;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Board mapper(ResultSet rs, int i) throws SQLException {
        Board board = new Board();
        board.setIdx(rs.getLong("idx"));
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
        return board;
    }
}
