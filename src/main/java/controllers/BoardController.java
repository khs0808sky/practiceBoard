package controllers;

import models.board.Board;
import models.board.BoardDao;
import models.board.RegistService;
import models.board.RegistValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private RegistValidator registValidator;

    @Autowired
    private RegistService registService;

    @Autowired
    private BoardDao boardDao;

    @GetMapping("/regist")
    public String regist(@ModelAttribute RegistForm registForm) {

        return "board/regist";
    }

    @PostMapping("/regist")
    public String registPs(@Valid RegistForm registForm, Errors errors) {

        registValidator.validate(registForm, errors);

        if (errors.hasErrors()) {

            return "board/regist";
        }

        registService.insert(registForm);

        return "redirect:/";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boards = boardDao.getList();
        System.out.println(boards);

        model.addAttribute("boards", boards);

        return "board/list";
    }

    @GetMapping("/detail")
    public String detail(HttpServletRequest request, Model model) {

        Board board = boardDao.getOne(request.getParameter("idx"));
        model.addAttribute("board", board);

        return "board/detail";
    }
}
