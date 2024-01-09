package com.pupygreen.desafiobossabox.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Tools controller test.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(ToolsTestConfiguration.class)
@DisplayName("Testando a classe ToolsController")
public class ToolsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  /**
   * Deve retornar status OK.
   *
   * @throws Exception the exception
   */
  @Test
  @DisplayName("Testando se o status da resposta é OK!")
  void deveRetonarStatusOk() throws Exception {
    mockMvc.perform(get("/tools"))
        .andExpect(status().isOk());
  }

  /**
   * Deve retonar status ok e todas as ferramentas.
   *
   * @throws Exception the exception
   */
  @Test
  @DisplayName("Testando se o status da resposta é OK, e um array de ferramentas!")
  void deveRetonarStatusOkETodasAsFerramentas() throws Exception {
    mockMvc.perform(get("/tools"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray());
  }

  /**
   * Deve retornar primeiro elemento com informacoes corretas.
   *
   * @throws Exception the exception
   */
  @Test
  @DisplayName("Testando as informações do primeiro elemento do array")
  void deveRetornarPrimeiroElementoComInformacoesCorretas() throws Exception {
    mockMvc.perform(get("/tools"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].title").value("chave de fenda"))
        .andExpect(jsonPath("$[0].link").value("http:3000"))
        .andExpect(jsonPath("$[0].description").value("Muito boa essa chave"))
        .andExpect(jsonPath("$[0].tags").isArray())
        .andExpect(jsonPath("$[0].tags[0]").value("novas"))
        .andExpect(jsonPath("$[0].tags[1]").value("bom preço"));
  }

  /**
   * Deve retornar a segunda ferramenta atravez do 'id'.
   *
   * @throws Exception the exception
   */
  @Test
  @DisplayName("Testando as informações fazendo um get para um id específico")
  void deveRetornarASegundaFerramentaAtravesDoId() throws Exception {
    mockMvc.perform(get("/tools/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(2))
        .andExpect(jsonPath("$.title").value("chave de roda"))
        .andExpect(jsonPath("$.link").value("http:4000"))
        .andExpect(jsonPath("$.description").value("Muito boa essa chave de roda, é da grande"))
        .andExpect(jsonPath("$.tags").isArray())
        .andExpect(jsonPath("$.tags[0]").value("usada"))
        .andExpect(jsonPath("$.tags[1]").value("cara de mais"));
  }

  @Test
  @DisplayName("Testando as informações fazendo um get através de uma tag")
  void deveRetornarFerramentaPelaTag() throws Exception {
    mockMvc.perform(get("/tools/findByTag?tags=qualidade"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(152))
        .andExpect(jsonPath("$[0].title").value("Rodas para skate profissional"))
        .andExpect(jsonPath("$[0].link").value("http:19002"))
        .andExpect(jsonPath("$[0].description").value("Muito boa essas rodas de skate"))
        .andExpect(jsonPath("$[0].tags").isArray())
        .andExpect(jsonPath("$[0].tags[0]").value("rodas novas"))
        .andExpect(jsonPath("$[0].tags[1]").value("bom preço"))
        .andExpect(jsonPath("$[0].tags[2]").value("Ótima qualidade"));
  }
}
