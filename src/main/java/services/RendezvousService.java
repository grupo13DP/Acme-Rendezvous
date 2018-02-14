package services;

import domain.Rendezvous;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.RendezvousRepository;

import javax.transaction.Transactional;
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
        Rendezvous rendezvous=null;
        User creator=null;

        creator= userService.findByPrincipal();
        rendezvous=new Rendezvous();
        rendezvous.setMoment(new Date(System.currentTimeMillis()-1000));
        creator.getRendezvouses().add(rendezvous);
        rendezvous.setCreator(creator);

        return rendezvous;
    }

    // Other business methods -------------------------------------------------




}
