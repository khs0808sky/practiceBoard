package models.board;

import controllers.RegistForm;
import org.springframework.stereotype.Service;

@Service
public class RegistService {
    private RegistValidator registValidator;
    private BoardDao boardDao;

    public RegistService(RegistValidator registValidator, BoardDao boardDao) {
        this.registValidator = registValidator;
        this.boardDao = boardDao;
    }

    public void regist(Board board) {
        registValidator.check(board);
    }

    public void insert(RegistForm registForm) {
        boardDao.insert(registForm);
    }
}
