public class Vector2 {
    public float x, y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 other){
        return new Vector2(other.x+x, y+other.y);
    }

    public Vector2 sub(Vector2 other){
        return new Vector2(x- other.x, y-other.y);
    }

    public Vector2 inc(Vector2 other){
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public float length(){
        return (float) Math.sqrt(x*x+y*y);
    }

    public float sqrdLength(){
        return x*x+y*y;
    }

    public Vector2 scale(float m){
        x*=m;
        y*=m;
        return this;
    }
}
