package lab;

import java.util.ArrayList;
import java.util.List;

public class Lab5 implements LabInterface {
    @Override
    public void execute() {
        // Створення композицій
        MusicTrack track1 = new MusicTrack.RockTrack("Bohemian Rhapsody", 354);
        MusicTrack track2 = new MusicTrack.PopTrack("Shape of You", 233);
        MusicTrack track3 = new MusicTrack.JazzTrack("So What", 320);
        MusicTrack track4 = new MusicTrack.RockTrack("Stairway to Heaven", 482);

        // Створення альбому з використанням списку
        List<MusicTrack> tracks = new ArrayList<>();
        tracks.add(track1);
        tracks.add(track2);
        tracks.add(track3);
        tracks.add(track4);

        MusicTrack.Album album = new MusicTrack.Album(tracks);

        // Виведення тривалості альбому
        System.out.println("Загальна тривалість альбому: " + album.calculateTotalDuration() + " сек.");

        // Перестановка композицій за стилем
        System.out.println("\nКомпозиції після сортування за стилем:");
        album.sortTracksByGenre();
        album.displayTracks();

        // Пошук композицій за діапазоном тривалості
        System.out.println("\nКомпозиції з тривалістю між 230 і 360 сек:");
        List<MusicTrack> foundTracks = album.findTracksByDuration(230, 360);
        foundTracks.forEach(System.out::println);

        // Запис альбому на диск
        album.writeToDisk();
    }

    @Override
    public void printVariant() {
        System.out.println("Номер заліковки: " + scoreBookNumber);
        System.out.println("C13 = " + scoreBookNumber % 13);
    }

}
