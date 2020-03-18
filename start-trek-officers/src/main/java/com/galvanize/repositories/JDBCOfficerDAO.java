package com.galvanize.repositories;


import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public class JDBCOfficerDAO {

// @Autowired
   JdbcTemplate jdbcTemplate;

    public JDBCOfficerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long count() {
        return jdbcTemplate.queryForObject("select count(*) from officers", Long.class);
    }

    public boolean exists(long id) {
        boolean found = false;
        try {
             found = jdbcTemplate.queryForObject("select 1 from officers where id = ?", Boolean.class, id);
        }catch(EmptyResultDataAccessException e){
            //Ignore
        }

        return found;
    }

    public List<Officer> findAll() {
        return jdbcTemplate.query( "select * from officers",
                (rs, rowNum) -> new Officer(rs.getLong(  "id"),
                Rank.valueOf(rs.getString(  "officer_rank")),
                rs.getString(  "first_name"),
                rs.getString(  "last_name")) );
      }

      public Optional<Officer> findById(long id) {
        if(exists(id)) return Optional.empty();
          return Optional.ofNullable(jdbcTemplate.queryForObject("select * from officers where id=?",
                  ((rs, rowNum) -> new Officer(rs.getLong(  "id"),
                          Rank.valueOf(rs.getString(  "officer_rank")),
                          rs.getString(  "first_name"),
                          rs.getString(  "last_name"))),id)
          );
      }
}

