package repositories;


import domain.Rendezvous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous,Integer> {

}
