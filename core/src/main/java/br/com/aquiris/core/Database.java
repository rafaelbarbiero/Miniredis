package br.com.aquiris.core;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Database {

    private static final Map<String, String> data = new LinkedHashMap<>();
    private static final Map<String, HashMap<String, Integer>> dataZ = new HashMap<>();

    public static void set(final String key, final String value) {
        Database.data.put(key, value);
    }

    /**
     * This is a string operation because Redis does not have a dedicated integer type.
     *
     * @see @link{https://redis.io/commands/incr}
     */
    public static String incr(final String key) {
        final Integer value = Optional.ofNullable(data.get(key))
                .map(Integer::new)
                .map(val -> ++val)
                .orElse(1);
        Database.set(key, String.valueOf(value));
        return Database.get(key);
    }

    public static String get(final String key) {
        return Database.data.get(key);
    }

    public static String del(final String key) {
        return Database.data.remove(key);
    }

    public static Integer getDBSIZE() {
        return Database.data.size();
    }

    public static HashMap<String, Integer> zadd(final String key, final int score, final String value) {
        final HashMap<String, Integer> hashMap = Optional.ofNullable(Database.dataZ.get(key)).orElseGet(HashMap::new);
        hashMap.put(value, score);
        return Database.dataZ.put(key, hashMap);
    }

    public static int getZCARD(final String key) {
        return Database.dataZ.get(key).entrySet().size();
    }

    public static List<String> getZRANGE(final String key, final Integer start, final Integer end) {
        final HashMap<String, Integer> entries = Database.dataZ.get(key);
        if (entries == null) return Collections.singletonList("null");
        int validStopIndex = (end > (entries.size() - 1) || end == -1) ? (entries.size()) : end;
        return entries.entrySet()
                .stream()
                .map(ScoreModel::new)
                .sorted()
                .collect(Collectors.toList())
                .subList(start, validStopIndex)
                .stream()
                .map(ScoreModel::getValue)
                .collect(Collectors.toList());
    }


    public static int getZRANK(final String key, final String keyScore) {
        final HashMap<String, Integer> entries = Database.dataZ.get(key);
        if (entries == null) return -1;
        final Stream<Map.Entry<String, Integer>> stream = entries.entrySet().stream();
        return stream.filter(entry_ -> entry_.getKey().equals(keyScore))
                .findFirst()
                .map(ScoreModel::new)
                .map(scoreModel -> {
                    final List<ScoreModel> scoreModels = stream.map(ScoreModel::new)
                            .sorted()
                            .collect(Collectors.toList());
                    return scoreModels.indexOf(scoreModel);
                }).orElse(-1);
    }
}