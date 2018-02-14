//package services;
//
//import domain.Comment;
//import domain.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.Assert;
//import repositories.CommentRepository;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//
//@Service
//@Transactional
//public class CommentService {
//
//    // Managed Repository -----------------------------------------------------
//
//    @Autowired
//    private CommentRepository commentRepository;
//
//    // Supporting services ----------------------------------------------------
//
//    @Autowired
//    private UserService userService;
//
//    // Constructors -----------------------------------------------------------
//
//    public CommentService(){
//        super();
//    }
//
//    // Simple CRUD methods ----------------------------------------------------
//
//    public Comment create(){
//
//        Comment result;
//        User user;
//        Collection<Comment> childrenComments;
//
//        user = this.userService.findByPrincipal();
//        childrenComments = new ArrayList<Comment>();
//
//        result = new Comment();
//
//        result.setUser(user);
//        result.setMoment(new Date(System.currentTimeMillis() - 1000));
//        result.setChildrenComments(childrenComments);
//
//        user.getComments().add(result);
//
//        return result;
//
//    }
//
//    public Comment save(Comment comment){
//
//        Assert.notNull(comment);
//
//        Comment res = comment;
//
//        res.setMoment(new Date(System.currentTimeMillis()-1000));
//
//        if(res.getParentComment() == null){
//            this.commentRepository.save(comment);
//        }else{
//            res.getChildrenComments().add(comment);
//            this.commentRepository.save(res);
//        }
//
//        return res;
//    }
//
//    // Other business methods -------------------------------------------------
//
//
//}
