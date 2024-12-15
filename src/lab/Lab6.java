package lab;

import java.util.*;

public class Lab6 implements LabInterface {
    @Override
    public void execute() {
        // Створення треків
        MusicTrack track1 = new MusicTrack.RockTrack("Bohemian Rhapsody", 354);
        MusicTrack track2 = new MusicTrack.PopTrack("Shape of You", 233);
        MusicTrack track3 = new MusicTrack.JazzTrack("So What", 320);
        MusicTrack track4 = new MusicTrack.RockTrack("Stairway to Heaven", 482);

        System.out.println("=== Тестування порожнього конструктора ===");
        MusicTrackSet emptySet = new MusicTrackSet();
        System.out.println("Розмір порожньої колекції: " + emptySet.size());
        System.out.println("Чи порожня колекція? " + emptySet.isEmpty());

        System.out.println("\n=== Тестування конструктора з одним треком ===");
        MusicTrackSet singleSet = new MusicTrackSet(track1);
        System.out.println("Розмір колекції з одним треком: " + singleSet.size());
        System.out.println("Чи містить трек1? " + singleSet.contains(track1));

        System.out.println("\n=== Тестування конструктора з колекцією ===");
        List<MusicTrack> tracks = Arrays.asList(track1, track2, track3);
        MusicTrackSet collectionSet = new MusicTrackSet(tracks);
        System.out.println("Розмір колекції: " + collectionSet.size());
        System.out.println("Чи містить всі треки? " + collectionSet.containsAll(tracks));

        System.out.println("\n=== Тестування додавання та видалення ===");
        MusicTrackSet testSet = new MusicTrackSet();
        System.out.println("Додаємо track1: " + testSet.add(track1));
        System.out.println("Додаємо track2: " + testSet.add(track2));
        System.out.println("Спроба додати дублікат track4: " + testSet.add(track4));
        System.out.println("Поточний розмір: " + testSet.size());

        System.out.println("Видаляємо track1: " + testSet.remove(track1));
        System.out.println("Розмір після видалення: " + testSet.size());
        System.out.println("Спроба видалити неіснуючий трек: " + testSet.remove(track3));

        System.out.println("\n=== Тестування очищення колекції ===");
        System.out.println("Розмір до очищення: " + testSet.size());
        testSet.clear();
        System.out.println("Розмір після очищення: " + testSet.size());
        System.out.println("Чи порожня колекція? " + testSet.isEmpty());
    }

    @Override
    public void printVariant() {
        System.out.println("Номер залікової книжки " + scoreBookNumber);
        System.out.println("C2 " + scoreBookNumber % 2);
        System.out.println("C3 " + scoreBookNumber % 3);
    }

    /**
     * Клас Node представляє вузол однозв'язного списку
     */
    class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Реалізація інтерфейсу Set для колекції музичних треків
     * використовуючи структуру однозв'язного списку
     */
    public class MusicTrackSet implements Set<MusicTrack> {
        private Node<MusicTrack> head;
        private int size;

        /**
         * Конструктор за замовчуванням - створює порожню колекцію
         */
        public MusicTrackSet() {
            this.head = null;
            this.size = 0;
        }

        /**
         * Конструктор, що приймає один музичний трек
         * @param track початковий музичний трек
         */
        public MusicTrackSet(MusicTrack track) {
            this.head = new Node<>(track);
            this.size = 1;
        }

        /**
         * Конструктор, що приймає колекцію музичних треків
         * @param collection колекція треків для ініціалізації
         */
        public MusicTrackSet(Collection<MusicTrack> collection) {
            this();
            addAll(collection);
        }

        /**
         * Додає новий трек до колекції
         * @param track трек для додавання
         * @return true якщо трек успішно додано
         */
        @Override
        public boolean add(MusicTrack track) {
            if (contains(track)) {
                return false;
            }

            Node<MusicTrack> newNode = new Node<>(track);
            if (head == null) {
                head = newNode;
            } else {
                Node<MusicTrack> current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
            size++;
            return true;
        }

        /**
         * Перевіряє чи містить колекція вказаний трек
         * @param o об'єкт для перевірки
         * @return true якщо трек знайдено
         */
        @Override
        public boolean contains(Object o) {
            if (!(o instanceof MusicTrack)) {
                return false;
            }
            Node<MusicTrack> current = head;
            while (current != null) {
                if (current.data.equals(o)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }

        /**
         * Видаляє вказаний трек з колекції
         * @param o трек для видалення
         * @return true якщо трек успішно видалено
         */
        @Override
        public boolean remove(Object o) {
            if (head == null || !(o instanceof MusicTrack)) {
                return false;
            }

            if (head.data.equals(o)) {
                head = head.next;
                size--;
                return true;
            }

            Node<MusicTrack> current = head;
            Node<MusicTrack> prev = null;
            while (current != null && !current.data.equals(o)) {
                prev = current;
                current = current.next;
            }

            if (current != null) {
                prev.next = current.next;
                size--;
                return true;
            }
            return false;
        }

        /**
         * @return розмір колекції
         */
        @Override
        public int size() {
            return size;
        }

        /**
         * @return true якщо колекція порожня
         */
        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * Очищує колекцію
         */
        @Override
        public void clear() {
            head = null;
            size = 0;
        }

        /**
         * @return масив об'єктів колекції
         */
        @Override
        public Object[] toArray() {
            Object[] array = new Object[size];
            int index = 0;
            Node<MusicTrack> current = head;
            while (current != null) {
                array[index++] = current.data;
                current = current.next;
            }
            return array;
        }

        // Імплементація інших методів інтерфейсу Set
        @Override
        public Iterator<MusicTrack> iterator() {
            return new Iterator<MusicTrack>() {
                private Node<MusicTrack> current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }


                @Override
                public MusicTrack next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    MusicTrack data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            for (Object o : c) {
                if (!contains(o)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public boolean addAll(Collection<? extends MusicTrack> c) {
            boolean modified = false;
            for (MusicTrack track : c) {
                if (add(track)) {
                    modified = true;
                }
            }
            return modified;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            boolean modified = false;
            Node<MusicTrack> current = head;
            Node<MusicTrack> prev = null;
            while (current != null) {
                if (!c.contains(current.data)) {
                    if (prev == null) {
                        head = current.next;
                    } else {
                        prev.next = current.next;
                    }
                    size--;
                    modified = true;
                } else {
                    prev = current;
                }
                current = current.next;
            }
            return modified;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            boolean modified = false;
            for (Object o : c) {
                if (remove(o)) {
                    modified = true;
                }
            }
            return modified;
        }

        @Override
        public <T> T[] toArray(T[] a) {
            if (a.length < size) {
                a = Arrays.copyOf(a, size);
            }
            int i = 0;
            for (Node<MusicTrack> current = head; current != null; current = current.next) {
                a[i++] = (T) current.data;
            }
            if (a.length > size) {
                a[size] = null;
            }
            return a;
        }
    }

            }
