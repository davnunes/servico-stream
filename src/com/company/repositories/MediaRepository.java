package com.company.repositories;

import com.company.domain.Actor;
import com.company.domain.Media;

import java.util.*;

public class MediaRepository {

    private List<Media> mediaList = new ArrayList<>();

    public MediaRepository() {
        populateBase();
    }

    public void addMedia(Media media) {
        mediaList.add(media);
        System.out.println("Media added");
    }

    public Media findMediaById(int id) {
        for (Media media : mediaList) {
            if (media.getId() == id) {
                return media;
            }
        }

        System.out.println("No media found");
        return null;
    }

    public List<Media> findMediaByTitle(String title) {
        List<Media> result = new ArrayList<>();
        for (Media media : mediaList) {
            String mediaTitle = media.getTitle().toLowerCase();
            if (mediaTitle.contains(title.toLowerCase())) {
                result.add(media);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No media found");
            return new ArrayList<>();
        }

        return result;
    }

    public List<Media> findMediaByYear(int year) {
        List<Media> result = new ArrayList<>();
        for (Media media : mediaList) {
            if (media.getYear() == year) {
                result.add(media);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No media found");
            return new ArrayList<>();
        }

        return result;
    }

    public List<Media> findMediaByGenre(String genre) {
        List<Media> result = new ArrayList<>();
        for (Media media : mediaList) {
            String genreName = media.getGenre().toLowerCase();
            if (genreName.contains(genre.toLowerCase())) {
                result.add(media);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No media found");
            return new ArrayList<>();
        }

        return result;
    }

    public List<Media> findMediaByDirector(String director) {
        List<Media> result = new ArrayList<>();
        for (Media media : mediaList) {
            String mediaDirector = media.getDirector().toLowerCase();
            if (mediaDirector.contains(director.toLowerCase())) {
                result.add(media);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No media found");
            return new ArrayList<>();
        }

        return result;
    }

    public List<Media> findMediaByActor(String actor) {
        List<Media> result = new ArrayList<>();
        for (Media media : mediaList) {
            for (String actorName : media.getActorList()) {
                String mediaActor = actorName.toLowerCase();
                if (mediaActor.contains(actor.toLowerCase())) {
                    result.add(media);
                }
            }
        }

        if (result.isEmpty()) {
            System.out.println("No media found");
            return new ArrayList<>();
        }

        return result;
    }

    public boolean updateMedia(Media media, int id) {
        Media mediaToUpdate = findMediaById(id);
        if (mediaToUpdate != null) {
            if (isValidString(media.getTitle())) {
                mediaToUpdate.setTitle(media.getTitle());
            }
            if (isValidString(media.getSubtitle())) {
                mediaToUpdate.setSubtitle(media.getSubtitle());
            }
            // TODO: Add Director, Genre, Actor
            mediaToUpdate.setYear(media.getYear());
            return true;
        }
        return false;
    }

    public boolean deleteMedia(int id) {
        Media mediaToDelete = findMediaById(id);
        if (mediaToDelete != null) {
            mediaList.remove(mediaToDelete);
            System.out.println("Media deleted");
            return true;
        }
        return false;
    }

    private boolean isValidString(String value) {
        return value != null && !value.isBlank();
    }

    public void populateBase() {
        Media media1 = new Media("Stranger Things", null, "The Duffer Brothers",
            "Suspense", new String[]{"Millie", "Noah", "Gaten", "Finn"}, 2017, "Série");

        Media media2 = new Media("Piratas do Caribe", null, "Disney",
            "Aventura", new String[]{"Jonny Depp"}, 2006, "Filme");

        Media media3 = new Media("Dark", null, "Baran bo Odar",
            "Suspense", new String[]{"Louis Hoffman", "Lisa Vicari", "Oliver Masucci"}, 2017, "Série");

        mediaList.add(media1);
        mediaList.add(media2);
        mediaList.add(media3);
    }
}
