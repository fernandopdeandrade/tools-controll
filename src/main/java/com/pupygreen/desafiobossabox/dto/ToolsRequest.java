package com.pupygreen.desafiobossabox.dto;

import com.pupygreen.desafiobossabox.entity.Tools;

import java.util.List;

/**
 * The type Tools request.
 */
public record ToolsRequest(String title, String link, String description, List<String> tags){

    /**
     * To model tools.
     *
     * @return the tools
     */
    public Tools toModel(){
        return new Tools(title,link,description,tags);
    }
}



