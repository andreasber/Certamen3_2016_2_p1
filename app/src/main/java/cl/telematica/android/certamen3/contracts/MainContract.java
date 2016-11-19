package cl.telematica.android.certamen3.contracts;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import cl.telematica.android.certamen3.models.Feed;

/**
 * Created by Andreas on 18.11.2016.
 */

public interface MainContract {
    List<Feed> getFeeds(String result);

}