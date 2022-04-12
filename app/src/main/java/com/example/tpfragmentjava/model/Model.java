package com.example.tpfragmentjava.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
    private static int incId = 0;

    public static final List<Category> ITEMS = new ArrayList<Category>();

    public static final Map<String, Category> ITEM_MAP = new HashMap<String, Category>();

    static {
            Category boisson = createCategory(++incId, "Boisson");
            makeArticles(boisson, new String[] {"Fanta", "Pepsi", "Coca"});
            addItem(boisson);
            Category fruit = createCategory(++incId, "Fruit");
            makeArticles(fruit, new String[] {"Orange", "Citron", "Mango", "Ananas"});
            addItem(fruit);
    }

    private static void addItem(Category item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Category createCategory(int position, String name) {
        return new Category(String.valueOf(position), name);
    }

    private static void makeArticles(Category category, String[] articles) {
        for (String article : articles) {
            category.addArticle(article);
        }
    }

    public static class Category {
        public final String id;
        public final String name;
        private final List<String> articles = new ArrayList<>();

        public Category(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public void addArticle(String article) {
            articles.add(article);
        }

        public List<String> getArticles() {
            return Collections.unmodifiableList(articles);
        }

        @Override
        public String toString() {
            return name;
        }
    }
}