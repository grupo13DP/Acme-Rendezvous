package repositories;


import domain.Rendezvous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous,Integer> {
    @Query("select avg(u.rendezvouses.size),sqrt(sum(u.rendezvouses.size *u.rendezvouses.size)/ count(u) - (avg(u.rendezvouses.size) *avg(u.rendezvouses.size))) from  User u")
    Collection<Double> avgDevRendezvousesPerUser();

    //@Query("select concat ( 100 * ( select count(u) from User u where u.rendezvouses is not empty )/ count(u.rendezvouses is empty) , '%') from User u")
    //public String ratioOfTripsWithAnyAuditRecord();

    //Collection<Double> avgDevUserPerRendezvous();

    //@Query("select avg(r.participated.size),sqrt(sum(r.participated.size *r.participated.size)/ count(r) - (avg(r.participated.size) *avg(r.participated.size))) from  Rendezvous r")
    //Collection<Double> avgDevRendezvousParticipatePerUser();


}
