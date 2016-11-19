package cl.telematica.android.certamen3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import cl.telematica.android.certamen3.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this);

        createMyRecyclerView();

        mainPresenter.executeMyAsynctask(this,mRecyclerView);
        //MyAsyncTaskExecutor.getInstance().executeMyAsynctask(this, mRecyclerView);
    }

    public void createMyRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            /**
             * You should manage the action to show the favorite items saved by the user
             */
            mainPresenter.showFavorites(mRecyclerView);

            return true;
        }
        if (id == R.id.action_settings_null) {
            /**
             * You should manage the action to show the favorite items saved by the user
             */
            createMyRecyclerView();

            mainPresenter.executeMyAsynctask(this,mRecyclerView);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
