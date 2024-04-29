package br.com.marciodc.starwars.adapter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.marciodc.starwars.adapter.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Long>{
    Optional<MovieEntity> findByEpisodeId(int id);
}
