package uk.co.bbc.listviewasyncimageloading;

public interface Presenter<T> {
    void onViewCreated(T view);
    void onViewDestroyed();
}
