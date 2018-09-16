package br.com.aquiris.core;

import br.com.aquiris.core.domain.DataModel;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class Database {

    private static final Map<String, DataModel> data = new LinkedHashMap<>();

    public static synchronized void set(final String key, final String value) {
        Database.data.put(key, new DataModel(key, value));
    }

    /**
     * This is a string operation because Redis does not have a dedicated integer type.
     *
     * @see @link{https://redis.io/commands/incr}
     */
    public static synchronized String incr(final String key) {
        final Integer value = Optional.ofNullable(data.get(key))
                .map(DataModel::getValue)
                .map(Integer::new)
                .map(val -> ++val)
                .orElse(1);
        Database.set(key, String.valueOf(value));
        return Database.get(key);
    }

    public static synchronized String get(final String key) {
        return Database.data.get(key).getValue();
    }


    public static synchronized DataModel del(final String key) {
        return Database.data.remove(key);
    }

    public static synchronized Integer getDBSize() {
        return Database.data.size();
    }

    public static synchronized void zadd(final String key, final int score, final String value) {
        Database.data.put(key, new DataModel(key, score, value));
    }

    public static synchronized String getZcard(final String key) {
        //TODO
        return null;
    }
}