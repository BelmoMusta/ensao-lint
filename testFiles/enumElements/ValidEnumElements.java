enum ValidEnumElements.java {
    DOG("woof"), CAT("meow"), FISH("bubble"), BIRD("tweet");

    private String sound;

    Animal(String sound) {
        this.sound = sound;
    }

    public String getSound() {
        return sound;
    }
}