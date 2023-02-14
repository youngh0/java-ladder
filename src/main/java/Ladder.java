public class Ladder {
    private final int height;
    Ladder(int height){
        if (height < 1 || height > 10) {
            throw new IllegalArgumentException("사다리 높이는 1에서 10");
        }
        this.height = height;
    }
}
