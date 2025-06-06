package step03.p01;

public class BiodomeFamily01 {
    public static void main(String[] args) {
        LifeNest nest = new LifeNest();

        Organism a = new Organism("펭귄", "동물", "남극");
        Organism b = new Organism("코알라", "동물", "호주");
        Organism c = new Organism("선인장", "식물", "사막");
        Organism d = new Organism("페퍼민트", "식물", "정원");
        Organism e = new Organism("고양이", "동물", "모든 곳");

        nest.addOrganism(a);
        nest.addOrganism(b);
        nest.addOrganism(c);
        nest.addOrganism(d);
        nest.addOrganism(e);

        nest.displayAll();

        nest.removeOrganism(b);
        nest.removeOrganism(c);

        a.setHabitat("해변");

        nest.displayAll();
        nest.searchOrganismByName("고양이");
    }
}
