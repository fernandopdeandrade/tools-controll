package com.pupygreen.desafiobossabox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The type Tools.
 */
@Entity
@Getter
@Setter
public class Tools {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private String link;
    private String description;
    private List<String> tags;

    /**
     * Instantiates a new Tools.
     *
     * @param id          the id
     * @param title       the title
     * @param link        the link
     * @param description the description
     * @param tags        the tags
     */
    public Tools(Long id, String title, String link, String description, List<String> tags) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.tags = tags;
    }

    /**
     * Instantiates a new Tools.
     *
     * @param title       the title
     * @param link        the link
     * @param description the description
     * @param tags        the tags
     */
    public Tools(String title, String link, String description, List<String> tags) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.tags = tags;
    }

    /**
     * Instantiates a new Tools.
     */
    public Tools() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId () {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId (Long id) {
        this.id = id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle () {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle (String title) {
        this.title = title;
    }

    /**
     * Gets link.
     *
     * @return the link
     */
    public String getLink () {
        return link;
    }

    /**
     * Sets link.
     *
     * @param link the link
     */
    public void setLink (String link) {
        this.link = link;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription () {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription (String description) {
        this.description = description;
    }

    /**
     * Gets tags.
     *
     * @return the tags
     */
    public List<String> getTags () {
        return tags;
    }

    /**
     * Sets tags.
     *
     * @param tags the tags
     */
    public void setTags (List<String> tags) {
        this.tags = tags;
    }
}
