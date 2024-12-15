package lab;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Абстрактний клас для музичних композицій
public abstract class MusicTrack {
    protected String title;
    protected int duration;  // тривалість у секундах
    protected String genre;  // стиль музики

    public MusicTrack(String title, int duration, String genre) {
        this.title = title;
        this.duration = duration;
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return title + " (" + genre + ") - " + duration + " сек.";
    }


    // Клас для стилю рок
    static class RockTrack extends MusicTrack {
        public RockTrack(String title, int duration) {
            super(title, duration, "Rock");
        }
    }

    // Клас для стилю поп
    static class PopTrack extends MusicTrack {
        public PopTrack(String title, int duration) {
            super(title, duration, "Pop");
        }
    }

    // Клас для стилю джаз
    static class JazzTrack extends MusicTrack {
        public JazzTrack(String title, int duration) {
            super(title, duration, "Jazz");
        }
    }

    // Клас для альбому, який містить список композицій
    static class Album {
        private final List<MusicTrack> tracks;

        public Album(List<MusicTrack> tracks) {
            this.tracks = tracks;
        }

        // Порахувати загальну тривалість альбому
        public int calculateTotalDuration() {
            return tracks.stream()
                    .mapToInt(MusicTrack::getDuration)
                    .sum();
        }

        // Перестановка композицій на основі стилю
        public void sortTracksByGenre() {
            tracks.sort(Comparator.comparing(MusicTrack::getGenre));
        }

        // Пошук композицій за діапазоном тривалості
        public List<MusicTrack> findTracksByDuration(int minDuration, int maxDuration) {
            return tracks.stream()
                    .filter(track -> track.getDuration() >= minDuration && track.getDuration() <= maxDuration)
                    .collect(Collectors.toList());
        }

        // Функція для запису альбому на диск
        public void writeToDisk() {
            try {
                System.out.println("Альбом записано на диск!");
                tracks.forEach(System.out::println);
            } catch (Exception e) {
                System.out.println("Помилка при запису на диск: " + e.getMessage());
            }
        }

        // Вивести всі композиції
        public void displayTracks() {
            tracks.forEach(System.out::println);
        }
    }
}
