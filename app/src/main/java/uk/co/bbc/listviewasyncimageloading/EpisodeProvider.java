package uk.co.bbc.listviewasyncimageloading;

import android.os.Handler;

public final class EpisodeProvider {

    private String[] mIds = {
            "p03j0cbd",
            "p03j0c99",
            "p03hjq53",
            "p03jqy1t",
            "p03j7nq4",
            "p03hbntf",
            "p03j0fdd",
            "p03j3n65",
            "p03hx9jp",
            "p03j0gjt",
            "p03j38jw",
            "p03gtcgf",
            "p03hmc9h",
            "p03hxtgm",
            "p03hfnpc",
            "p03j40mp",
            "p03hbc4t",
            "p03hxfkj",
            "p03gmp16",
            "p03hf81d",
            "p03j3mtf",
            "p03j0ky5",
            "p03gqcvw",
            "p03jbjj6",
            "p03j3nyf",
            "p03hdrkn",
            "p03gmpnp",
            "p03hxq7z",
            "p03hmbv6",
            "p03jc5r0",
            "p03j3m9l",
            "p03j1rlr",
    };

    Handler mHandler = new Handler();

    public void requestEpisodes(final EpisodesReceiver episodesReceiver) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                episodesReceiver.onEpisodeReceived(mIds);
            }
        }, 2000);
    }
}
