package com.pupygreen.desafiobossabox.repository;

import com.pupygreen.desafiobossabox.entity.Tools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * The interface Tools repository.
 */
public interface ToolsRepository extends JpaRepository<Tools, Long> {

    /**
     * Buscar list.
     *
     * @param tag the tag
     * @return the list
     */
    @Query(value = "select * from tools where tags like %:tag%", nativeQuery = true)
    List<Tools> buscar(@Param("tag")String tag);
}
