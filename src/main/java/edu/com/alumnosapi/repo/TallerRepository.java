package edu.com.alumnosapi.repo;

import edu.com.alumnosapi.model.Taller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TallerRepository extends JpaRepository<Taller, Integer> { Integer id(Integer id);

    //
    List<Taller> findByNombre(String nombre);
    List<Taller> findByNombreLike(String nombre);
}


