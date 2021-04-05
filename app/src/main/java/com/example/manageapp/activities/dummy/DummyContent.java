package com.example.manageapp.activities.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */

    private static final int COUNT = 25;

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String category;
        public final String amount;
        public final String date;

        public DummyItem(String id, String content, String category, String amount ,String date) {
            this.id = id;
            this.content = content;
            this.category = category;
            this.amount = amount;
            this.date = date;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}