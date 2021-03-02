package zad3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


class ProgLang<T, V> {

    private Map<T, V> jezpro;
    private Map<String, Set<String>> projez;
    private Map<String, Programmer> programmersMap;

    ProgLang(String nazwaPliku) {
        jezpro = new LinkedHashMap<>();
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(new File(nazwaPliku)));
            while ((line = br.readLine()) != null) {
                String[] helper = line.split("\\t");
                List<String> list = new ArrayList<>();
                for (int i = 1; i < helper.length; i++) {
                    list.add(helper[i]);
                }
                list = list.stream().distinct().collect(Collectors.toList());

                jezpro.put((T) helper[0], (V) list);
            }
        } catch (IOException ex) {
            System.err.print("Blad przy wczytywaniu pliku o nazwie " + nazwaPliku + ": " + ex);
        }
    }

    <T, V> Map<T, V> getLangsMap() {
        return (Map<T, V>) jezpro;
    }
    Map<String, Set<String>> getProgsMap() {
        projez = new LinkedHashMap<>();
        programmersMap = new LinkedHashMap<>();

        for (T key : jezpro.keySet())
            for (V v : (List<V>) jezpro.get(key)) {
                if (projez.containsKey(v)) {
                    projez.get(v).add((String) key);
                } else {
                    Set<String> stringSet = new LinkedHashSet<>();
                    stringSet.add((String) key);
                    projez.put((String) v, stringSet);
                    programmersMap.put((String) v, new Programmer((String) v, stringSet));
                }
            }
        return projez;
    }

    Map<T, V> getLangsMapSortedByNumOfProgs() {
        LinkedHashMap getLangsProgs = jezpro.entrySet().stream().sorted((o1, o2) -> {
            List<String> listo1 = (List<String>) o1.getValue();
            List<String> listo2 = (List<String>) o2.getValue();
            Integer val1 = listo1.size();
            Integer val2 = listo2.size();
            return val2.compareTo(val1);
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (o1, o2) -> o1, LinkedHashMap::new));
        return getLangsProgs;
    }

    public Map<T, V> getProgsMapSortedByNumOfLangs() {
        LinkedHashMap getProgsLangs = programmersMap.entrySet().stream()
                .sorted(((o1, o2) ->
                        o2.getValue().getJezSize().compareTo(o1.getValue().getJezSize()))
                ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> o1,
                        LinkedHashMap::new));

        return getProgsLangs;
    }

    public Map<T,V> getProgsMapForNumOfLangsGreaterThan(int i) {
        Map<T, V> mapGreat = (LinkedHashMap) programmersMap.entrySet()
                .stream().filter(o -> o.getValue().getJezSize() > i)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2)
                        -> o1, LinkedHashMap::new));
        return mapGreat;
    }

        public Map<T, V> sorted(Map<T, V> unsorted, Comparator<Map.Entry<T, V>> comperator) {
        Map<T, V> mapSorted = unsorted.entrySet().stream().sorted(comperator)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2)
                        -> o2, LinkedHashMap::new));
        return mapSorted;
    }

        public Map<T, V> filtered(Map<T, V> mapfilt, Predicate<Map.Entry<T, V>> pred) {
        Map<T, V> mapFiltered = mapfilt.entrySet().stream().filter(pred)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) ->
                        e1, LinkedHashMap::new));
        return mapFiltered;
    }
}