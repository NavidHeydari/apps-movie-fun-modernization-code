package org.superbiz.moviefun.albums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {

    private AlbumsRepository albumBean;

    public AlbumsController(AlbumsRepository albumBean){
        this.albumBean = albumBean;
    }


    @PostMapping("")
    public void addAlbum(@RequestBody Album album) {
        albumBean.addAlbum(album);
    }

    @GetMapping("/{id}")
    public Album getById(@PathVariable("id") Long id) {
        return albumBean.find(id);
    }

    @GetMapping("")
    public List<Album> getAll() {
        return albumBean.getAlbums();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        albumBean.deleteAlbum(id);
    }

    @PutMapping("/{id}")
    public void updateAlbum(@PathVariable("id") Long id, @RequestBody Album album) {
        album.setId(id);
        albumBean.updateAlbum(album);
    }
}
