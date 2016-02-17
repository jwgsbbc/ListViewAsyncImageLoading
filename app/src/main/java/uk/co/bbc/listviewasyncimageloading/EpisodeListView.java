package uk.co.bbc.listviewasyncimageloading;

interface EpisodeListView {
    interface EpisodeViewModelProvider {
        EpisodeViewModel getEpisodeViewModel(int position);
    }
    void setEpisodeViewModelProvider(EpisodeViewModelProvider episodeImageProvider);
    void setEpisodeCount(int length);
    void notifyItemChanged(int position);
}
