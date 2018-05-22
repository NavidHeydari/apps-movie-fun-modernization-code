package org.superbiz.moviefun.albumapi; /**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;


public class AlbumsClient {

    private String albumUrl;
    private RestOperations restOperations;

    private static ParameterizedTypeReference<List<AlbumInfo>> albumListType = new ParameterizedTypeReference<List<AlbumInfo>>() {
    };

    public AlbumsClient(String albumUrl, RestOperations restOperations) {
        this.albumUrl = albumUrl;
        this.restOperations = restOperations;
    }


    public void addAlbum(AlbumInfo album) {
        restOperations.postForEntity(albumUrl, album, AlbumInfo.class);
    }

    public AlbumInfo find(long id) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(albumUrl + "/" + id);

        return restOperations.exchange(builder.toUriString(), GET, null, AlbumInfo.class).getBody();
    }

    public List<AlbumInfo> getAlbums() {
        return restOperations.exchange(albumUrl, GET, null, albumListType).getBody();
    }

    public void deleteAlbum(AlbumInfo album) {
        restOperations.delete(albumUrl + "/" + album.getId());
    }


    public void updateAlbum(AlbumInfo album) {
        restOperations.put(albumUrl + "/" + album.getId(), album);

    }
}
