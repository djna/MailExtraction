package org.djna.email;

import java.util.Objects;

public class Domain {
    private String name;
    private int count;
    public Domain(String initName){
        name = initName;
        count = 0;
    }

    public void incrementCount(){
        count++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Domain domain = (Domain) o;
        return count == domain.count && Objects.equals(name, domain.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, count);
    }

    @Override
    public String toString() {
        return "Domain{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }
}
