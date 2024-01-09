package com.pupygreen.desafiobossabox.dto;

import java.util.List;

/**
 * The type Tools response.
 */
public record ToolsResponse( Long id, String title, String link, String description, List<String> tags) { }
