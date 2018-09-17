package br.com.aquiris.core;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Database {

    private static final Map<String, String> data = new LinkedHashMap<>();
    private static final Map<String, TreeMap<Integer, String>> dataZ = new HashMap<>();

    public static synchronized void set(final String key, final String value) {
        Database.data.put(key, value);
    }

    /**
     * This is a string operation because Redis does not have a dedicated integer type.
     *
     * @see @link{https://redis.io/commands/incr}
     */
    public static synchronized String incr(final String key) {
        final Integer value = Optional.ofNullable(data.get(key))
                .map(Integer::new)
                .map(val -> ++val)
                .orElse(1);
        Database.set(key, String.valueOf(value));
        return Database.get(key);
    }

    public static synchronized String get(final String key) {
        return Database.data.get(key);
    }


    public static synchronized String del(final String key) {
        return Database.data.remove(key);
    }

    public static synchronized Integer getDBSIZE() {
        return Database.data.size();
    }

    public static synchronized TreeMap<Integer, String> zadd(final String key, final int score, final String value) {
        final TreeMap<Integer, String> treeMap = Optional.ofNullable(Database.dataZ.get(key)).orElseGet(TreeMap::new);
        treeMap.put(score, value);
        return Database.dataZ.put(key, treeMap);
    }

    public static synchronized int getZCARD(final String key) {
        return Database.dataZ.get(key).entrySet().size();
    }

    public static synchronized List<String> getZRANGE(final String key, final Integer start, final Integer end) {
        final TreeMap<Integer, String> entries = Database.dataZ.get(key);
        return Optional.ofNullable(entries).map(entries_ -> {
            final List<ScoreModel> scores = Optional.ofNullable(entries_).map(TreeMap::entrySet).orElseGet(HashSet::new)
                    .stream()
                    .map(ScoreModel::new)
                    .collect(Collectors.toList());
            Collections.sort(scores);
            Integer validStopIndex = (end > (scores.size() - 1) || end == -1) ? (scores.size()) : end;
            return scores.subList(start, validStopIndex).stream().map(ScoreModel::getValue).collect(Collectors.toList());
        }).orElse(Arrays.asList("nil"));
    }


    public static synchronized String getZRANK(final String key, final String scoreValue) {
        final TreeMap<Integer, String> entries = Database.dataZ.get(key);
        return Optional.ofNullable(entries).map(entries_ -> {
            final List<ScoreModel> scores = entries.entrySet()
                    .stream()
                    .map(ScoreModel::new)
                    .collect(Collectors.toList());
            Collections.sort(scores);
            final Optional<ScoreModel> scoreModel = scores.stream()
                    .filter(scoreModel_ -> scoreModel_.getValue().equals(scoreValue))
                    .findFirst();
            return scoreModel.map(scoreModel1 -> String.valueOf(scores.indexOf(scoreModel1))).orElse("nil");
        }).orElse("nil");
    }
}