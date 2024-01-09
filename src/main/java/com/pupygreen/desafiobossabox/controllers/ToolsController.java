package com.pupygreen.desafiobossabox.controllers;

import com.pupygreen.desafiobossabox.dto.ToolsRequest;
import com.pupygreen.desafiobossabox.dto.ToolsResponse;
import com.pupygreen.desafiobossabox.entity.Tools;
import com.pupygreen.desafiobossabox.repository.ToolsRepository;
import com.pupygreen.desafiobossabox.services.ToolsService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * The type Tools controller.
 */
@RestController
@RequestMapping("/tools")
public class ToolsController {

    @Autowired
    private ToolsService toolsService;

  /**
   * Find all response entity.
   *
   * @return the response entity
   */
  @GetMapping
    public ResponseEntity<List<Tools>> findAll() {
        List<Tools> allTools = toolsService.getAllTools();

        return ResponseEntity.ok().body(allTools);
    }

  /**
   * Find tools by tag response entity.
   *
   * @param tags the tags
   * @return the response entity
   */
  @GetMapping("/findByTag")
   public ResponseEntity<?> findToolsByTag(@RequestParam String tags) {
       List<Tools> toolsList = toolsService.findTollByTag(tags);

       if (toolsList.isEmpty()) {
           return ResponseEntity.noContent()
               .header(
               "X-Custom-Message", "Nenhuma ferramenta encontrada com a tag: "
               + tags)
               .build();
       }

       return ResponseEntity.ok(toolsList);
   }

  /**
   * Gets tool id.
   *
   * @param id the id
   * @return the tool id
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getToolId(@PathVariable Long id) {
      Optional<Tools> toolsOptional = toolsService.findToolById(id);

      if (toolsOptional.isEmpty()) {
        return ResponseEntity.notFound()
            .header("X-Custon-Message", "Ferramenta não encontrada com o ID: "
            + id)
            .build();
      }

    Tools tools = toolsOptional.get();

    return ResponseEntity.ok(new ToolsResponse(
        tools.getId(),
        tools.getTitle(),
        tools.getLink(),
        tools.getDescription(),
        tools.getTags()
    ));
  }

  /**
   * Save tools response entity.
   *
   * @param toolsRequest the tools request
   * @return the response entity
   */
  @PostMapping
    public ResponseEntity<ToolsResponse> saveTools(@RequestBody ToolsRequest toolsRequest) {
        Tools toolsToBeSaved = toolsRequest.toModel();
        ToolsResponse toolsResponse = toolsService.saveTools(toolsToBeSaved);

        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(toolsResponse.id())
                .toUri();

        return ResponseEntity.created(headerLocation).body(toolsResponse);
    }

  /**
   * Delete tools response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping
  public ResponseEntity<String> deleteTools(@RequestParam Long id) {
    if (toolsService.findToolById(id).isEmpty()) {
      return ResponseEntity.notFound()
          .header("X-Custom-Message", "Ferramenta não encontrada com o ID: "
          + id)
          .build();
    }

    toolsService.deleteToolsById(id);

    return ResponseEntity.ok().body("Ferramenta deletada com sucesso!");
  }
}
