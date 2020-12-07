package Organization;

import java.util.Objects;

public class Department {
        private String name;
        private String description;
        private int id;
        private int size;



        public Department(String name, String description) {
            this.name = name;
            this.description = description;
            this.size=0;
        }
        public String getDescription() {
            return description;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getName() {
            return name;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Department that = (Department) o;
            return id == that.id &&
                    Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, id);
        }
}
