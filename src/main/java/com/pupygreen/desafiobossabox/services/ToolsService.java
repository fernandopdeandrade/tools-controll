package com.pupygreen.desafiobossabox.services;

import com.pupygreen.desafiobossabox.dto.ToolsResponse;
import com.pupygreen.desafiobossabox.entity.Tools;
import com.pupygreen.desafiobossabox.repository.ToolsRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Tools service.
 */
@Service
public class ToolsService {
    @Autowired
    private ToolsRepository toolsRepository;


  /**
   * Get all tools list.
   *
   * @return the list
   */
  @CircuitBreaker(name = "tools", fallbackMethod = "fallback")
  public List<Tools> getAllTools(){
      return toolsRepository.findAll();
    }
  private List<Tools> fallback(RuntimeException e) { return List.of(); }

  /**
   * Find toll by tag list.
   *
   * @param tags the tags
   * @return the list
   */
  public List<Tools> findTollByTag(String tags){
      return toolsRepository.buscar(tags);
    }

  /**
   * Find tool by id optional.
   *
   * @param id the id
   * @return the optional
   */
  public Optional<Tools> findToolById(Long id) {return toolsRepository.findById(id); }

  /**
   * Save tools tools response.
   *
   * @param tools the tools
   * @return the tools response
   */
  public ToolsResponse saveTools(Tools tools){

        Tools toolsSaved = toolsRepository.save(tools);

      return new ToolsResponse(
                toolsSaved.getId(),
                toolsSaved.getTitle(),
                toolsSaved.getLink(),
                toolsSaved.getDescription(),
                toolsSaved.getTags());
    }

  /**
   * Delete tools by id.
   *
   * @param id the id
   */
  public void deleteToolsById(Long id){
        toolsRepository.deleteById(id);
    }
}
