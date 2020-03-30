package lv.javaguru.productlist.domain;

public enum Category {
    FRUIT {
        @Override
        public String toString() {
            return "Fruit";
        }
    },
    VEGETABLES {
        @Override
        public String toString() {
            return "Vegetables";
        }
    },
    DRINKS {
        @Override
        public String toString() {
            return "Drinks";
        }
    },
    OTHER_CATEGORY {
        @Override
        public String toString() {
            return "Other category";
        }
    }
}

