package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class AnnouncementService {

    // Managed Repository -----------------------------------------------------

    @Autowired
    private AnnouncementService	applicationRepository;



    // Simple CRUD methods ----------------------------------------------------

    public Application create(final Explorer explorer) {
         announcement;

        announcement = new Application();
        announcement.setMoment(new Date(System.currentTimeMillis() - 1000));
        announcement.setStatus(Status.PENDING);
        announcement.setApplicant(explorer);

        return announcement;
    }

    public Application findOne(final int applicationId) {
        Application application = null;

        application = this.applicationRepository.findOne(applicationId);
        Assert.isTrue(this.checkByPrincipal(application) || this.checkByPrincipalExplorer(application));

        return application;
    }
    public Application save(final Application application) {
        Assert.notNull(application);
        Assert.notNull(application.getTrip());
        Assert.isTrue(application.getTrip().getStartDateTrip().after(new Date(System.currentTimeMillis() - 1000)));

        if (application.getId() == 0)
            Assert.isTrue(!this.tripService.hasThisTripAnyApplicationFromThisExplorer(application.getTrip().getId(), application.getApplicant().getId()));
        else if (application.getStatus().equals(Status.DUE) && application.getCreditCard() != null) {
            Assert.notNull(application.getCreditCard().getHolder());
            Assert.notNull(application.getCreditCard().getBrand());
            Assert.notNull(application.getCreditCard().getNumber());
            Assert.notNull(application.getCreditCard().getExpirationMonth());
            Assert.notNull(application.getCreditCard().getExpirationYear());
            Assert.notNull(application.getCreditCard().getCvv());
            application.setStatus(Status.ACCEPTED);
        } else if (application.getStatus().equals(Status.PENDING))
            Assert.isNull(application.getRejectReason());

        if(application.getId()!=0) {

            final Application old = this.findOne(application.getId());

		/*	if (old != null && old.getStatus() != application.getStatus()) {
				final Message message = this.messageService.create();
				message.setSubject("Application status changed from " + old.getStatus() + " to " + application.getStatus());
				message.setBody("Trip: " + application.getTrip().getTicker() + ". Applicant: " + application.getApplicant().getName());
				message.setPriority(Priority.HIGH);
				final Admin sender = this.adminService.findAll().iterator().next();
				message.setSender(sender);
				message.setRecipient(application.getApplicant());
				message.setFolder(this.folderService.findByFolderName(sender.getUserAccount().getId(), "Out Box"));
				final Message copyMessage = this.messageService.copy(message);
				copyMessage.setRecipient(application.getTrip().getManager());
				this.messageService.save(message);
				this.messageService.save(copyMessage);
			}*/
        }

        final Application saved = this.applicationRepository.save(application);
        return saved;
    }
    public Collection<Application> findAll() {
        return this.applicationRepository.findAll();
    }

    public void delete(final Application application) {
        this.applicationRepository.delete(application);
    }

    public void deleteApplications(final Trip trip) {
        final Collection<Application> applications = trip.getApplications();
        this.applicationRepository.delete(applications);
    }

    // Other business methods -------------------------------------------------

    public Application dueApplication(final Application application) {

        Assert.notNull(application);
        Assert.isTrue(application.getStatus().equals(Status.PENDING));
        this.checkByPrincipal(application);

        Application saved = null;

        application.setStatus(Status.DUE);
        saved = this.applicationRepository.save(application);
        this.messageService.notifyChangeInApplicationStatus(saved);

        return saved;
    }

    public Application cancelApplication(final Application application) {

        Assert.notNull(application);
        Assert.isTrue(application.getStatus().equals(Status.ACCEPTED));
        Assert.isTrue(application.getTrip().getStartDateTrip().after(new Date(System.currentTimeMillis() - 1000)));   //Requisito 13.4

        final Date currentDate = new Date(System.currentTimeMillis() - 10000);

        Assert.isTrue(currentDate.before(application.getTrip().getStartDateTrip()));
        this.checkByPrincipalExplorer(application);

        Application saved = null;

        application.setStatus(Status.CANCELLED);
        saved = this.save(application);
        this.messageService.notifyChangeInApplicationStatus(saved);
        return saved;
    }

    public Application acceptApplication(final Application application, final CreditCard creditCard) {

        this.checkByPrincipalExplorer(application);

        Assert.notNull(application);
        Assert.isTrue(application.getStatus().equals(Status.DUE));
        Assert.notNull(creditCard);

        Assert.notNull(creditCard.getHolder());
        Assert.notNull(creditCard.getBrand());
        Assert.notNull(creditCard.getExpirationMonth());
        Assert.notNull(creditCard.getExpirationYear());
        Assert.notNull(creditCard.getCvv());

        Application saved = null;

        application.setCreditCard(creditCard);
        application.setStatus(Status.ACCEPTED);
        saved = this.applicationRepository.save(application);
        this.messageService.notifyChangeInApplicationStatus(saved);
        return saved;
    }

    public Application rejectApplication(final Application application, final String rejectReason) {

        Assert.notNull(application);
        Assert.notNull(rejectReason);
        Assert.isTrue(!rejectReason.isEmpty());
        Assert.isTrue(application.getStatus().equals(Status.PENDING));

        this.checkByPrincipal(application);
        Application saved = null;

        application.setStatus(Status.REJECTED);
        application.setRejectReason(rejectReason);
        saved = this.applicationRepository.save(application);

        this.messageService.notifyChangeInApplicationStatus(saved);

        return application;
    }

    public Collection<Application> findApplicationsByExplorerId(final int explorerId) {
        return this.applicationRepository.findApplicationByExplorer(explorerId);
    }

    public Collection<Application> findApplicationsByManagerId(final int managerId) {
        return this.applicationRepository.findApplicationByManager(managerId);
    }

    public Double findRatioOfAcceptedApplications() {
        return this.applicationRepository.acceptedApplications();
    }

    public Double findRatioOfCancellededApplications() {
        return this.applicationRepository.cancelledApplications();
    }

    public Double findRatioOfDueApplications() {
        return this.applicationRepository.dueApplications();
    }

    public Double findRatioOfPendingApplications() {
        return this.applicationRepository.pendingApplications();
    }

    public Collection<Application> findApplicationsByStatus(final Status status) {
        return this.applicationRepository.findApplicationsByStatus(status);
    }

    private boolean checkByPrincipal(final Application application) {
        Boolean res = false;

        Manager manager = null;

        manager = this.managerService.findByPrincipal();
        if (application.getTrip().getManager().equals(manager))
            res = true;
        return res;

    }

    private boolean checkByPrincipalExplorer(final Application application) {
        Boolean res = false;

        Explorer explorer = null;

        explorer = this.explorerService.findByPrincipal();
        if (application.getApplicant().equals(explorer))
            res = true;
        return res;

    }

}
