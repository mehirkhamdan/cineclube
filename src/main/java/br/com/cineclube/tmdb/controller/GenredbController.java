package br.com.cineclube.tmdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cineclube.tmdb.model.GenreTMDB;
import br.com.cineclube.tmdb.service.GenredbService;

@Controller
@RequestMapping("/genres")
public class GenredbController {
	
	@Autowired
	GenredbService apiGenreService;
	
	@GetMapping("/list")
	public String list(Model model)	{
		List<GenreTMDB> genreList =
				apiGenreService.searchGenresAll().getGenres().size() != 0 ?
						apiGenreService.searchGenresAll().getGenres() : null;
		
		model.addAttribute("genreList", genreList);
		return "genre/genreList";
	}

}
