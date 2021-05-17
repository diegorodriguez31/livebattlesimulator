public class Vector2 {
    public float x,y;

    /**
     * Copy constructor for vector2
     * @param other vector to copy
     */
    public Vector2(Vector2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    /**
     * Create a vector given parts
     * @param x part
     * @param y part
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Add two vector together
     * @param other
     * @return the addition result
     */
    Vector2 add(Vector2 other){
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    /**
     * Subtract two vector together
     * @param other
     * @return the subscription result
     */
    Vector2 sub(Vector2 other){
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    /**
     * Increment this with an other Vector2
     * @param other
     * @return the incremented Vector2
     */
    Vector2 inc(Vector2 other){
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    /**
     * Decrement this with an other Vector2
     * @param other
     * @return the decrement Vector2
     */
    Vector2 dec(Vector2 other){
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    /**
     * Return the size of the vector
     * @return the size
     */
    float size(){
        return (float) Math.sqrt(this.sqrSize());
    }

    /**
     * Returns the square of the size of the vector
     * Faster than plain size can be used for size comparaison
     * @return the size
     */
    float sqrSize(){
        return this.x * this.x + this.y * this.y;
    }

    /**
     * Scales the vector
     * @param scale factor
     * @return Scaled vector
     */
    Vector2 scale(float scale){
        return new Vector2(this.x * scale, this.y * scale);
    }

    /**
     * Scales the vector to be of lenght 1
     * @return The normalized vector
     */
    Vector2 normalize(){
        return scale(1/size());
    }

    /**
     * Scales the vector to be of a given length
     * @return The normalized vector
     */
    Vector2 normalize(float length){
        return scale(length/size());
    }
}
