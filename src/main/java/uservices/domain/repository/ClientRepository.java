package uservices.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uservices.domain.Entity.Client;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

    public List<Client> findByEmail(String email);

    public default Client findClientByEmail(String email){
        ArrayList<Client> list = (ArrayList<Client>) findAll();
        int i = 0;
        while(list.get(i).getEmail() != email){
            i++;
            if(i == list.toArray().length){
                return null;
            }
        }
        return list.get(i);

    }
}
