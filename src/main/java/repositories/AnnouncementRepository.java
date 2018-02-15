package repositories;


import domain.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

    @Query("select a from Announcement a join a.rendezvous.participated p where p.attendant.id=?1 order by a.moment desc")
    Collection<Announcement> announcementFindByParticipated(int userId);
}
