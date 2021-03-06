package services;

import domain.Admin;
import domain.Announcement;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.AnnouncementRepository;

import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class AnnouncementService {

    // Managed Repository -----------------------------------------------------

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;


    // Simple CRUD methods ----------------------------------------------------

    public Announcement create() {

        Announcement announcement;

        announcement = new Announcement();
        announcement.setMoment(new Date(System.currentTimeMillis() - 1000));

        return announcement;
    }


    public Announcement save(final Announcement announcement) {
        Assert.notNull(announcement);

        checkByPrincipal(announcement);


        final Announcement saved = this.announcementRepository.save(announcement);
        return saved;
    }

    public Announcement findOne(final int announcementId) {
        Announcement announcement = null;

        announcement = this.announcementRepository.findOne(announcementId);

        return announcement;
    }

    public Collection<Announcement> findAll() {
        return this.announcementRepository.findAll();
    }

    public void delete(final Announcement announcement) {
        checkByPrincipalAdministrator();
        this.announcementRepository.delete(announcement);
    }

    // Other business methods -------------------------------------------------

    public Announcement findOneToEdit(final int announcementId) {
        Announcement announcement = null;

        announcement = this.announcementRepository.findOne(announcementId);
        this.checkByPrincipal(announcement);

        return announcement;
    }

    private void checkByPrincipal(final Announcement announcement) {

        User user = null;
        user = this.userService.findByPrincipal();
        Assert.isTrue(announcement.getRendezvous().getCreator().equals(user));

    }

    private void checkByPrincipalAdministrator() {

        Admin admin = null;
        admin = this.adminService.findByPrincipal();
        Assert.isTrue(admin.getUserAccount().getAuthorities().equals("ADMIN"));

    }

    public Collection<Announcement> announcementFindByParticipated(int userId){
        Collection<Announcement> result;
        result = this.announcementRepository.announcementFindByParticipated(userId);
        return result;
    }

    public Collection<Double> avgDevAnnouncementsPerRendezvous(){
        Collection<Double> result;
        result = this.announcementRepository.avgDevAnnouncementsPerRendezvous();
        return result;
    }
}

