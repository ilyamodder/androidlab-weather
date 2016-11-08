package ru.ilyamodder.contentprovider.sqlite;

/**
 * Created by ilya on 02.11.16.
 */

public interface RequestsTable {
    String NAME = "requests";

    interface Columns extends BaseColumns {
        String RESP_CODE = "response_code";
        String URL = "url";
    }
}
