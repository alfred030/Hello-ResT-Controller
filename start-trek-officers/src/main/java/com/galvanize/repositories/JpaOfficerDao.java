package com.galvanize.repositories;


import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaOfficerDao extends JpaRepository<Officer, Long> {
        List<Officer> findAllByRank(Rank rank);
        static <First, Last> void findByLastAndFirst(Last, First) {
        }
}

