package controllers;

import domain.Question;
import domain.Rendezvous;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.QuestionService;
import services.RendezvousService;
import services.UserService;

import java.util.Collection;

@Controller
@RequestMapping("/question/user")
public class QuestionUserController extends AbstractController {

    // Services --------------------------------------------
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    // Constructor -----------------------------------------
    public QuestionUserController() {
        super();
    }

    // Creation --------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView result;
        Question question;

        question = questionService.create();
        result = this.createEditModelAndView(question);

        return result;
    }


    // listing ---------------------------------------------



    protected ModelAndView createEditModelAndView(Question question) {
        ModelAndView result;

        result = this.createEditModelAndView(question, null);
        return result;
    }

    protected ModelAndView createEditModelAndView(Question question,  String messageCode) {
        ModelAndView result;
        Collection<Rendezvous> rendezvousCollection;
        User user ;

        user = userService.findByPrincipal();
        rendezvousCollection = user.getRendezvouses();

        result = new ModelAndView("question/edit");
        result.addObject("question", question);
        result.addObject("rendezvousCollection", rendezvousCollection);
        result.addObject("cancelUri", "welcome/index.do");
        result.addObject("message", messageCode);
        return result;
    }
}
