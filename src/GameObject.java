public interface GameObject {
    void start(Battle context);
    void update(Battle context, float deltaTime);
    void end(Battle context);
}
