package com.etnetera.hr.repository;

import com.etnetera.hr.data.JavaScriptFramework;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Spring data repository interface used for accessing the data in database.
 *
 * @author Etnetera
 */
public interface JavaScriptFrameworkRepository extends CrudRepository<JavaScriptFramework, Long> {


    default JavaScriptFramework mustFindById(Long id) throws EntityNotFoundException {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Entity %s does not exists!", id)));
    }

    @Query("SELECT framework FROM JavaScriptFramework framework WHERE framework.name like %:expression% ")
    List<JavaScriptFramework> findByName(@Param("expression") String expression);
}
