package org.netcracker.library.controller;

import org.netcracker.library.model.Album;
import org.netcracker.library.model.Library;
import org.netcracker.library.model.Singer;
import org.netcracker.library.model.Track;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCommand extends Command {

    protected SearchCommand(Library library, String key, String[] args) {
        super(library,null, args);
    }

    @Override
    public int execute() {
        return searchTrack(args[0]);
    }

    public int searchTrack(String searchString){

        Pattern p = Pattern.compile(searchString, Pattern.CASE_INSENSITIVE);

        Matcher m;

        int count = 0;

        for(Singer singer : library.getSingers().values()){
            for(Album album: singer.getAlbums().values()){
                for(Track track: album.getTracks().values()){
                    m = p.matcher(track.getName());
                    if(m.find()){
                        System.out.println(singer.getName() + " | " + album.getName() + " | " + track.getName());
                        ++count;
                    }
                }
            }
        }
        System.out.println("Number of matches: " + count);
        return 0;
    }
}
