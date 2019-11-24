package com.example.soswedding.model;

public class Singleton {

        private static User INSTANCE = null;

        private Singleton() {};

        public static User getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new User();
            }
            return(INSTANCE); //
        }
}
