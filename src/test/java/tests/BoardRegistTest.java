package tests;

import models.board.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("게시판 등록 테스트")
public class BoardRegistTest {

    private Board board;
    private RegistService registService;

    @BeforeEach
    void init() {
        BoardDao boardDao = new BoardDao();
        RegistValidator registValidator = new RegistValidator();
        registService = new RegistService(registValidator, boardDao);
        board = getBoard();
    }

    private Board getBoard() {
        Board board = new Board();
        board.setTitle("제목1");
        board.setContent("내용1");
        return board;
    }

    @Test
    @DisplayName("게시글 등록 성공 시 예외 발생하지 않음")
    void registSuccessTest() {
        assertDoesNotThrow(() -> {
            registService.regist(board);
        });
    }

    @Test
    @DisplayName("필수 항목 체크 (제목, 내용)")
    void requiredCheckTest() {
        assertAll(
                // 제목 - null
                () -> assertThrows(RegistValidationException.class, () -> {
                    Board board = new Board();
                    board.setTitle(null);
                    registService.regist(board);
                }),
                // 제목 - 빈값
                () -> assertThrows(RegistValidationException.class, () -> {
                    Board board = new Board();
                    board.setTitle("    ");
                    registService.regist(board);
                }),
                // 내용 - null
                () -> assertThrows(RegistValidationException.class, () -> {
                    Board board = new Board();
                    board.setContent(null);
                    registService.regist(board);
                }),
                // 내용 - 빈값
                () -> assertThrows(RegistValidationException.class, () -> {
                    Board board = new Board();
                    board.setContent("    ");
                    registService.regist(board);
                })
        );
    }

    @Test
    @DisplayName("제목, 내용 글자 수 체크")
    void lengthCheckTest() {
        assertAll(
                // 제목 25글자 초과
                () -> assertThrows(RegistValidationException.class, () -> {
                    board = getBoard();
                    board.setTitle("가나다라마가나다라마가나다라마가나다라마가나다라마가"); //26
                    registService.regist(board);
                }),
                // 내용 50글자 초과
                () -> assertThrows(RegistValidationException.class, () -> {
                    board = getBoard();
                    board.setContent("가나다라마가나다라마가나다라마가나다라마가나다라마가나다라마가나다라마가나다라마가나다라마가나다라마가"); //51
                    registService.regist(board);
                })
        );
    }
}
