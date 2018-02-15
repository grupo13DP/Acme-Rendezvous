package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.RendezvousRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class RendezvousService  {

    // Managed repository -----------------------------------------------------

    @Autowired
    private RendezvousRepository rendezvousRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private UserService userService;

    // Constructors -----------------------------------------------------------

    public RendezvousService() {
        super();
    }

    // Simple CRUD methods ----------------------------------------------------

    public Rendezvous create(){
        Rendezvous res=null;
        User creator=null;
        Collection<Comment> comments =null;
        Collection<Question> questions=null;
        Collection<Announcement> announcements=null;
        Collection<Participate> participated=null;
        Collection<Rendezvous> associated=null;

        creator= userService.findByPrincipal();
        res=new Rendezvous();
        res.setMoment(new Date(System.currentTimeMillis()-1000));
        creator.getRendezvouses().add(res);
        res.setCreator(creator);
        res.setAnnouncements(announcements);
        res.setAssociated(associated);
        res.setComments(comments);
        res.setParticipated(participated);
        res.setQuestions(questions);


        return res;
    }

    public Rendezvous save(Rendezvous rendezvous){
        Rendezvous res= null;
        Assert.isTrue(checkByPrincipal(rendezvous));
        Assert.notNull(rendezvous);
        if(rendezvous.getId()==0){
            res.getCreator().getRendezvouses().add(rendezvous);
            res=rendezvousRepository.save(rendezvous);

        }else{
            res=rendezvousRepository.save(rendezvous);
        }

        return res;
    }


    public Collection<Rendezvous> findAll() {

        Collection<Rendezvous> res = null;
        res = this.rendezvousRepository.findAll();
        return res;
    }

    public Rendezvous findOne(final int rendezvousId) {

        Rendezvous res = null;
        res = this.rendezvousRepository.findOne(rendezvousId);
        return res;
    }



    public Rendezvous delete(final Rendezvous rendezvous) {
        Rendezvous res=null;
        Assert.notNull(rendezvous);
        Assert.isTrue(this.checkByPrincipal(rendezvous));
        Assert.isTrue(!rendezvous.getFinalMode());
        rendezvous.setFinalMode(true);
        return res= rendezvousRepository.save(rendezvous);



    }

    // Other business methods -------------------------------------------------

    public Rendezvous findOneToEdit(final int rendezvousId) {
        Rendezvous res = this.rendezvousRepository.findOne(rendezvousId);
        Assert.isTrue(checkByPrincipal(res));
        Assert.isTrue(!res.getFinalMode());
        return res;
    }

    public boolean checkByPrincipal(final Rendezvous rendezvous) {
        Boolean res = null;
        User principal = null;

        res = false;
        principal = this.userService.findByPrincipal();

        if (rendezvous.getCreator().equals(principal))
            res = true;

        return res;
    }




}
