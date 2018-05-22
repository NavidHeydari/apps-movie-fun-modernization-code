package org.superbiz.moviefun;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.superbiz.moviefun.albumapi.AlbumFixtures;
import org.superbiz.moviefun.albumapi.AlbumInfo;
import org.superbiz.moviefun.albumapi.AlbumsClient;
import org.superbiz.moviefun.movieapi.MovieFixtures;
import org.superbiz.moviefun.movieapi.MovieInfo;
import org.superbiz.moviefun.movieapi.MoviesClient;

import java.util.Map;


@Controller
public class HomeController {

    private final MoviesClient moviesBean;
    private final AlbumsClient albumsBean;
    private final MovieFixtures movieFixtures;
    private final AlbumFixtures albumFixtures;

    public HomeController(MoviesClient moviesBean, AlbumsClient albumsBean,
                          MovieFixtures movieFixtures, AlbumFixtures albumFixtures) {
        this.moviesBean = moviesBean;
        this.albumsBean = albumsBean;
        this.movieFixtures = movieFixtures;
        this.albumFixtures = albumFixtures;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }



    @GetMapping("/setup")
    public String setup(Map<String, Object> model) {
        for (MovieInfo movie : movieFixtures.load()) {
            moviesBean.addMovie(movie);
        }

        for (AlbumInfo album : albumFixtures.load()) {
            albumsBean.addAlbum(album);
        }

        model.put("movies", moviesBean.getMovies());
        model.put("albums", albumsBean.getAlbums());

        System.out.println("HOMECONTROLLER - 4");

        return "setup";
    }
}
