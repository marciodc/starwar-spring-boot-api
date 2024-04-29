package br.com.marciodc.starwars.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDTO {
    private int episodeId;
    private String openingCrawl;
}
